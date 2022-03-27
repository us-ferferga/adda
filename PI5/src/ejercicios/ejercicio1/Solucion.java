package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import us.lsi.gurobi.GurobiSolution;

public class Solucion {

	private Map<Memoria, List<Fichero>> map = new TreeMap<Memoria, List<Fichero>>();
	private Integer size = 0;

	public Solucion(GurobiSolution gs) {
		Fichero fichero = null;
		Memoria memoria = null;

		for (Map.Entry<String, Double> entry : gs.values.entrySet()) {
			if (entry.getValue() > 0 && entry.getKey().startsWith("x")) {
				// x_ficheroId_memId
				Integer ficheroId = Integer.valueOf(entry.getKey().split("_")[1]) + 1;
				Integer memId = Integer.parseInt(entry.getKey().split("_")[2]) + 1;

				fichero = Modelo.ficheros.stream().filter(f -> f.id().equals(ficheroId)).findFirst().get();
				memoria = Modelo.memorias.stream().filter(m -> m.id().equals(memId)).findFirst().get();

				if (map.containsKey(memoria)) {
					map.get(memoria).add(fichero);
					size++;
				} else {
					List<Fichero> ls = new ArrayList<>();
					ls.add(fichero);
					map.put(memoria, ls);
					size++;
				}
			}
		}
	}

	public Solucion(List<Integer> ls) {
		Memoria memoria = null;
		Fichero fichero = null;

		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				Integer idMemoria = ls.get(i);
				memoria = Modelo.memorias.stream().filter(m -> m.id().equals(idMemoria)).findFirst().get();
				fichero = Modelo.ficheros.get(i);

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
		String finalToString = "Reparto obtenido:\n";

		for (Map.Entry<Memoria, List<Fichero>> par : map.entrySet()) {
			Collections.sort(par.getValue());
			finalToString = finalToString + par.getKey() + ": " + par.getValue() + "\n";
		}

		finalToString += "\nNúmero de archivos: " + size.toString();
		return finalToString;
	}
}
