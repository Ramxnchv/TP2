package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {
	
	public NewtonUniversalGravitationBuilder(){
		
	}
	
	public GravityLaws createTheInstance(JSONObject jsonobject) throws IllegalArgumentException{
		NewtonUniversalGravitation nlug = null;
		
		if(jsonobject.get("type").equals("nlug")){
			nlug = new NewtonUniversalGravitation();
		}
		return nlug;
	}
	
	public JSONObject getBuilderInfo(){
		JSONObject info= new JSONObject();
		info.accumulate("type", "nlug");
		info.accumulate("data", null);
		info.accumulate("desc", "Newton’s law of universal gravitation");
		return info;
	}
	
}
