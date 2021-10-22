package ejercicios;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Ejercicio4 {
	private static Double cubo(Double n) {
		return Math.pow(n, 3);
	}

	private static void ComprobarVariables(Double error) {
		if (!(error < 1 && error > 0)) {
			throw new IllegalArgumentException("El error debe estar entre 0 y 1");
		}
	}

	public static Double funcional(Double number, Double error) {
		ComprobarVariables(error);
		Double x = 0.0;
		Double a = number;
		Double b = number / 2;

		UnaryOperator<Double[]> iteracion = p -> (cubo(p[2]) > number) ? new Double[] { p[0], p[2], (p[0] + p[1]) / 2 }
				: new Double[] { p[2], p[1], (p[0] + p[1]) / 2 };
		Predicate<Double[]> filtro = p -> Math.abs(number - cubo(p[2])) < error;

		return Stream.iterate(new Double[] { x, a, b }, iteracion).filter(filtro).findFirst().get()[2];

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
