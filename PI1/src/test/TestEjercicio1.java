package test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import ejercicios.Ejercicio1;
import util.Helper;

public class TestEjercicio1 {

	public static void PrintWelcome() {
		System.out.println("##################################################");
		System.out.println("#                   Ejercicio 1                  #");
		System.out.println("#         ficheros/PI1E1_DatosEntrada.txt        #");
		System.out.println("##################################################");
		System.out.println("");
	}

	public static void main(String[] args) {
		PrintWelcome();

		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI1E1_DatosEntrada.txt");

		Predicate<String> contieneVocales = s -> s.contains("a") || s.contains("e") || s.contains("o")
				|| s.contains("A") || s.contains("E") || s.contains("O");

		Predicate<Integer> esPar = i -> i % 2 == 0;

		Function<String, Integer> longitudDeCadena = s -> s.length();

		for (String s : lineasFichero) {
			if (s.contains("// ")) {
				continue;
			}
			List<String> splittedWords = Arrays.asList(s.split(","));

			System.out.println("Entrada: " + splittedWords.toString());
			System.out.println("1. Iterativa (while): "
					+ Ejercicio1.iterativa(splittedWords, contieneVocales, esPar, longitudDeCadena));
			System.out.println("2. Recursiva final: "
					+ Ejercicio1.recursiva(splittedWords, contieneVocales, esPar, longitudDeCadena));
			System.out.println(
					"3. Funcional: " + Ejercicio1.funcional(splittedWords, contieneVocales, esPar, longitudDeCadena));
			System.out.println("\n");
		}
	}
}
