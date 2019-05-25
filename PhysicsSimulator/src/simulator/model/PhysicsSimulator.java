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
		
		for(SimulatorObserver i: simulatorObserver){
			i.onAdvance(this.cuerpos, this.tiempoActual);
		}
		
	}

	public void addBody(Body b) throws IllegalArgumentException{
		if(!cuerpos.contains(b)){
			cuerpos.add(b);
			for(SimulatorObserver i: simulatorObserver){
				i.onBodyAdded(this.cuerpos, b);
			}
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
		tiempoActual = 0.0;
		tiempoPaso = 2500.0;
		for(SimulatorObserver i: simulatorObserver){
			i.onReset(this.cuerpos, this.tiempoActual, this.tiempoPaso, this.leyes.toString());
		}
	}
	
	public void setDeltaTime(double dt) throws IllegalArgumentException{
		if(dt<0) {
			throw new IllegalArgumentException("Valor no valido de delta-time");
		}
		
		this.tiempoPaso = dt;
		
		for(SimulatorObserver i: simulatorObserver){
			i.onDeltaTimeChanged(this.tiempoPaso);
		}
		
	}
	
	public void setGravityLaws(GravityLaws gravityLaws)throws IllegalArgumentException{
		if(gravityLaws==null) {
			throw new IllegalArgumentException("Valor no valido de gravityLaws");
		}
		
		this.leyes=gravityLaws;
		
		for(SimulatorObserver i: simulatorObserver){
			i.onGravityLawChanged(this.leyes.toString());
		}
		
	}
	
	public void addObserver(SimulatorObserver o) {
		if(!this.simulatorObserver.contains(o)) {
			simulatorObserver.add(o);
			o.onRegister(this.cuerpos, this.tiempoActual, this.tiempoPaso,this.leyes.toString());
		}
	}

}
