package simulator.view;

import java.awt.BorderLayout;

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
	
	MainWindow (Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		// TODO complete this method to build the GUI
		this.add(controlPanel, BorderLayout.PAGE_START);
		this.add(statusBar, BorderLayout.PAGE_END);
		
		JPanel centralPanel = new JPanel(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		centralPanel.add(bodiesTable);
		centralPanel.add(viewer);
		this.add(centralPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	// other private/protected methods
	// ...
}
