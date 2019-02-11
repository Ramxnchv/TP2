package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Builder<T> {
	
	public Builder(){
		
	}
	
	//Parsea el  objeto JSON y comprueba si se corresponde con el builder
	public abstract T createTheInstance(JSONObject jsonobject) throws IllegalArgumentException;
	
	//Crea una plantilla JSONObject con valores por defecto para todas las keys
	public abstract JSONObject getBuilderInfo();
	
	//Convierte un array de JSON a un array de Double para poder parsear los vectores
	public double[] jsonArrayToDoubleArray(JSONArray jsonarray){
		double[] vector = new double[jsonarray.length()];
		for(int i=0;i<jsonarray.length()-1;i++){
			vector[i]=jsonarray.getDouble(i);
		}
		return vector;
	}
	
}
