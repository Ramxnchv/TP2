package simulator.factories;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;


public class BuilderBasedFactory<T> implements Factory<T> {
	
	private ArrayList<Builder<T>> availableBuilders;

	public BuilderBasedFactory (ArrayList<Builder<T>> builders){
		this.availableBuilders=builders;
	}
	
	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		T objeto=null;
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
		}
		
		return objeto;
	}

	@Override
	public ArrayList<JSONObject> getInfo() {
		
		ArrayList<JSONObject> infolist = new ArrayList<JSONObject>();
		for(Builder<T> i:availableBuilders){
			infolist.add(i.getBuilderInfo());
		}
		
		return infolist;
	}

}
