package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws> {
	
	public FallingToCenterGravityBuilder(){
		
	}
	
	public GravityLaws createTheInstance(JSONObject jsonobject) throws IllegalArgumentException{
		FallingToCenterGravity ftcg = null;
		
		if(jsonobject.get("type").equals("ftgc")){
			ftcg = new FallingToCenterGravity();
		}
		return ftcg;
	}
	
	public JSONObject getBuilderInfo(){
		JSONObject info= new JSONObject();
		info.accumulate("type", "ftcg");
		info.accumulate("data", null);
		info.accumulate("desc", "Falling to Center Gravity");
		return info;
	}
	
}
