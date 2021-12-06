package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio3 {
	public static Pair<List<Integer>, Integer> recursiva(BinaryTree<Integer> arbol) {
		List<Integer> memoria = new ArrayList<Integer>();
		
		return recursiva_interna(arbol, Pair.of(null, null), memoria);
	}
	
	private static Pair<List<Integer>, Integer> recursiva_interna(BinaryTree<Integer> arbol, Pair<List<Integer>, Integer> p, List<Integer> m) {
		Integer val = null;
		switch (arbol.getType()) {
			case Leaf:
				val = arbol.getLabel();
				m.add(val);
				Integer mult = m.stream().reduce(1, (a, b) -> a * b);
				if (p.second() == null || mult > p.second()) {
					p = Pair.of(m, mult);
				}
				break;
			case Binary:
				val = arbol.getLabel();
				m.add(val);
				List<Integer> copia_memoria = new ArrayList<>(m);
				p = recursiva_interna(arbol.getLeft(), p, m);
				// Para continuar con el camino más largo, es preciso que la segunda llamada recursiva utilice
				// la copia de la lista original.
				p = recursiva_interna(arbol.getRight(), p, copia_memoria);
				break;
			default:
				break;
		}
		return p;
	}
}
