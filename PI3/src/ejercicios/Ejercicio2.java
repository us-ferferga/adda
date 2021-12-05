package ejercicios;

import java.util.HashSet;
import java.util.Set;

import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio2 {
	public static Set<Integer> recursiva(BinaryTree<Integer> arbol, Integer num) {
		return recursiva_interna(arbol, num, new HashSet<Integer>());
	}
	
	private static Set<Integer> recursiva_interna(BinaryTree<Integer> arbol, Integer num, Set<Integer> set) {
		Integer val = arbol.getLabel();
		switch (arbol.getType()) {
			case Leaf:
				if (val >= num) {
					set.add(val);
				}
				break;
			case Binary:
				if (val >= num) {
					set.add(val);
				}
				set.addAll(recursiva_interna(arbol.getRight(), num, set));
				set.addAll(recursiva_interna(arbol.getLeft(), num, set));
				break;
			default:
				break;
		}
		return set;
	}
}
