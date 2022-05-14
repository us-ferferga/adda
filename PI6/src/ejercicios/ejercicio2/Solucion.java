package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.GraphPath;

public class Solucion {
	private List<Candidato> seleccionados = new ArrayList<>();

	public Solucion(GraphPath<Vertice, Arista> gp) {
		this(gp.getEdgeList().stream().map(e -> e.action()).toList());		
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

		finalToString += String.format("Sueldos totales: %.1f\nValores totales: %.1f\n", gasto,
				valoracionTotal);
		return finalToString;
	}
}
