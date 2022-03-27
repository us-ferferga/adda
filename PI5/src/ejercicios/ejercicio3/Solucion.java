package ejercicios.ejercicio3;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import us.lsi.gurobi.GurobiSolution;

public class Solucion {
	private Map<Producto, Integer> map = new TreeMap<>();

	public Solucion(GurobiSolution gs) {
		Map<String, Double> variables = gs.values;
		Producto producto = null;

		for (Map.Entry<String, Double> par : variables.entrySet()) {
			if (par.getValue() > 0 && par.getKey().startsWith("x")) {
				Integer productoId = Integer.valueOf(par.getKey().substring(2)) + 1;
				producto = Modelo.productos.stream().filter(p -> p.id().equals(productoId)).findFirst().get();

				map.put(producto, par.getValue().intValue());
			}
		}
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
		String finalToString = "Productos Seleccionados:\n";
		Double beneficio = 0.;

		for (Map.Entry<Producto, Integer> par : map.entrySet()) {
			finalToString += par.getKey() + ": " + par.getValue() + " unidades" + "\n";
			Integer uds = par.getValue();
			beneficio += uds * par.getKey().precio();
		}
		finalToString += "\nBeneficio: " + String.format("%.1f", beneficio);
		return finalToString;
	}
}
