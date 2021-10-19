package test;

import java.util.Arrays;
import java.util.List;

import ejercicios.Ejercicio3;
import util.Helper;

public class TestEjercicio3 {

	public static void PrintWelcome() {
		System.out.println("##################################################");
		System.out.println("#                   Ejercicio 3                  #");
		System.out.println("#         ficheros/PI1E3_DatosEntrada.txt        #");
		System.out.println("##################################################");
		System.out.println("");
	}

	public static void main(String[] args) {
		PrintWelcome();

		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI1E3_DatosEntrada.txt");

		for (String s : lineasFichero) {
			List<String> splittedString = Arrays.asList(s.split(","));
			Integer a = Integer.parseInt(splittedString.get(0));
			Integer limit = Integer.parseInt(splittedString.get(1));

			System.out.println("Entrada: " + s);
			System.out.println("1. Iterativa (while): " + Ejercicio3.iterativa(a, limit));
			System.out.println("2. Recursiva final: " + Ejercicio3.recursiva(a, limit));
			System.out.println("3. Funcional: " + Ejercicio3.funcional(a, limit));
			System.out.println("\n");
		}
	}
}
