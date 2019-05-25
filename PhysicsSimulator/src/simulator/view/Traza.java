package simulator.view;

import java.util.ArrayList;
import java.util.List;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Traza implements SimulatorObserver {

	private List<Body> _bodies;
	private int _steps;
	private List<List<Vector>> _positions;
	
	public Traza(Controller ctrl){
		_steps=0;
		_bodies = new ArrayList<>();
		_positions = new ArrayList<>();
		ctrl.addObserver(this);
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Traza de los cuerpos: ");
		int counter=1;
		for(List<Vector> i:_positions){
			sb.append("\n\n"+"b"+counter+": { ");
			for(int j=0;j<_steps;j++){
				sb.append(" p"+j+": "+i.get(j).toString()+", ");
			}
			sb.append("}\n");
			counter++;
		}
			
		return sb.toString();
	}
	
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		_bodies=bodies;
		for(int i=0;i<_bodies.size();i++){
			_positions.add(new ArrayList<>());
			_positions.get(i).add(_steps, _bodies.get(i).getPosition());
		}
		_steps++;
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		_bodies=bodies;
		for(int i=0;i<_bodies.size();i++){
			_positions.add(new ArrayList<>());
			_positions.get(i).add(_steps, _bodies.get(i).getPosition());
		}
		_steps++;
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		_bodies=bodies;
		for(int i=0;i<_bodies.size();i++){
			_positions.add(new ArrayList<>());
			_positions.get(i).add(_steps, _bodies.get(i).getPosition());
		}
		_steps++;
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		_bodies=bodies;
		for(int i=0;i<_bodies.size();i++){
			_positions.get(i).add(_steps, _bodies.get(i).getPosition());
		}
		_steps++;
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
