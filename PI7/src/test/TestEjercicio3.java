package test;

import java.util.List;

import ejercicios.ejercicio3.Vertice;
import ejercicios.ejercicio3.Modelo;
import ejercicios.ejercicio3.Solucion;
import ejercicios.ejercicio3.ProgramacionDinamica;
import util.Helper;

public class TestEjercicio3 {

	private static void PD() {
		ProgramacionDinamica.pd(Modelo.getTotalProd(), Modelo.getTotalManual());
		Solucion s = ProgramacionDinamica.solucion();

		if (s != null) {
			System.out.println("#### Algoritmo PD ####");
			System.out.println(s);
		} else {
			System.out.println("No hay solucion");
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i < 3; i++) {
			String inputPath = System.getProperty("user.dir") + "\\tests\\PI7Ej3DatosEntrada" + i + ".txt";
			System.out.println("Para el fichero PI7Ej3DatosEntrada" + i + ".txt");
			List<String> lineas = Helper.leerLineas(inputPath);

			Vertice.from(lineas);
			PD();
		}
	}
}
