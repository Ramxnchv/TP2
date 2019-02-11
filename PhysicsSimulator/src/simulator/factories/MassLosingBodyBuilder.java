package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body> {
	
	public MassLosingBodyBuilder(){
		
	}
	
	public Body createTheInstance(JSONObject jsonobject) throws IllegalArgumentException{
		MassLossingBody mlb = null;
		
		if(jsonobject.get("type").equals("basic")){
			
			JSONObject data=jsonobject.getJSONObject("data");

			try{
				String id= data.getString("id");
				Vector position= new Vector(jsonArrayToDoubleArray(jsonobject.getJSONArray("pos")));
				Vector velocity= new Vector(jsonArrayToDoubleArray(jsonobject.getJSONArray("vel")));
				double mass= data.getDouble("mass");
				double frequency= data.getDouble("freq");
				double factor= data.getDouble("factor");
				Vector acceleration= new Vector(velocity.dim());
				
				mlb = new MassLossingBody(id, velocity, acceleration, position, mass,frequency,factor);
				
			}catch(JSONException e){
				throw new IllegalArgumentException("Clave inexistente");
			}
		}
		
		return mlb;
	}
	
	public JSONObject getBuilderInfo(){
		JSONObject info= new JSONObject();
		info.accumulate("type", "mlb");
		info.accumulate("data", this.createData());
		info.accumulate("desc", "Lossing Mass Body");
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
		data.accumulate("freq", Double.valueOf(0.0));
		data.accumulate("factor", Double.valueOf(0.0));

		return data;
	}
	
}
