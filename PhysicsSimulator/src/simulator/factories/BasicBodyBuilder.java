package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body> {
	
	public BasicBodyBuilder(){
		
	}
	
	public Body createTheInstance(JSONObject jsonobject) throws IllegalArgumentException{
		Body b = null;
		
		if(jsonobject.get("type").equals("basic")){
			
			JSONObject data=jsonobject.getJSONObject("data");
				try{
					String id= data.getString("id");
					Vector position= new Vector(jsonArrayToDoubleArray(jsonobject.getJSONArray("pos")));
					Vector velocity= new Vector(jsonArrayToDoubleArray(jsonobject.getJSONArray("vel")));
					double mass= data.getDouble("mass");
					Vector acceleration= new Vector(velocity.dim());
			
					b = new Body(id, velocity, acceleration, position, mass);
					
				}catch(JSONException e){
					throw new IllegalArgumentException("Clave inexistente");
				}
		}
		
		return b;
	}
	
	public JSONObject getBuilderInfo(){
		JSONObject info= new JSONObject();
		info.accumulate("type", "basic");
		info.accumulate("data", this.createData());
		info.accumulate("desc", "Basic Body");
		return info;
	}
	
	private JSONObject createData(){
		JSONObject data= new JSONObject();
		
		double[] doubleVector= new double[2];
		doubleVector[0]=0.0;
		doubleVector[1]=0.0;
		Vector defaultVector = new Vector(doubleVector);
		
		data.accumulate("id", "default");
		data.accumulate("pos", defaultVector);
		data.accumulate("vel", defaultVector);
		data.accumulate("mass", Double.valueOf(0.0));
		
		return data;
	}
	
}
