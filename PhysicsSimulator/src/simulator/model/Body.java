package simulator.model;
import simulator.misc.Vector;

public class Body {
	protected String id;
	protected Vector velocity;
	protected Vector acceleration;
	protected Vector position;
	protected double mass;
	
	public Body(String id, Vector velocity, Vector acceleration,Vector position, double mass ) {
		this.id=id;
		this.velocity=velocity;
		this.acceleration=acceleration;
		this.position=position;
		this.mass=mass;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public Vector getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public double getMass() {
		return mass;
	}
	
	public void move(double t) {
		
		this.setPosition(this.position.plus(this.velocity.scale(t)).plus(this.acceleration.scale(t*t*0.5)));
		this.setVelocity(this.velocity.plus(this.acceleration.scale(t)));
	}
	
	public String toString() {
		
		return "\"id\": "+this.id+",\"mass\": "+this.mass+",\"pos\": "+this.position+",\"vel\": "+this.velocity+",\"acc\": "+this.acceleration;
	}
	
}
