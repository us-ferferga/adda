package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.List;

public record Candidato(Integer id, List<String> cualidades, Double sueldoMin, Integer valoracion,
		List<Integer> incompatibilidades) implements Comparable<Candidato> {
	public static Candidato create(String linea) {
		List<String> cualidades = new ArrayList<String>();
		List<Integer> incompatibilidades = new ArrayList<Integer>();

		String[] s = linea.split(":");
		Integer id = Integer.valueOf(s[0].replace("C", ""));
		String[] info = s[1].trim().split(";");

		for (String cualidad : info[0].split(",")) {
			cualidades.add(cualidad.trim());
		}

		Double sueldoMin = Double.valueOf(info[1].trim());
		Integer valoracion = Integer.valueOf(info[2].trim());

		for (String incompatibilidad : info[3].split(",")) {
			incompatibilidades.add(Integer.valueOf(incompatibilidad.replace("C", "").trim()));
		}

		return new Candidato(id, cualidades, sueldoMin, valoracion, incompatibilidades);
	}

	@Override
	public String toString() {
		return String.format("C%d: %s; %s; %s; %s", id(), cualidades(), sueldoMin(), valoracion(),
				incompatibilidades());
	}

	@Override
	public int compareTo(Candidato c) {
		if (this.id > c.id) {
			return 1;
		} else if (this.id == c.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
