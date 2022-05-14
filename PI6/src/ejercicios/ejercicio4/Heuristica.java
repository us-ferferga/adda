package ejercicios.ejercicio4;

import java.util.function.Predicate;

public class Heuristica {
	public static Double heuristic(Vertice actual, Predicate<Vertice> goal, Vertice last) {
		return actual.weight();
	}
}
