package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws> {

	public NoGravityBuilder(){
		super("ng","No Gravity Law");
	}

	public GravityLaws createTheInstance(JSONObject jsonobject) throws IllegalArgumentException{

		return new NoGravity();
	}

}
