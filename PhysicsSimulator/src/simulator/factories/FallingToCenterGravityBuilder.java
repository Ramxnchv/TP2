package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws> {

	public FallingToCenterGravityBuilder(){
		super("ftcg","Falling to Center Gravity");
	}

	public GravityLaws createTheInstance(JSONObject jsonobject) throws IllegalArgumentException{
		/*FallingToCenterGravity ftcg = null;

		if(jsonobject.get("type").equals("ftgc")){
			ftcg = new FallingToCenterGravity();
		}*/
		return new FallingToCenterGravity();
	}

}
