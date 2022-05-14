package ejercicios.ejercicio3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Arista(Vertice source, Vertice target, Integer action, Double weight)
		implements SimpleEdgeAction<Vertice, Integer> {

	public static Arista of(Vertice c1, Vertice c2, Integer action) {
		Double w = Double.valueOf(action * Modelo.productos.get(c1.index()).precio());

		return new Arista(c1, c2, action, w);
	}

}
