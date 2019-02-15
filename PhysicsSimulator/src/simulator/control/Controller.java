package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONArray;
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
		JSONArray bodies = new JSONArray();
		
		bodies=jsonInput.getJSONArray("bodies");
		
		for(int i=0;i<bodies.length();i++) {
			simulator.addBody(factoryBody.createInstance(bodies.getJSONObject(i)));
		}
		
	}
	
	public void run(int n, OutputStream out){
		JSONObject jsonOutput = new JSONObject(out);
		JSONArray states = new JSONArray();
		
		for(int i=0;i<n;i++) {
			states.put(simulator.toString());
			simulator.advance();
		}
		
		jsonOutput.append("states", states);
		
	}
	
}
