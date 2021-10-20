package ejercicios;

import java.util.stream.Stream;

public class Ejercicio4 {
	private static Double cubo(Double n) {
		return n * n * n;
	}

	private static Double error_newton(Double inicial, Double ultima) {
		return Math.abs(inicial - ultima) / Math.abs(ultima);
	}

	private static void ComprobarVariables(Double error) {
		if (!(error < 1 && error > 0)) {
			throw new IllegalArgumentException("El error debe estar entre 0 y 1");
		}
	}

	public static Double funcional(Double number, Double error) {
		ComprobarVariables(error);
		return Stream.iterate();
	}

	private static Double recursiva_interna(Double x, Double a, Double b, Double number, Double error) {
		if (Math.abs(number - cubo(b)) > error) {

			if (cubo(b) > number) {
				a = b;
			} else {
				x = b;
			}
			return recursiva_interna(x, a, (x + a) / 2, number, error);
		}
		return x;
	}

	public static Double recursiva(Double number, Double error) {
		ComprobarVariables(error);

		Double x = 0.0;
		Double a = number;
		Double b = a / 2;

		return recursiva_interna(x, a, b, number, error);
	}

	public static Double iterativa(Double number, Double error) {
		ComprobarVariables(error);

		Double x = 0.0;
		Double a = number;
		Double b = a / 2;

		// Aplicamos método de Bisección
		while (Math.abs(number - cubo(b)) > error) {
			if (cubo(b) > number) {
				a = b;
			} else {
				x = b;
			}
			b = (x + a) / 2;
		}
		return x;
	}
}
