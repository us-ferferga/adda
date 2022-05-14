package ejercicios.ejercicio3;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.GraphPath;

public class Solucion {
	private Map<Producto, Integer> map = new TreeMap<>();

	public Solucion(GraphPath<Vertice, Arista> gp) {
		this(gp.getEdgeList().stream().map(e -> e.action()).toList());
	}

	public Solucion(List<Integer> ls) {
		Producto producto = null;

		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				Integer e = i;
				producto = Modelo.productos.stream().filter(p -> p.id().equals(e + 1)).findFirst().get();
				map.put(producto, ls.get(i));
			}
		}
	}

	@Override
	public String toString() {
		String finalToString = "";
		String productsString = "";
		Double beneficio = 0.;

		for (Map.Entry<Producto, Integer> par : map.entrySet()) {
			productsString += par.getKey() + ": " + par.getValue() + " unidades" + "\n";
			Integer uds = par.getValue();
			beneficio += uds * par.getKey().precio();
		}

		finalToString += "Precio Total: " + String.format("%.1f", beneficio);
		finalToString += "\nProductos Seleccionados:\n" + productsString;
		return finalToString;
	}
}
