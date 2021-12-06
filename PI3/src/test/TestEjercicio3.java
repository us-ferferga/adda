package test;

import java.util.List;
import java.util.stream.Collectors;

import ejercicios.Ejercicio3;
import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;
import util.Helper;

public class TestEjercicio3 {

	private static void imprimirSalida(BinaryTree<Integer> arbol, Pair<List<Integer>, Integer> resultado) {
		String out = String.format("%s: %s", arbol, resultado);
		System.out.println(out);
	}

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI3E3_DatosEntrada.txt");
		
		// Conversión a árboles binarios
		List<BinaryTree<Integer>> arboles = lineasFichero.stream()
															.map(s -> BinaryTree.parse(s, i -> Integer.parseInt(i)))
															.collect(Collectors.toUnmodifiableList());

		System.out.println("SOLUCIÓN RECURSIVA:\n");
		for (BinaryTree<Integer> a : arboles) {
			imprimirSalida(a, Ejercicio3.recursiva(a));
		}
	}
}