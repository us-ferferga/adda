package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solucion {
	private Map<Memoria, List<Fichero>> map = new TreeMap<Memoria, List<Fichero>>();
	private Integer size = 0;

	public Integer getSize() {
		return size;
	}

	public Solucion(List<Integer> ls) {
		for (int i = 0; i < ls.size(); i++) {
			Integer a = ls.get(i);
			if (a < Modelo.lenMemorias()) {
				Memoria memoria = Modelo.getMemorias().stream().filter(m -> m.id() == a + 1).findFirst().get();
				Fichero fichero = Modelo.getFichero(i);

				if (map.containsKey(memoria)) {
					map.get(memoria).add(fichero);
					size++;
				} else {
					List<Fichero> lsAux = new ArrayList<>();
					lsAux.add(fichero);
					map.put(memoria, lsAux);
					size++;
				}
			}
		}
	}

	@Override
	public String toString() {
		String finalString = "Reparto obtenido:\n";

		for (Map.Entry<Memoria, List<Fichero>> par : map.entrySet()) {
			Collections.sort(par.getValue());

			String arrayString = "[";
			for (Fichero f : par.getValue()) {
				arrayString += String.format("%s: %d, ", f, f.size());
			}
			arrayString = arrayString.substring(0, arrayString.length() - 2) + "]";

			finalString += String.format("%s : %s; %s: %s", par.getKey(), par.getKey().storage(),
					par.getKey().maxSize(), arrayString) + "\n";
		}

		finalString += String.format("Se almacenaron %d archivos.\n", size);
		return finalString;
	}
}
