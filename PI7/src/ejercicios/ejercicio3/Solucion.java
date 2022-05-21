package ejercicios.ejercicio3;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solucion {
	private Map<Producto, Integer> map = new TreeMap<>();

	public Solucion(List<Integer> ls) {
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				Integer e = i;
				Producto producto = Modelo.getProductos().stream().filter(p -> p.id() == e + 1).findFirst().get();
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
