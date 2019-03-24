package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws{

	private final double constanteGravitacional=6.674E-11;

	public void apply(List<Body> bodies) {

		//fuerzaJEscalar calcula la fuerza que ejerce un cuerpo j sobre otro i
		//fuerzaJVectorial calcula esa misma fuerza multiplicada por la distancia entre ambos vectores para convertirla en vector
		//fuerzaTotal acumula el valor de cada fuerza calculada para simular el sumatorio

		Vector fuerzaJVectorial,fuerzaTotal;
		double fuerzaJEscalar=0.0;

		for(Body i:bodies){
			if(i.getMass()==0.0){
				i.setAcceleration(new Vector(i.getAcceleration().dim()));
				i.setVelocity(new Vector(i.getVelocity().dim()));
			}
			else{

				fuerzaTotal= new Vector(i.getAcceleration().dim());

				for(Body j:bodies){
					if(j!=i){
						fuerzaJEscalar=constanteGravitacional*((i.getMass()*j.getMass())/(Math.pow(j.getPosition().distanceTo(i.getPosition()),2)));
						fuerzaJVectorial= (j.getPosition().minus(i.getPosition())).direction().scale(fuerzaJEscalar);
						fuerzaTotal=fuerzaTotal.plus(fuerzaJVectorial);
					}
				}

				i.setAcceleration(fuerzaTotal.scale(1/i.getMass()));

			}
		}
	}
}
