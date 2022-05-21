package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solucion {
	private List<Candidato> seleccionados = new ArrayList<>();

	public Double getValoracionTotal() {
		Double r = 0.;

		try {
			r = Double.valueOf(seleccionados.stream().map(c -> c.valoracion()).reduce((c1, c2) -> c1 + c2).get());
		} catch (Exception e) {
		}

		return r;
	}

	public Solucion(List<Integer> ls) {
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				Integer e = i;
				Candidato candidato = Modelo.getCandidatos().stream().filter(c -> c.id() == e + 1).findFirst().get();
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

		Double gasto = seleccionados.stream().mapToDouble(c -> c.sueldoMin()).sum();

		finalToString += String.format("Sueldos totales: %.1f\nValores totales: %.1f\n", gasto, getValoracionTotal());
		return finalToString;
	}
}
