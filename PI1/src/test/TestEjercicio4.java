package test;

import java.util.Arrays;
import java.util.List;

import ejercicios.Ejercicio4;
import util.Helper;

public class TestEjercicio4 {

	public static void PrintWelcome() {
		System.out.println("##################################################");
		System.out.println("#                   Ejercicio 4                  #");
		System.out.println("#         ficheros/PI1E4_DatosEntrada.txt        #");
		System.out.println("##################################################");
		System.out.println("");
	}

	public static void main(String[] args) {
		PrintWelcome();

		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI1E4_DatosEntrada.txt");

		for (String s : lineasFichero) {
			List<String> splittedString = Arrays.asList(s.split(","));
			Double number = Double.parseDouble(splittedString.get(0));
			Double error = Double.parseDouble(splittedString.get(1));

			System.out.println("Entrada: " + s);
			System.out.println("1. Iterativa (while): " + Ejercicio4.iterativa(number, error));
			System.out.println("2. Recursiva final: " + Ejercicio4.recursiva(number, error));
			System.out.println("3. Funcional: " + Ejercicio4.funcional(number, error));
			System.out.println("\n");
		}
	}

}
