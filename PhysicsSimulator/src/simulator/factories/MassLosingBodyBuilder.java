package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body> {

	public MassLosingBodyBuilder(){
		super("mlb","Lossing Mass Body");
	}

	public Body createTheInstance(JSONObject data) throws IllegalArgumentException{
			MassLossingBody mlb = null;
			
			try{
				String id= data.getString("id");
				double[] p = jsonArrayToDoubleArray(data.getJSONArray("pos"));
				Vector position = new Vector(p);
				double[] v = jsonArrayToDoubleArray(data.getJSONArray("vel"));
				Vector velocity = new Vector(v);
				double mass= data.getDouble("mass");
				double frequency= data.getDouble("freq");
				double factor= data.getDouble("factor");
				Vector acceleration= new Vector(velocity.dim());

				mlb = new MassLossingBody(id, velocity, acceleration, position, mass,factor,frequency);

			}catch(JSONException e){
				throw new IllegalArgumentException("Clave inexistente");
			}

		return mlb;
	}

	@Override
	public JSONObject createData(){
		JSONObject data= new JSONObject();
		data.put("id", "the identifier");
		data.put("pos", "the position");
		data.put("vel", "the velocity");
		data.put("mass", "the mass");
		data.put("freq", "the frequency");
		data.put("factor", "the factor");

		return data;
	}

}
