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
		return new Vector(velocity);
	}

	public void setVelocity(Vector velocity) {
		this.velocity = new Vector(velocity);
	}

	public Vector getAcceleration() {
		return new Vector (acceleration);
	}

	public void setAcceleration(Vector acceleration) {
		this.acceleration = new Vector(acceleration);
	}

	public Vector getPosition() {
		return new  Vector(position);
	}

	public void setPosition(Vector position) {
		this.position = new Vector (position);
	}

	public String getId() {
		return id;
	}

	public double getMass() {
		return mass;
	}
	

	public void move(double t) {
		position = position.plus(velocity.scale(t).plus(acceleration.scale(t*t*0.5)));
		velocity = velocity.plus(acceleration.scale(t));
	}
	
	@Override
	public String toString() {
		return "{  \"id\": " +  "\"" + this.id +"\"" + ", \"mass\": " + this.mass + ", \"pos\": "+ this.position + ", \"vel\": " + this.velocity + ", \"acc\": " + this.acceleration + " }";
	}
	
	
	//Sobreescribimos equals para comparar los cuerpos solo por el atributo "id"
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Body other = (Body) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
