package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solucion {
	private Map<Contenedor, List<Elemento>> map = new TreeMap<Contenedor, List<Elemento>>();
	private Integer size = 0;

	public Solucion(List<Integer> ls) {
		for (int i = 0; i < ls.size(); i++) {
			Integer a = ls.get(i);
			if (a < Modelo.lenContenedores()) {
				Contenedor contenedor = Modelo.getContenedores().stream().filter(c -> c.id() == a + 1).findFirst()
						.get();
				Elemento elemento = Modelo.getElemento(i);

				if (map.containsKey(contenedor)) {
					map.get(contenedor).add(elemento);
					size++;
				} else {
					List<Elemento> lsAux = new ArrayList<>();
					lsAux.add(elemento);
					map.put(contenedor, lsAux);
					size++;
				}
			}
		}
	}

	@Override
	public String toString() {
		String finalToString = "Reparto obtenido:\n";

		for (Map.Entry<Contenedor, List<Elemento>> par : map.entrySet()) {
			finalToString += par.getKey() + ": " + par.getValue() + "\n";
		}

		finalToString += "Numero de contenedores " + map.keySet().size() + "\n";

		return finalToString;
	}
}
