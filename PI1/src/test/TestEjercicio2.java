package test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ejercicios.Ejercicio2;
import util.Helper;

public class TestEjercicio2 {
	
	public static void PrintWelcome() {
		System.out.println("##################################################");
		System.out.println("#                   Ejercicio 2                  #");
		System.out.println("#         ficheros/PI1E2_DatosEntrada1.txt       #");
		System.out.println("##################################################");
		System.out.println("");
	}
	
	public static void PrintWelcome2() {
		System.out.println("##################################################");
		System.out.println("#                   Ejercicio 2                  #");
		System.out.println("#         ficheros/PI1E2_DatosEntrada2.txt       #");
		System.out.println("##################################################");
		System.out.println("");
	}

	public static void main(String[] args) {
		PrintWelcome();

		List<String> lineasFichero1 = Helper.leerLineas(
				System.getProperty("user.dir") + "\\src\\ficheros\\PI1E2_DatosEntrada1.txt"
		);
		List<String> lineasFichero2 = Helper.leerLineas(
				System.getProperty("user.dir") + "\\src\\ficheros\\PI1E2_DatosEntrada2.txt"
		);
		
		List<List<String>> elementosFichero1 = lineasFichero1
				.stream()
				.map(s -> Arrays.asList(s.split(",")))
				.collect(Collectors.toList());
		
		List<List<String>> elementosFichero2 = lineasFichero2
				.stream()
				.map(s -> Arrays.asList(s.split(",")))
				.collect(Collectors.toList());

		System.out.println("Entrada: " + elementosFichero1);
		System.out.println("1. Iterativa (while): " + 
				Ejercicio2.iterativa(elementosFichero1));
		System.out.println("2. Recursiva final: " + 
				Ejercicio2.recursiva(elementosFichero1));
		System.out.println("3. Funcional: " + 
				Ejercicio2.funcional(elementosFichero1));
		System.out.println("\n");
		
		PrintWelcome2();

		System.out.println("Entrada: " + elementosFichero2);
		System.out.println("1. Iterativa (while): " + 
				Ejercicio2.iterativa(elementosFichero2));
		System.out.println("2. Recursiva final: " + 
				Ejercicio2.recursiva(elementosFichero2));
		System.out.println("3. Funcional: " + 
				Ejercicio2.funcional(elementosFichero2));
		System.out.println("\n");
	}
}
