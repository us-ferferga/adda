package ejercicios.ejercicio2;

import java.util.function.Predicate;

public class Heuristica {
	public static Double heuristic(Vertice actual, Predicate<Vertice> goal, Vertice last) {
		Double h = 0.;

		if (!actual.getCualidadesRestantes().isEmpty()) {
			for (int i = actual.index(); i < Modelo.candidatos.size(); i++) {
				Integer valor = Modelo.candidatos.get(i).valoracion();
				h += valor * 1.;
			}
		}

		return h;
	}
}
