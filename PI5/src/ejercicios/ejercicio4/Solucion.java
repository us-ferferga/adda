package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import us.lsi.gurobi.GurobiSolution;

public class Solucion {
	private Map<Contenedor, List<Elemento>> map = new TreeMap<Contenedor, List<Elemento>>();
	private Integer size = 0;

	public Solucion(GurobiSolution gs) {
		Map<String, Double> variables = gs.values;

		Elemento elemento = null;
		Contenedor contenedor = null;

		for (Map.Entry<String, Double> par : variables.entrySet()) {
			if (par.getValue() > 0 && par.getKey().startsWith("x")) {

				Integer elementoId = Integer.valueOf(par.getKey().substring(2, par.getKey().indexOf("_", 2))) + 1;
				elemento = Modelo.elementos.stream().filter(f -> f.id().equals(elementoId)).findFirst().get();

				Integer cont = Integer.parseInt(par.getKey().substring(4).replace("_", "").trim());
				contenedor = Modelo.contenedores.stream().filter(c -> c.id().equals(cont + 1)).findFirst().get();

				if (map.containsKey(contenedor)) {
					map.get(contenedor).add(elemento);
					size++;
				} else {
					List<Elemento> ls = new ArrayList<>();
					ls.add(elemento);
					map.put(contenedor, ls);
					size++;
				}
			}
		}
	}

	public Solucion(List<Integer> ls) {

		Contenedor contenedor = null;
		Elemento elemento = null;

		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) < Modelo.contenedores.size()) {
				Integer idCont = ls.get(i);

				contenedor = Modelo.contenedores.stream().filter(c -> c.id().equals(idCont + 1)).findFirst().get();
				elemento = Modelo.elementos.get(i);
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
		List<String> ls = new ArrayList<>();

		for (Map.Entry<Contenedor, List<Elemento>> par : map.entrySet()) {
			ls.add(par.getKey() + ": " + par.getValue());
		}

		String finalToString = "Reparto obtenido:\n";

		for (int i = 0; i < ls.size(); i++) {
			finalToString = finalToString + ls.get(i) + "\n";
		}

		return finalToString;
	}
}
