package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector;

public class MassLossingBody extends Body{
	private double lossFactor;
	private double lossFrequency;
	
	//contador de tiempo para reducir masa
	private double c = 0.0;
	
	public MassLossingBody(String id, Vector velocity, Vector acceleration,Vector position, double mass,double lossFactor,double lossFrequency) {
		super(id,velocity,acceleration,position,mass);
		this.lossFactor=lossFactor;
		this.lossFrequency=lossFrequency;
	}
	
	public void move(double t) {
		super.move(t);
		c+=t;
		if(c>=lossFrequency) {
			this.mass=mass*(1-lossFactor);
			c=0.0;
		}
	}
	public String toString() {
		JSONObject b = new JSONObject();
		b.accumulate("id", id);
		b.accumulate("pos", position.toString());
		b.accumulate("vel", velocity.toString());
		b.accumulate("mass", mass);
		b.accumulate("freq", lossFrequency);
		b.accumulate("factor", lossFactor);
		return b.toString();
	}

}
