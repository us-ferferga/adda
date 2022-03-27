package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import us.lsi.gurobi.GurobiSolution;

public class Solucion {

	private List<Candidato> seleccionados = new ArrayList<>();

	public Solucion(GurobiSolution gs) {
		Map<String, Double> variables = gs.values;
		Candidato candidato = null;

		for (Map.Entry<String, Double> par : variables.entrySet()) {
			if (par.getValue() > 0 && par.getKey().startsWith("x")) {
				Integer candidatoId = Integer.valueOf(par.getKey().substring(2)) + 1;
				candidato = Modelo.candidatos.stream().filter(c -> c.id().equals(candidatoId)).findFirst().get();
				seleccionados.add(candidato);
			}
		}
	}

	public Solucion(List<Integer> ls) {
		Candidato candidato = null;

		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				Integer e = i;
				candidato = Modelo.candidatos.stream().filter(c -> c.id().equals(e + 1)).findFirst().get();
				seleccionados.add(candidato);
			}
		}
	}

	@Override
	public String toString() {
		String finalToString = "Candidatos Seleccionados:\n";
		Collections.sort(seleccionados);

		for (Candidato candidato : seleccionados) {
			finalToString += candidato + "\n";
		}

		Double valoracionTotal = Double
				.valueOf(seleccionados.stream().map(c -> c.valoracion()).reduce((c1, c2) -> c1 + c2).get());
		Double gasto = seleccionados.stream().mapToDouble(c -> c.sueldoMin()).sum();
		Double media = valoracionTotal / seleccionados.stream().count();

		finalToString += String.format("\nValoración total: %.1f; Gasto: %.1f; Valoración Media: %.1f", valoracionTotal,
				gasto, media);
		return finalToString;
	}
}
