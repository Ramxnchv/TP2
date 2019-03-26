package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulator {
	private double tiempoPaso;
	private GravityLaws leyes;
	private List<Body> cuerpos;
	private double tiempoActual;
	private List<SimulatorObserver> simulatorObserver;


	public PhysicsSimulator(double tiempoPaso, GravityLaws leyes) {
		tiempoActual=0.0;
		this.tiempoPaso=tiempoPaso;
		this.leyes=leyes;
		this.cuerpos = new ArrayList<>();
		this.simulatorObserver = new ArrayList<>();
	}

	public void advance(){
		leyes.apply(cuerpos);
		
		for(Body i:cuerpos){
			
			i.move(tiempoPaso);
		}
		tiempoActual+=tiempoPaso;
	}

	public void addBody(Body b) throws IllegalArgumentException{
		if(!cuerpos.contains(b)){
			cuerpos.add(b);
		}
		else{
			throw new IllegalArgumentException("Existe un cuerpo con el mismo ID");
		}
	}

	public String toString(){
		StringBuilder sb= new StringBuilder();
		String info="{ \"time\": "+tiempoActual+", \"bodies\": [";
		String end="] }";

		sb.append(info);
			
		int c=0;
		
		for(Body i:cuerpos){
			int n=cuerpos.size();
			if(c< n-1){
				sb.append(i.toString()+", ");
			}
			else{
				sb.append(i.toString());
			}
			c++;
		}

		return sb.append(end).toString();
	}
	
	public void reset() {
		cuerpos = new ArrayList<>();
		tiempoPaso = 0.0;
	}
	
	public void setDeltaTime(double dt) throws IllegalArgumentException{
		if(dt<0) {
			throw new IllegalArgumentException("Valor no valido de delta-time");
		}
		
		this.tiempoPaso = dt;
		
	}
	
	public void setGravityLaws(GravityLaws gravityLaws)throws IllegalArgumentException{
		if(gravityLaws==null) {
			throw new IllegalArgumentException("Valor no valido de gravityLaws");
		}
		
		this.leyes=gravityLaws;
	}
	
	public void addObserver(SimulatorObserver o) {
		if(!this.simulatorObserver.contains(o)) {
			simulatorObserver.add(o);
		}
	}
	
	

}
