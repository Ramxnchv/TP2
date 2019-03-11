package simulator.factories;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {

	private List<Builder<T>> builders;
	private List<JSONObject> factoryElements = new ArrayList<JSONObject>();



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



		/*T objeto=null;
		Builder<T> builder= null;
		boolean found=false;
		Iterator<Builder<T>> it=availableBuilders.iterator();

		while(it.hasNext()&&!found){
			builder=it.next();
			objeto=builder.createInstance(info);

			if(objeto!=null){
				found=true;
			}
		}

		if(objeto==null){
			throw new IllegalArgumentException("No se pudo encontrar un constructor adecuado");
		}*/


		return objeto;
	}


	@Override
	public List<JSONObject> getInfo() {
		for(Builder<?> i: this.builders){
			this.factoryElements.add(i.getBuilderInfo());
		}
		return factoryElements;
	}

}
