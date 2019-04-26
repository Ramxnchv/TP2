package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {
	
	private Controller _ctrl;
	private volatile Thread _thread;
	
	private JToolBar toolBar;
	private JButton loadButton;
	private JButton lawSelector;
	private JButton playButton;
	private JButton stopButton;
	private JLabel delayLabel;
	private JLabel stepsLabel;
	private JLabel deltaTimeLabel;
	private JSpinner delaySpinner;
	private JSpinner stepsSpinner;
	private JTextArea dtSelector;
	private JButton exitButton;
	
	
	ControlPanel (Controller ctrl) {
		super(new BorderLayout());
		this._ctrl=ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}

	private void initGUI() {
		// TODO build the tool bar by adding buttons, etc.
		createToolBarComponents();
	}
	
	private void createToolBarComponents() {
		this.toolBar = new JToolBar();
		this.add(toolBar,BorderLayout.PAGE_START);
		
		//Selector de archivo -------------------------------------------------
		this.loadButton = new JButton();
		loadButton.setActionCommand("load");
		loadButton.setToolTipText("Load the body file");
		loadButton.setIcon(new ImageIcon("resources/icons/open.png"));
		loadButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileSelector = new JFileChooser();
				int selection = fileSelector.showOpenDialog(loadButton);
				if(selection==JFileChooser.APPROVE_OPTION){
					_ctrl.reset();
					try {
						_ctrl.loadBodies(new FileInputStream(fileSelector.getSelectedFile()));
					} catch (FileNotFoundException ex) {
						System.out.println("Error, archivo seleccionado inexistente");
					}
				}
			}
			
		});
		
		
		//Selector de leyes -------------------------------------------------
		this.lawSelector = new JButton();
		lawSelector.setActionCommand("load law");
		lawSelector.setToolTipText("Choose the law to use");
		lawSelector.setIcon(new ImageIcon("resources/icons/physics.png"));
		lawSelector.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventanaDialogo = new JFrame();
				
				//Obtener posibilidades con la factoria de leyes
				Object[] possibilities = new Object[_ctrl.getGravityLawsFactory().getInfo().size()];
				int contador=0;
				for (JSONObject i: _ctrl.getGravityLawsFactory().getInfo()){
					possibilities[contador]= i.get("desc");
					contador++;
				}
				
				//Ventana de Seleccion
				String n = (String) JOptionPane.showInputDialog(ventanaDialogo,"Select gravity laws to be used: ","Gravity Laws Selector",JOptionPane.INFORMATION_MESSAGE,null,possibilities,possibilities[2]);
				
				if(n!=null){
					//Comprobar opcion y cambiar ley del simulador
					for (JSONObject i: _ctrl.getGravityLawsFactory().getInfo()){
						if(n.equals(i.getString("desc"))){
							_ctrl.setGravityLaws(i);
							break;
						}
					}	
				}
					
			}
			
		});
		
		//Selectores y Spinners ------------------------------------------------------
		this.delayLabel = new JLabel("Delay: ");
		this.stepsLabel = new JLabel("Steps: ");
		this.deltaTimeLabel = new JLabel("Delta-Time: ");
		
		SpinnerNumberModel delaySpinnerModel = new SpinnerNumberModel(0,0,1000,1);
		this.delaySpinner = new JSpinner(delaySpinnerModel);
		this.delaySpinner.setPreferredSize(new Dimension(50,30));
		this.delaySpinner.setMaximumSize(new Dimension(50,30));
		
		
		SpinnerNumberModel stepsSpinnerModel = new SpinnerNumberModel(0,0,1000000,500);
		this.stepsSpinner = new JSpinner(stepsSpinnerModel);
		this.stepsSpinner.setPreferredSize(new Dimension(65,30));
		this.stepsSpinner.setMaximumSize(new Dimension(65,30));
		
		this.dtSelector = new JTextArea();
		this.dtSelector.setPreferredSize(new Dimension(65,25));
		this.dtSelector.setMaximumSize(new Dimension(65,25));

		
		
		//Boton Play --------------------------------------------------------------
		this.playButton = new JButton();
		playButton.setActionCommand("play");
		playButton.setToolTipText("Start the simulation");
		playButton.setIcon(new ImageIcon("resources/icons/run.png"));
		playButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deshabilitarBotones();
				_ctrl.setDeltaTime(Double.parseDouble(dtSelector.getText()));
				
				_thread = new Thread(new Runnable(){
					public void run() {
						run_sim(Integer.parseInt(stepsSpinner.getValue().toString()),Long.parseLong(delaySpinner.getValue().toString()));
						habilitarBotones();
						_thread = null;
					}
				});
				
				_thread.start();
				
			}
			
		});
		
		//Boton Stop ---------------------------------------------------------------
		this.stopButton = new JButton();
		stopButton.setActionCommand("stop");
		stopButton.setToolTipText("Stops the simulation");
		stopButton.setIcon(new ImageIcon("resources/icons/stop.png"));
		stopButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(_thread!=null){
					_thread.interrupt();
				}
			}
			
		});
		
		//Boton Exit ----------------------------------------------------------
		
		this.exitButton= new JButton();
		exitButton.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		exitButton.setActionCommand("exit");
		exitButton.setToolTipText("Closes the simulator");
		exitButton.setIcon(new ImageIcon("resources/icons/exit.png"));
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] options = {"Si","No","Cancelar"};
				int op= JOptionPane.showOptionDialog(exitButton, "Do you want to exit?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if(op==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
			
		});
		
		
		//Toolbar --------------------------------------------------------------------
		toolBar.add(loadButton);
		toolBar.addSeparator();
		toolBar.add(lawSelector);
		toolBar.addSeparator();
		toolBar.add(playButton);
		toolBar.add(stopButton);
		toolBar.addSeparator();
		toolBar.add(delayLabel);
		toolBar.add(delaySpinner);
		toolBar.addSeparator();
		toolBar.add(stepsLabel);
		toolBar.add(stepsSpinner);
		toolBar.addSeparator();
		toolBar.add(deltaTimeLabel);
		toolBar.add(dtSelector);
		toolBar.addSeparator();
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(exitButton);
		
	}
	
	private void deshabilitarBotones(){
		this.stopButton.setEnabled(true);
		this.loadButton.setEnabled(false);
		this.playButton.setEnabled(false);
		this.lawSelector.setEnabled(false);
	}
	
	private void habilitarBotones(){
		this.stopButton.setEnabled(true);
		this.loadButton.setEnabled(true);
		this.playButton.setEnabled(true);
		this.lawSelector.setEnabled(true);
	}
	
	private void run_sim(int n, long delay) {
		
		while ( n>0 && (!Thread.interrupted()) ) {
			// 1. execute the simulator one step, i.e., call method
			// _ctrl.run(1) and handle exceptions if any
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// TODO show the error in a dialog box
				SwingUtilities.invokeLater( new Runnable() {
					@Override
					public void run() {
						JOptionPane.showMessageDialog(toolBar, e.getMessage(), "Error in Simulator", JOptionPane.ERROR_MESSAGE);
					}
				});
				
				// TODO enable all buttons
				this.habilitarBotones();
				return;
			}
				
			// 2. sleep the current thread for ’delay’ milliseconds
			try{
				Thread.sleep(delay);
			}catch(InterruptedException e){
				return;
			}
			
			n--;
		}
		
		/*if ( n>0 && !_stopped ) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// TODO show the error in a dialog box
				JOptionPane.showMessageDialog(this.toolBar, e.getMessage(), "Error in Simulator", JOptionPane.ERROR_MESSAGE);
				// TODO enable all buttons
				this.habilitarBotones();
				_stopped = true;
				return;
		}
			
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				run_sim(n-1);
			}
		});
		
		} else {
			_stopped = true;
			// TODO enable all buttons
			this.habilitarBotones();
		}*/
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				dtSelector.setText(Double.toString(dt));
				stepsSpinner.setValue(10000);
			}
		});
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				dtSelector.setText(Double.toString(dt));
				stepsSpinner.setValue(10000);
			}
		});
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				dtSelector.setText(Double.toString(dt));
			}
		});
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		
	} 

}
