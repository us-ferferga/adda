package ejercicios.ejercicio1;

import java.util.function.Predicate;

public class Heuristica {
	public static Double heuristic(Vertice actual, Predicate<Vertice> obj, Vertice last) {
		Double r;
		Integer n = Modelo.lenFicheros();

		if (actual.index() == n) {
			r = 0.;
		} else {
			r = Double.valueOf(n - actual.index());
		}

		return r;

	}
}