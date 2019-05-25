package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class VelocityTableModel extends AbstractTableModel implements SimulatorObserver{
	
	private List<Body> _bodies;
	
	private List<Double> _averages;
	
	private double _numVelocidades;
	
	
	private String[] _columns = {"Id", "Average Velocity"};
	
	VelocityTableModel (Controller ctrl){
		_bodies = new ArrayList<>();
		_averages = new ArrayList<>();
		ctrl.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return _columns.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return _bodies.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return _columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object s = null;
		Body b = this._bodies.get(rowIndex);
		
		switch(columnIndex){
			case 0:
				s = b.getId();
				break;
			case 1:
				s = _averages.get(rowIndex)/_numVelocidades;
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
				_averages = new ArrayList<>();
				_numVelocidades = 0;
				
				for(int i=0;i<bodies.size();i++){
					_averages.add(i, _bodies.get(i).getVelocity().magnitude());
				}
				
				_numVelocidades++;
				
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
				_averages = new ArrayList<>();
				_numVelocidades = 0;
				
				for(int i=0;i<bodies.size();i++){
					_averages.add(i, _bodies.get(i).getVelocity().magnitude());
				}
				
				_numVelocidades++;
				
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
				_averages = new ArrayList<>();
				_numVelocidades = 0;
				
				for(int i=0;i<bodies.size();i++){
					_averages.add(i, _bodies.get(i).getVelocity().magnitude());
				}
				
				_numVelocidades++;
				
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
				
				for(int i=0;i<bodies.size();i++){
					_averages.set(i, _bodies.get(i).getVelocity().magnitude()+_averages.get(i));
				}
				
				_numVelocidades++;
				
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
