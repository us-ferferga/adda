package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramacionDinamica {

	public static record Sp(Integer a, Integer weight) implements Comparable<Sp> {
		public int compareTo(Sp sp) {
			return this.weight.compareTo(sp.weight);
		}
	}

	public static Integer maxValue = Integer.MIN_VALUE;
	public static Vertice start;
	public static Map<Vertice, Sp> memory;

	public static Solucion pd(List<Integer> capacidadInicial) {
		ProgramacionDinamica.maxValue = Integer.MIN_VALUE;
		ProgramacionDinamica.start = new Vertice(0, capacidadInicial);
		ProgramacionDinamica.memory = new HashMap<>();
		pd(start, 0, memory);
		return ProgramacionDinamica.solucion();
	}

	public static Sp pd(Vertice vertex, Integer accumulateValue, Map<Vertice, Sp> memory) {
		Sp res;
		Integer n = Modelo.lenElementos();

		if (memory.containsKey(vertex)) {
			res = memory.get(vertex);
		} else if (vertex.index() == n) {
			res = null;
			if (Vertice.constraint().test(vertex)) {
				res = new Sp(null, 0);
				memory.put(vertex, res);

				if (accumulateValue > ProgramacionDinamica.maxValue) {
					ProgramacionDinamica.maxValue = accumulateValue;
				}
			}
			memory.put(vertex, res);
		} else {
			List<Sp> soluciones = new ArrayList<>();
			for (Integer a : vertex.actions()) {
				Integer cota = Heuristica.dimension(vertex, a);

				if (cota < ProgramacionDinamica.maxValue) {
					continue;
				}

				Sp s = pd(vertex.neighbor(a), vertex.weight(), memory);

				if (s != null) {
					Sp sp = new Sp(a, s.weight());
					soluciones.add(sp);
				}
			}

			res = soluciones.stream().max(Comparator.naturalOrder()).orElse(null);

			if (res != null) {
				memory.put(vertex, res);
			}
		}
		return res;
	}

	public static Solucion solucion() {
		List<Integer> alternativas = new ArrayList<>();
		Vertice v = ProgramacionDinamica.start;
		Sp s = ProgramacionDinamica.memory.get(v);

		while (s.a() != null) {
			alternativas.add(s.a());
			v = v.neighbor(s.a());
			s = ProgramacionDinamica.memory.get(v);
		}
		return new Solucion(alternativas);
	}
}
