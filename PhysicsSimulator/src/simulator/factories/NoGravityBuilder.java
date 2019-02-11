package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws> {
	
	public NoGravityBuilder(){
		
	}
	
	public GravityLaws createTheInstance(JSONObject jsonobject) throws IllegalArgumentException{
		NoGravity ng = null;
		
		if(jsonobject.get("type").equals("ng")){
			ng = new NoGravity();
		}
		return ng;
	}
	
	public JSONObject getBuilderInfo(){
		JSONObject info= new JSONObject();
		info.accumulate("type", "ng");
		info.accumulate("data", null);
		info.accumulate("desc", "No Gravity Law");
		return info;
	}
	
}
