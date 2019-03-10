package simulator.model;

import java.util.List;



public class FallingToCenterGravity implements GravityLaws{

	private final double g= 9.81;

	public void apply(List<Body> bodies) {
		for(Body i:bodies){
			i.setAcceleration(i.getPosition().direction().scale(-g));
		}
	}
}
