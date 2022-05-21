package ejercicios.ejercicio3;

import java.util.stream.IntStream;

public class Heuristica {
	public static Double heuristic(Vertice actual) {
		Integer n = Modelo.lenProductos();
		Double h = 0.;

		if (actual.index() >= n) {
			return h;
		} else {
			h = IntStream.range(actual.index(), n).boxed().mapToDouble(p -> Modelo.getPrecio(p) * actual.getRatioUds(p))
					.sum();
		}
		return h;
	}

	public static Double dimension(Vertice v, Integer a) {
		return heuristic(v.neighbor(a)) + a * Modelo.getPrecio(v.index());
	}
}
