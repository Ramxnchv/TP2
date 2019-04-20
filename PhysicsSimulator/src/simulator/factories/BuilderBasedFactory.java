package simulator.factories;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {

	private List<Builder<T>> builders;


	public BuilderBasedFactory (List<Builder<T>> builders){

		this.builders = new ArrayList<> (builders);

	}

	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException {

		T objeto = null;
		for(Builder<T> b: builders) {
			objeto = b.createInstance(info);
			if(objeto != null) {
				break;
			} 
		}
		
		if(objeto==null) {
			throw new IllegalArgumentException("No se pudo encontrar un constructor adecuado");
		}

		return objeto;
	}


	@Override
	public List<JSONObject> getInfo() {
		List<JSONObject> factoryElements= new ArrayList<JSONObject>();
		for(Builder<?> i: this.builders){
			factoryElements.add(i.getBuilderInfo());
		}
		return factoryElements;
	}

}
