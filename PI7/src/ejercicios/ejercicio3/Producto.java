package ejercicios.ejercicio3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Producto(Integer id, Integer precio, Map<Componente, Integer> numComp, Integer maxUds)
		implements Comparable<Producto> {
	public static Producto create(String linea, List<Componente> componentes) {
		String[] l1 = linea.split("->");
		Integer id = Integer.valueOf(l1[0].trim().replace("P", ""));

		String[] l2 = l1[1].split(";");
		Integer precio = Integer.parseInt(l2[0].replace("precio=", "").trim());

		Map<Componente, Integer> numComp = new HashMap<>();
		String[] parseaMap = l2[1].replace("comp=", "").replace("(", "").replace(")", "").split(",");

		for (String comp : parseaMap) {
			Integer idComp = Integer.valueOf(comp.substring(0, comp.indexOf(":")).replace("C", "").trim());
			Componente c = componentes.stream().filter(componente -> componente.id().equals(idComp)).findFirst().get();

			Integer num = Integer.valueOf(comp.substring(comp.indexOf(":") + 1, comp.length()).trim());
			numComp.put(c, num);
		}

		Integer maxUds = Integer.valueOf(l2[2].replace("max_u=", "").trim());

		return new Producto(id, precio, numComp, maxUds);
	}

	@Override
	public String toString() {
		String prefix = this.id < 10 ? "P0" : "P";

		return prefix + id().toString();
	}

	@Override
	public int compareTo(Producto p) {
		if (this.id > p.id) {
			return 1;
		} else if (this.id == p.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
