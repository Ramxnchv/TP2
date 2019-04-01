package simulator.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
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
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {
	
	private Controller _ctrl;
	private boolean _stopped;
	
	public ControlPanel (Controller ctrl) {
		this._ctrl=ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}

	private void initGUI() {
		// TODO build the tool bar by adding buttons, etc.
		createToolBarComponents();
		// other private/protected methods
		// ...
		
	}
	
	private void createToolBarComponents(){
		JToolBar toolBar = new JToolBar();
		this.add(toolBar,BorderLayout.PAGE_START);
		
		JFileChooser fileSelector = new JFileChooser();
		JButton lawSelector = new JButton();
		lawSelector.setActionCommand("load");
		lawSelector.setToolTipText("Choose the law to use");
		lawSelector.setIcon(new ImageIcon("resources/icons/physics.png"));
		lawSelector.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventanaDialogo = new JFrame();
				Object[] possibilities = _ctrl.getGravityLawsFactory().getInfo().toArray();
				String n = (String) JOptionPane.showInputDialog(ventanaDialogo,"Select gravity laws to be used: ","Gravity Laws Selector",JOptionPane.INFORMATION_MESSAGE,null,possibilities,"No gravity (ng)");
				if(n.equals("Newton's law of universal gravitation (nlug)")){
					_ctrl.setGravityLaws(info);
					
					/*for (JSONObject i : _gravityLawsFactory.getInfo()) {
						if (gl.equals(i.getString("type"))) {
							_gravityLawsInfo = i;
							break;
						}
					}*/
				}
				else if(n.equals("Falling to center gravity (ftcg)")){
					_ctrl.setGravityLaws(info);
				}
				else if(n.equals("No gravity (ng)")){
					_ctrl.setGravityLaws(info);
				}
				else{
					
				}
			}
			
		});
		
		JButton playButton = new JButton();
		JButton stopButton = new JButton();
		
		JLabel stepsLabel = new JLabel("Steps: ");
		JLabel deltaTimeLabel = new JLabel("Delta-Time: ");
		JSpinner stepsSpinner = new JSpinner();
		JTextArea dtSelector = new JTextArea();
		
		toolBar.add(fileSelector);
		toolBar.add(lawSelector);
		toolBar.add(playButton);
		toolBar.add(stopButton);
		toolBar.add(stepsLabel);
		toolBar.add(deltaTimeLabel);
		toolBar.add(stepsSpinner);
		toolBar.add(dtSelector);
		
		
	}
	
	private void run_sim(int n) {
		
		if ( n>0 && !_stopped ) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// TODO show the error in a dialog box
				// TODO enable all buttons
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
			}
		}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		
	} 

}
