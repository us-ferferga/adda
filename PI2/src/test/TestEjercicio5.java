package test;

import java.util.Arrays;
import java.util.List;

import ejercicios.Ejercicio5;
import us.lsi.common.Trio;
import util.Helper;

public class TestEjercicio5 {

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI2E5_DatosEntrada.txt");

		for (String s : lineasFichero) {
			List<Integer> in = Arrays.asList(s.split(",")).stream().map(str -> Integer.valueOf(str)).toList();
			Trio<Integer, Integer, Integer> trio = Trio.of(in.get(0), in.get(1), in.get(2));
			
			System.out.println(String.format("(a, b, c) = (%d, %d, %d)", trio.first(), trio.second(), trio.third()));
			System.out.println("Sol. Rec. sin memoria: " + Ejercicio5.recursiva_sin_memoria(trio));
			System.out.println("Sol. Rec. con memoria: " + Ejercicio5.recursiva_con_memoria(trio));
			System.out.println("Sol. Iterativa:        " + Ejercicio5.iterativa(trio));
			System.out.println("________________________________________________________________");
		}
	}

}
