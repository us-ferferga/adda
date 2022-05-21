package test;

import java.util.List;
import java.util.Comparator;

import ejercicios.ejercicio1.Vertice;
import ejercicios.ejercicio1.BackTracking;
import ejercicios.ejercicio1.Modelo;
import ejercicios.ejercicio1.Solucion;
import util.Helper;

public class TestEjercicio1 {

	private static void BT() {
		BackTracking.search(Modelo.capacidadesIniciales());
		Solucion s = BackTracking.soluciones.stream().max(Comparator.comparing(x -> x.getSize())).orElse(null);

		if (s != null) {
			System.out.println("#### Algoritmo BT ####");
			System.out.println(s);
		} else {
			System.out.println("No hay solucion");
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i < 3; i++) {
			String inputPath = System.getProperty("user.dir") + "\\tests\\PI7Ej1DatosEntrada" + i + ".txt";
			System.out.println("Para el fichero PI7Ej1DatosEntrada" + i + ".txt");
			List<String> lineas = Helper.leerLineas(inputPath);

			Vertice.from(lineas);
			BT();
		}
	}
}
