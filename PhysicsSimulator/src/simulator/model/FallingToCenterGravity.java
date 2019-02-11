package simulator.model;

import java.util.ArrayList;

import simulator.misc.Vector;


public class FallingToCenterGravity implements GravityLaws{
	
	private final double g= 9.81;
	
	public void apply(ArrayList<Body> bodies) {
		for(Body i:bodies){
			i.setAcceleration(i.getPosition().direction().scale(-g));
		}
	}
}
