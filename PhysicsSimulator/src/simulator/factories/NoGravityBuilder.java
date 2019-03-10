package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws> {

	public NoGravityBuilder(){
		super("nlug","Newton’s law of universal gravitation");
	}

	public GravityLaws createTheInstance(JSONObject jsonobject) throws IllegalArgumentException{
		/*NewtonUniversalGravitation nlug = null;

		if(jsonobject.get("type").equals("nlug")){
			nlug = new NewtonUniversalGravitation();
		}*/
		return new NoGravity();
	}

}
