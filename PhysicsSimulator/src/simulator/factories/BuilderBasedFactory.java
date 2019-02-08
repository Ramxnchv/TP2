package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {
	
	private ArrayList<Builder<T>> builders;

	public BuilderBasedFactory (ArrayList<Builder<T>> builders){
		this.builders=builders;
	}
	
	@Override
	public T createInstance(JSONObject info) {
		for(Builder i:builders){
			
		}
		return null;
	}

	@Override
	public List<JSONObject> getInfo() {
		return null;
	}

}
