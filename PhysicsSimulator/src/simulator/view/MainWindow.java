package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {
	
	private Controller _ctrl;
	private ControlPanel controlPanel;
	private BodiesTable bodiesTable;
	private Viewer viewer;
	private StatusBar statusBar;
	
	public MainWindow (Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		
		// TODO complete this method to build the GUI
		this.controlPanel = new ControlPanel(_ctrl);
		this.statusBar = new StatusBar(_ctrl);
		
		mainPanel.add(controlPanel, BorderLayout.PAGE_START);
		mainPanel.add(statusBar, BorderLayout.PAGE_END);
		mainPanel.add(createCentralPanel(), BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	// other private/protected methods
	// ...
	private JPanel createCentralPanel(){
		JPanel centralPanel = new JPanel(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
		this.bodiesTable = new BodiesTable(_ctrl);
		this.bodiesTable.setPreferredSize(new Dimension(1200,300));
		centralPanel.add(bodiesTable);
		this.viewer = new Viewer(_ctrl);
		this.viewer.setPreferredSize(new Dimension(1200,500));
		centralPanel.add(viewer);
		centralPanel.setBackground(Color.WHITE);
		return centralPanel;
	}
	
}
