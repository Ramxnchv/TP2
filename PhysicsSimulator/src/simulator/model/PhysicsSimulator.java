package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulator {
	private double tiempoPaso;
	private GravityLaws leyes;
	private List<Body> cuerpos;
	private double tiempoActual;


	public PhysicsSimulator(double tiempoPaso, GravityLaws leyes, ArrayList<Body> cuerpos) {
		tiempoActual=0.0;
		this.tiempoPaso=tiempoPaso;
		this.leyes=leyes;
		this.cuerpos=cuerpos;
	}

	public void advance(){
		leyes.apply(cuerpos);
		for(Body i:cuerpos){
			i.move(tiempoPaso);
		}
		tiempoActual+=tiempoPaso;
	}

	public void addBody(Body b) throws IllegalArgumentException{
		for(Body i:cuerpos){
			if(i.getId()==b.getId()){
				throw new IllegalArgumentException("Existe un cuerpo con el mismo ID");
			}
		}

		cuerpos.add(b);

	}

	public String toString(){
		StringBuilder sb= new StringBuilder();
		String info="{ \"time\": "+tiempoActual+",\"bodies\": [";
		String end="] }";

		sb.append(info);

		for(Body i:cuerpos){
			sb.append(i.toString()+", ");
		}

		return sb.append(end).toString();
	}

}
