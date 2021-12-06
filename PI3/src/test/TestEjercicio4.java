package test;

import java.util.List;

import ejercicios.Ejercicio4;
import us.lsi.tiposrecursivos.Tree;
import util.Helper;

public class TestEjercicio4 {
	private static void imprimirSalida(Tree<String> arbol, List<String> resultado) {
		String out = String.format("%s: %s", arbol, resultado);
		System.out.println(out);
	}

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI3E4_DatosEntrada.txt");

		System.out.println("SOLUCIÓN RECURSIVA\n");
		for (String s : lineasFichero) {
			Tree<String> arbol = Tree.parse(s);
			imprimirSalida(arbol, Ejercicio4.recursiva(arbol));
		}
	}

}
