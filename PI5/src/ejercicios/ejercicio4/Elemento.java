package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.List;

public record Elemento(Integer id, Integer size, List<String> tipos) implements Comparable<Elemento> {
	public static Elemento create(String linea) {
		String[] l1 = linea.split(":");
		Integer id = Integer.valueOf(l1[0].trim().replace("E", ""));

		String[] info = l1[1].trim().split(";");
		Integer size = Integer.valueOf(info[0].trim());
		List<String> tipos = new ArrayList<String>();

		for (String tipo : info[1].split(",")) {
			tipos.add(tipo.trim());
		}

		return new Elemento(id, size, tipos);
	}

	@Override
	public String toString() {
		return "E" + id();
	}

	@Override
	public int compareTo(Elemento e) {
		if (this.id > e.id) {
			return 1;
		} else if (this.id == e.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
