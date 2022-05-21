package test;

import java.util.List;

import ejercicios.ejercicio4.Vertice;
import ejercicios.ejercicio4.ProgramacionDinamica;
import ejercicios.ejercicio4.Solucion;
import util.Helper;

public class TestEjercicio4 {

	private static void PD() {
		ProgramacionDinamica.pd(Vertice.capacidadInicial);
		Solucion s = ProgramacionDinamica.solucion();

		if (s != null) {
			System.out.println("#### Algoritmo PD ####");
			System.out.println(s);
		}
		else {
			System.out.println("No hay solucion");
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i < 3; i++) {
			String inputPath = System.getProperty("user.dir") + "\\tests\\PI7Ej4DatosEntrada" + i + ".txt";
			System.out.println("Para el fichero PI7Ej4DatosEntrada" + i + ".txt");
			List<String> lineas = Helper.leerLineas(inputPath);

			Vertice.from(lineas);
			PD();
		}
	}
}