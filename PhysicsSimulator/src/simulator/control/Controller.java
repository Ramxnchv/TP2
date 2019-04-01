package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class Controller {
	private PhysicsSimulator simulator;
	private Factory<Body> factoryBody;
	private Factory<GravityLaws> factoryLaws;

	public Controller(PhysicsSimulator simulator, Factory<Body> factoryBody,Factory<GravityLaws> factoryLaws){
		this.simulator=simulator;
		this.factoryBody=factoryBody;
		this.factoryLaws = factoryLaws;	
	}
	
	public Controller(PhysicsSimulator simulator, Factory<Body> factoryBody){
		this.simulator=simulator;
		this.factoryBody=factoryBody;
	}

	public void loadBodies(InputStream in){
		JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		JSONArray bodies = jsonInupt.getJSONArray("bodies");


		for(int i=0;i<bodies.length();i++) {
			simulator.addBody(factoryBody.createInstance(bodies.getJSONObject(i)));
		}

	}

	public void run(int n, OutputStream out){
				
		//Si out no es null lo imprime en el archivo de salida
		if(out!=null){
			StringBuilder sb = new StringBuilder();
			sb.append("{"+" \"states\": [ ");
			PrintStream p = new PrintStream(out);
			
			
			for(int i=0;i<n;i++) {
				sb.append(simulator.toString());
				if(i<(n-1)){
					sb.append(",");
				}
				
				simulator.advance();
			}
			
			sb.append("] }");
			
			//jsonOutput.append("states", states);
			p.print(sb.toString());
			p.close();
		}
		
		//Si no hay archivo de salida se utiliza la salida por consola
		else{
			for(int i=0;i<n;i++) {
				System.out.println("Paso "+i+" :");
				System.out.println(simulator.toString());
				simulator.advance();
			}
		}
	}
	
	public void run(int n) {
		//implementar
		for(int i=0;i<n;i++){
			simulator.advance();
		}
	}
	
	public void reset() {
		simulator.reset();
	}
	
	public void setDeltaTime(double dt) {
		simulator.setDeltaTime(dt);
	}
	
	public void addObserver(SimulatorObserver o) {
		simulator.addObserver(o);
	}
	
	public Factory<GravityLaws> getGravityLawsFactory(){
		return this.factoryLaws;
	}
	
	public void setGravityLaws(JSONObject info) {
		simulator.setGravityLaws(factoryLaws.createInstance(info));
	}


}
