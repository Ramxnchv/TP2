package simulator.view;

import java.util.ArrayList;
import java.util.List;

import simulator.model.Body;
import simulator.model.SimulatorObserver;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
	
	private List<Body> _bodies;
	private String[] _columns = {"Id", "Mass", "Position", "Velocity", "Acceleration"};
	
	
	BodiesTableModel(Controller ctrl) {
		
		_bodies = new ArrayList<>();
		
		ctrl.addObserver(this);
	}


	@Override
	public int getColumnCount() {
		return _columns.length;
	}

	@Override
	public int getRowCount() {
		return _bodies.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return _columns[column];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object s = null;
		Body b = this._bodies.get(row);
		
		switch(col){
			case 0:
				s = b.getId();
				break;
			case 1:
				s = b.getMass();
				break;
			case 2:
				s= b.getPosition();
				break;
			case 3:
				s= b.getVelocity();
				break;
			case 4:
				s=b.getAcceleration();
				break;
			
			default: assert (false);
		}
		return s;
	}


	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				_bodies=bodies;
				fireTableStructureChanged();
			}
		});
	
	}


	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				_bodies=bodies;
				fireTableStructureChanged();
			}
		});
		
	}


	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				_bodies=bodies;
				fireTableStructureChanged();
			}
		});
		
	}


	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				_bodies=bodies;
				fireTableStructureChanged();
			}
		});
		
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

