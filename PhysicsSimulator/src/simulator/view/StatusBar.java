package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {
	
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	
	StatusBar (Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		// TODO complete the code to build the tool bar
		this._currTime = new JLabel("Time: ");
		this.add(_currTime);
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this._numOfBodies = new JLabel("Bodies: ");
		this.add(_numOfBodies);
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this._currLaws = new JLabel("Laws: ");
		this.add(_currLaws);
		
		}
	

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		this._currTime.setText("Time: "+time);
		this._numOfBodies.setText("Bodies: "+bodies.size());
		this._currLaws.setText("Laws: none ");
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		this._currTime.setText("Time: "+time);
		this._numOfBodies.setText("Bodies: "+bodies.size());
		this._currLaws.setText("Laws: "+gLawsDesc);
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		this._numOfBodies.setText("Bodies: "+bodies.size());
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		this._currTime.setText("Time: "+time);
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		this._currLaws.setText("Laws: "+gLawsDesc);
	}

}
