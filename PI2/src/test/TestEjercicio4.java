package test;

import java.util.List;

import ejercicios.Ejercicio4;
import util.Helper;

public class TestEjercicio4 {

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI2E4_DatosEntrada.txt");

		for (String s : lineasFichero) {
			Integer len = s.length();
			Integer dato = Integer.valueOf(s.substring(2, len));
			
			System.out.println("Entero de entrada:     " + dato);
			System.out.println("Sol. Rec. sin memoria: " + Ejercicio4.recursiva_sin_memoria(dato));
			System.out.println("Sol. Rec. con memoria: " + Ejercicio4.recursiva_con_memoria(dato));
			System.out.println("Sol. Iterativa:        " + Ejercicio4.iterativa(dato));
			System.out.println("________________________________________________________________");
		}
	}

}
