package test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ejercicios.Ejercicio5;
import ejercicios.Paridad;
import us.lsi.tiposrecursivos.BinaryTree;
import util.Helper;

public class TestEjercicio5 {
	private static void imprimirSalida(BinaryTree<Integer> arbol, Map<Paridad, List<Integer>> resultado) {
		String out = String.format("%s: %s", arbol, resultado);
		System.out.println(out);
	}

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI3E5_DatosEntrada.txt");
		
		// Conversión a árboles
		List<BinaryTree<Integer>> arboles = lineasFichero.stream()
													.map(s -> BinaryTree.parse(s, i -> Integer.parseInt(i)))
													.collect(Collectors.toUnmodifiableList());

		System.out.println("SOLUCIÓN RECURSIVA:\n");
		for (BinaryTree<Integer> arbol : arboles) {
			imprimirSalida(arbol, Ejercicio5.recursiva(arbol));
		}
		
		System.out.println("\nSOLUCIÓN ITERATIVA:\n");
		for (BinaryTree<Integer> arbol : arboles) {
			imprimirSalida(arbol, Ejercicio5.iterativa_imperativa(arbol));
		}
	}

}
