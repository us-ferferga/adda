package test;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ejercicios.Ejercicio1;
import us.lsi.tiposrecursivos.Tree;
import util.Helper;

public class TestEjercicio1 {
	private static void imprimirSalida(Tree<Integer> arbol, Set<Integer> resultado) {
		String out = String.format("%s: %s", arbol, resultado);
		System.out.println(out);
	}

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI3E1_DatosEntrada.txt");
		
		// Predicados del enunciado
		Predicate<Integer> esPar = x -> x%2 == 0;
		Predicate<Integer> menor5 = x-> x < 5;
		
		// Conversión a árboles
		List<Tree<Integer>> arboles = lineasFichero.stream()
													.map(s -> Tree.parse(s, i -> Integer.parseInt(i)))
													.collect(Collectors.toUnmodifiableList());

		System.out.println("SOLUCIÓN RECURSIVA-PAR:\n");
		for (Tree<Integer> a : arboles) {
			imprimirSalida(a, Ejercicio1.recursiva(a, esPar));
		}
		
		System.out.println("\nSOLUCIÓN RECURSIVA MENOR QUE CINCO:\n");
		for (Tree<Integer> a : arboles) {
			imprimirSalida(a, Ejercicio1.recursiva(a, menor5));
		}
		
		System.out.println("\nSOLUCIÓN ITERATIVA PAR:\n");
		for (Tree<Integer> a : arboles) {
			imprimirSalida(a, Ejercicio1.iterativa(a, esPar));
		}
		
		System.out.println("\nSOLUCIÓN ITERATIVA MENOR QUE CINCO:\n");
		for (Tree<Integer> a : arboles) {
			imprimirSalida(a, Ejercicio1.iterativa(a, menor5));
		}
	}
}
