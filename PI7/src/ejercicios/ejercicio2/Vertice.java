package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record Vertice(Integer index, List<Integer> as, Integer presupRestante) {

	public static Set<String> cualidadesACubrir;

	@SuppressWarnings("static-access")
	public static void from(List<String> ls) {
		Vertice.cualidadesACubrir = new Modelo(ls).getCualidadesDeseadas().stream().collect(Collectors.toSet());
	}

	public Set<String> getCualidadesRestantes() {
		return cualidadesACubrir;
	}

	public static Vertice initial() {
		return new Vertice(0, new ArrayList<>(), Modelo.getPresupuesto());
	}

	public static Predicate<Vertice> goal() {
		return v -> v.index() == Modelo.lenCandidatos();
	}

	/**
	 * 1. Candidato escogido. 0: Candidato no escogido
	 */
	public List<Integer> actions() {
		List<Integer> la = new ArrayList<Integer>();

		if (index >= Modelo.lenCandidatos()) {
			return la;
		}

		la.add(0);

		if (Modelo.sueldoMin(index) <= presupRestante) {
			Boolean compatibilidad = true;

			for (int i = 0; i < as.size(); i++) {
				if (!Modelo.esIncompatible(index, as.get(i))) {
					continue;
				} else {
					compatibilidad = false;
					break;
				}
			}

			if (compatibilidad) {
				la.add(1);
			}
		}

		return la;
	}

	public Vertice neighbor(Integer a) {
		List<Integer> as2 = new ArrayList<Integer>(as);
		Set<String> cualidades2 = new HashSet<String>(cualidadesACubrir);
		Integer presup2 = presupRestante;

		if (a == 1) {
			as2.add(index);
			cualidades2.removeAll(Modelo.cualidades(index));
			presup2 = (presup2 - Modelo.sueldoMin(index).intValue());
		}
		return new Vertice(index + 1, as2, presup2);
	}
}
