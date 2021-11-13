package test;

import java.util.List;

import ejercicios.Ejercicio1;
import util.Helper;

public class TestEjercicio1 {

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI2E1_DatosEntrada.txt");

		for (String s : lineasFichero) {
			String[] splitted = s.split(",");
			Integer a = Integer.parseInt(splitted[0]);
			Integer b = Integer.parseInt(splitted[1]);
			Integer c = Integer.parseInt(splitted[2]);
			
			System.out.println(String.format("(a, b, c) = (%d, %d, %d)", a, b,c));
			System.out.println("Sol. Rec. No Final: " + Ejercicio1.recursiva_no_final(a, b, c));
			System.out.println("Sol. Iterativa:     " + Ejercicio1.iterativa(a, b, c));
			System.out.println("Sol. Rec. Final:    " + Ejercicio1.recursiva_final(a, b, c));
			System.out.println("Sol. Funcional:     " + Ejercicio1.funcional(a, b, c));
			System.out.println("_____________________________________________________________");
		}
	}
}
