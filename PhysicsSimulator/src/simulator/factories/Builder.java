package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Builder<T> {
	
	protected String tag;
	protected String desc;
	
	public Builder(String tag,String desc){
		this.tag=tag;
		this.desc=desc;
	}
	
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		T builder=null;
		if(tag!=null&&tag.equals(info.getString("type"))){
			builder=createTheInstance(info.getJSONObject("data"));
		}
		return builder;
	}
	
	//Parsea el  objeto JSON y comprueba si se corresponde con el builder
	public abstract T createTheInstance(JSONObject jsonobject) throws IllegalArgumentException;
	
	//Crea una plantilla JSONObject con valores por defecto para todas las keys
	public JSONObject getBuilderInfo(){
		JSONObject info= new JSONObject();
		info.accumulate("type", tag);
		info.accumulate("data", createData());
		info.accumulate("desc", desc);
		return info;
	}
	
	//Convierte un array de JSON a un array de Double para poder parsear los vectores
	protected double[] jsonArrayToDoubleArray(JSONArray jsonarray){
		double[] vector = new double[jsonarray.length()];
		for(int i=0;i<jsonarray.length();i++){
			vector[i]=jsonarray.getDouble(i);
		}
		return vector;
	}
	
	public JSONObject createData() {
		return null;
	}
	
}
