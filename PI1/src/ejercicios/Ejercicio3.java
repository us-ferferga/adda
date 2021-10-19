package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.Par;

public class Ejercicio3 {
	// Método provisto en el enunciado
	public static String funcional(Integer a, Integer limit) {
		return Stream
				.iterate(Par.of(0, a), t -> t.v1() < limit,
						t -> Par.of(t.v1() + 1, t.v1() % 3 == 1 ? t.v2() : t.v1() + t.v2()))
				.collect(Collectors.toList()).toString();
	}

	public static String recursiva(Integer a, Integer limit) {
		return "";
	}

	public static String iterativa(Integer a, Integer limit) {
		List<Par<Integer, Integer>> lista = new ArrayList<Par<Integer, Integer>>();
		Integer loop = 0;

		while (loop < limit) {
			if (loop == 0) {
				lista.add(Par.of(loop, a));
			} else {
				var t = lista.get(lista.size() - 1);
				var p = Par.of(t.v1() + 1, t.v1() % 3 == 1 ? t.v2() : t.v1() + t.v2());
				lista.add(p);
			}

			loop++;
		}
		return lista.toString();
	}
}
