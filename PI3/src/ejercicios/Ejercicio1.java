package ejercicios;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import us.lsi.tiposrecursivos.Tree;

public class Ejercicio1 {
	public static <E> Set<E> recursiva(Tree<E> arbol, Predicate<E> p) {
		return recursiva_interna(arbol, p, new HashSet<E>());
	}
	
	private static <E> Set<E> recursiva_interna(Tree<E> arbol, Predicate<E> p, Set<E> set) {
		switch (arbol.getType()) {
			case Leaf:
				E val = arbol.getLabel();
				if (p.test(val)) {
					set.add(val);
				}
				break;
			case Nary:
				for (Tree<E> hijo : arbol.getChildren()) {
					recursiva_interna(hijo, p, set);
				}
				break;
			default:
				break;
		}
		return set;
	}

	// Iterativa funcional
	public static <E> Set<E> iterativa(Tree<E> arbol, Predicate<E> p) {
		return arbol.stream()
					.filter(a -> a.isLeaf() && p.test(a.getLabel()))
					.map(a -> a.getLabel())
					.collect(Collectors.toUnmodifiableSet());
	}
}
