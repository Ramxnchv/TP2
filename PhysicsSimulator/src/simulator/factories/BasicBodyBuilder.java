package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body> {

	public BasicBodyBuilder(){
		super("basic","Basic Body");
	}

	public Body createTheInstance(JSONObject jsonobject) throws IllegalArgumentException{
		Body b = null;

	//	if(jsonobject.get("type").equals("basic")){

			JSONObject data=jsonobject.getJSONObject("data");
				try{
					String id= data.getString("id");
					double[] p = jsonArrayToDoubleArray(data.getJSONArray("pos"));
					Vector position = new Vector(p);
					double[] v = jsonArrayToDoubleArray(data.getJSONArray("vel"));
					Vector velocity = new Vector(v);
					/*Vector position= new Vector(jsonArrayToDoubleArray(jsonobject.getJSONArray("pos")));
					Vector velocity= new Vector(jsonArrayToDoubleArray(jsonobject.getJSONArray("vel")));*/
					double mass= data.getDouble("mass");
					Vector acceleration= new Vector(v.length);

					b = new Body(id, velocity, acceleration, position, mass);

				}catch(JSONException e){
					throw new IllegalArgumentException("Clave inexistente");
				}
		//}

		return b;
	}

	@Override
	public JSONObject createData(){
		JSONObject data= new JSONObject();

		data.put("id", "the identifier");
		data.put("pos", "the position");
		data.put("vel", "the velocity");
		data.put("mass", "the mass");

		return data;
	}

}
