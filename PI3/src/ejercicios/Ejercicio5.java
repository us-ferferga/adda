package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio5 {
	private static Map<Paridad, List<Integer>> CrearMapa() {
		Map<Paridad, List<Integer>> map = new HashMap<Paridad, List<Integer>>();
		map.put(Paridad.Impar, new ArrayList<Integer>());
		map.put(Paridad.Par, new ArrayList<Integer>());
		
		return map;
	}
	
	private static void LimpiarMapa(Map<Paridad, List<Integer>> map) {
		if (map.get(Paridad.Par).size() == 0) {
			map.remove(Paridad.Par);
		}
		
		if (map.get(Paridad.Impar).size() == 0) {
			map.remove(Paridad.Impar);
		}
	}

	public static Map<Paridad, List<Integer>> recursiva(BinaryTree<Integer> arbol) {
		Map<Paridad, List<Integer>> map = CrearMapa();
		recursiva_interna(arbol, map);
		LimpiarMapa(map);

		return map;
	}
	
	private static Map<Paridad, List<Integer>> recursiva_interna(BinaryTree<Integer> arbol, Map<Paridad, List<Integer>> m) {
		switch(arbol.getType()) {
			case Binary:
				Integer val = arbol.getLabel();
				if (!arbol.getLeft().isEmpty() &&
						!arbol.getRight().isEmpty() &&
						val > arbol.getLeft().getLabel() &&
						val < arbol.getRight().getLabel()
					) {
					if (val%2 == 0) {
						m.get(Paridad.Par).add(val);
					} else {
						m.get(Paridad.Impar).add(val);
					}
				}
				
				recursiva_interna(arbol.getLeft(), m);
				recursiva_interna(arbol.getRight(), m);
				break;
			default:
				break;
		}

		return m;
	}
	
	public static Map<Paridad, List<Integer>> iterativa_imperativa(BinaryTree<Integer> arbol) {
		Map<Paridad, List<Integer>> map = CrearMapa();

		for (BinaryTree<Integer> nodos: arbol) {
			if (nodos.isBinary()) {
				Integer val = nodos.getLabel();
				if (!nodos.getLeft().isEmpty() &&
						!nodos.getRight().isEmpty() &&
						val > nodos.getLeft().getLabel() &&
						val < nodos.getRight().getLabel()
					) {
					if (val%2 == 0) {
						map.get(Paridad.Par).add(val);
					} else {
						map.get(Paridad.Impar).add(val);
					}
				}
			}
		}
		
		LimpiarMapa(map);
		
		return map;
	}
}
