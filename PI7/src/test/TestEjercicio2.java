package test;

import java.util.Comparator;
import java.util.List;

import ejercicios.ejercicio2.Modelo;
import ejercicios.ejercicio2.Vertice;
import ejercicios.ejercicio2.BackTracking;
import ejercicios.ejercicio2.Solucion;
import util.Helper;

public class TestEjercicio2 {

	private static void BT() {
		BackTracking.search(Modelo.getPresupuesto());
		Solucion s = BackTracking.soluciones.stream().max(Comparator.comparing(x -> x.getValoracionTotal()))
				.orElse(null);

		if (s != null) {
			System.out.println("#### Algoritmo BT ####");
			System.out.println(s);
		} else {
			System.out.println("No hay solucion");
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i < 3; i++) {
			String inputPath = System.getProperty("user.dir") + "\\tests\\PI7Ej2DatosEntrada" + i + ".txt";
			System.out.println("Para el fichero PI7Ej2DatosEntrada" + i + ".txt");
			List<String> lineas = Helper.leerLineas(inputPath);

			Vertice.from(lineas);
			BT();
		}
	}
}
