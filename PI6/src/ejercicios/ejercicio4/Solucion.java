package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.GraphPath;

public class Solucion {
	private Map<Contenedor, List<Elemento>> map = new TreeMap<Contenedor, List<Elemento>>();
	private Integer size = 0;
	
	public Solucion(GraphPath<Vertice, Arista> gp) {
		this(gp.getEdgeList().stream().map(e -> e.action()).toList());
	}

	public Solucion(List<Integer> ls) {

		Contenedor contenedor = null;
		Elemento elemento = null;

		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) < Modelo.lenContenedores()) {
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
		String finalToString = "Reparto obtenido:\n";

		for (Map.Entry<Contenedor, List<Elemento>> par : map.entrySet()) {
			finalToString += par.getKey() + ": " + par.getValue() + "\n";
		}
		
		finalToString += "Numero de contenedores " + map.keySet().size() + "\n";

		return finalToString;
	}
}
