package ejercicios.ejercicio3;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Heuristica {
	public static Double heuristic(Vertice actual, Predicate<Vertice> goal, Vertice last) {
		Integer n = Modelo.lenProductos();
		Double h = 0.;

		if (actual.index() >= n) {
			return h;
		} else {
			h = IntStream.range(actual.index(), n).boxed()
					.mapToDouble(p -> Modelo.productos.get(p).precio() * actual.getRatioUds(p)).sum();
		}
		return h;
	}
}
