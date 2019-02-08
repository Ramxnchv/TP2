package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	private PhysicsSimulator simulator;
	private Factory<Body> factoryBody;
	
	public Controller(PhysicsSimulator simulator, Factory<Body> factoryBody){
		this.simulator=simulator;
		this.factoryBody=factoryBody;
	}
	
	public void loadBodies(InputStream in){
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		//Mirar como parsear bien cuerpos del JSON (Incompleto)
		jsonInput.get("bodies");
		Body b=factoryBody.createInstance(jsonInput);
		simulator.addBody(b);
	}
	
	public void run(int n, OutputStream out){
		
	}
	
}
