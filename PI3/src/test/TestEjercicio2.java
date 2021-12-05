package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ejercicios.Ejercicio2;
import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;
import util.Helper;

public class TestEjercicio2 {
	private static void imprimirSalida(BinaryTree<Integer> arbol, Set<Integer> resultado) {
		String out = String.format("%s: %s", arbol, resultado);
		System.out.println(out);
	}

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI3E2_DatosEntrada.txt");
		
		List<Pair<Integer, BinaryTree<Integer>>> datos = new ArrayList<Pair<Integer, BinaryTree<Integer>>>();

		for (String s : lineasFichero) {
			var splitted = s.split("#");
			Integer num = Integer.parseInt(splitted[1]);
			BinaryTree<Integer> arbol = BinaryTree.parse(splitted[0], i -> Integer.parseInt(i));
			datos.add(Pair.of(num, arbol));
		}

		System.out.println("SOLUCIÓN RECURSIVA:\n");
		for (Pair<Integer, BinaryTree<Integer>> d : datos) {
			Integer num = d.first();
			BinaryTree<Integer> arbol = d.second();
			imprimirSalida(arbol, Ejercicio2.recursiva(arbol, num));
		}
	}
}
