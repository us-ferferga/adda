package ejercicios.ejercicio5;

import java.util.ArrayList;
import java.util.List;

public class Solucion {
	private List<Ciudad> ciudades = new ArrayList<>();
	private Double kms = 0.;

	public Solucion(List<Integer> ls) {
		ciudades = ls.stream().map(i -> Modelo.graph.getVertex(i)).toList();

		Ciudad c1 = null;
		Ciudad c2 = null;

		for (int i = 0; i < ciudades.size() - 1; i++) {
			c1 = ciudades.get(i);
			c2 = ciudades.get(i + 1);

			if (Modelo.gf.containsEdge(c1, c2)) {
				Carretera carretera = Modelo.gf.getEdge(c1, c2);
				kms += carretera.km();
			}
		}
	}

	@Override
	public String toString() {
		return String.format("%s; Kms: %.1f", ciudades, kms);
	}
}
