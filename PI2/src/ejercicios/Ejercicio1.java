package ejercicios;
import java.util.HashMap;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import us.lsi.math.Math2;
import us.lsi.common.Trio;

public class Ejercicio1 {
	private static void ComprobarVariables(Integer a, Integer b, Integer c) {
		if (a < 0 && b < 0 && c < 0) {
			throw new IllegalArgumentException("Los enteros proporcionados no son positivos");
		}
	}
	public static String iterativa(Integer a, Integer b, Integer c) {
		ComprobarVariables(a, b, c);
		Map<Trio<Integer, Integer, Integer>, String> mp = new HashMap<Trio<Integer, Integer, Integer>, String>();
		Trio<Integer, Integer, Integer> num1 = null;
		Trio<Integer, Integer, Integer> num2 = null;
		Integer _a = 0;

		while (_a <= a) {
			Integer _b = 0;
			while (_b <= b) {
				Integer _c = 0;
				while (_c <= c) {
					num1 = new Trio<Integer, Integer, Integer>(_a,_b,_c);
					num2 = null;
					// Primer caso: a < 3 ⋀ b < 3 ⋀ c < 3
					if (_a < 3 && _b < 3 && _c < 3) {
						mp.put(num1, "(" + Integer.toString(_a*_b*_c) + ")");
					// Segundo caso: a < 3 ⋁ b < 3 ⋁ c < 3
					} else if (_a < 3 || _b < 3 || _c < 3) {
						mp.put(num1, "(" + Integer.toString(_a+_b+_c) + ")");
					// Tercer caso: a es par ⋀ b es par ⋀ c es par
					} else if (Math2.esPar(_a) && Math2.esPar(_b) && Math2.esPar(_c)) {
						num2 = new Trio<Integer, Integer, Integer>(_a/2, _b-2, _c/2);
						mp.put(num1, Integer.toString(_a*_b*_c) + mp.get(num2));
					} else {
						num2 = new Trio<Integer, Integer, Integer>(_a/3, _b-3, _c/3);
						mp.put(num1, Integer.toString(_a+_b+_c) + mp.get(num2));
					}
					_c++;
				}
				_b++;
			}
			_a++;
		}

		num2 = new Trio<Integer, Integer, Integer>(a,b,c);
		return mp.get(num2);
	}
	
	private static String recursiva_final_interna(Integer a, Integer b, Integer c, String str) {
		// Primer caso: a < 3 ⋀ b < 3 ⋀ c < 3
		if (a < 3 && b < 3 && c < 3) {
			return "(" + Integer.toString(a*b*c) + ")";
		// Segundo caso: a < 3 ⋁ b < 3 ⋁ c < 3
		} else if (a < 3 || b < 3 || c < 3) {
			return "(" + Integer.toString(a+b+c) + ")";
		// Tercer caso: a es par ⋀ b es par ⋀ c es par
		} else if (Math2.esPar(a) && Math2.esPar(b) && Math2.esPar(c)) {
			return Integer.toString(a*b*c) + recursiva_final_interna(a/2, b-2, c/2, str);
		} else {
			return Integer.toString(a+b+c) + recursiva_final_interna(a/3, b-3, c/3, str);
		}
	}
	
	public static String recursiva_final(Integer a, Integer b, Integer c) {
		ComprobarVariables(a, b, c);
		String str = "";
		return recursiva_final_interna(a, b, c, str);
	}
	
	public static String recursiva_no_final(Integer a, Integer b, Integer c) {
		ComprobarVariables(a, b, c);
		// Primer caso: a < 3 ⋀ b < 3 ⋀ c < 3
		if (a < 3 && b < 3 && c < 3) {
			return "(" + Integer.toString(a*b*c) + ")";
		// Segundo caso: a < 3 ⋁ b < 3 ⋁ c < 3
		} else if (a < 3 || b < 3 || c < 3) {
			return "(" + Integer.toString(a+b+c) + ")";
		// Tercer caso: a es par ⋀ b es par ⋀ c es par
		} else if (Math2.esPar(a) && Math2.esPar(b) && Math2.esPar(c)) {
			return Integer.toString(a*b*c) + recursiva_no_final(a/2, b-2, c/2);
		} else {
			return Integer.toString(a+b+c) + recursiva_no_final(a/3, b-3, c/3);
		}
	}
	
	public static String funcional(Integer a, Integer b, Integer c) {
		Map<Trio<Integer, Integer, Integer>, String> mp = new HashMap<Trio<Integer, Integer, Integer>, String>();
		Predicate<Trio<Integer, Integer, Integer>> fin = n -> (n.first() != a || n.second() != b || n.third()-1 != c);
		UnaryOperator<Trio<Integer, Integer, Integer>> iteracion = n -> 
											(n.third() <= c) ? new Trio<Integer, Integer, Integer>(n.first(), n.second(), n.third()+1) :
											(n.second() <= b) ? new Trio<Integer, Integer, Integer>(n.first(), n.second()+1, 0) :
											(n.first() <= a) ? new Trio<Integer, Integer, Integer>(n.first()+1, 0, 0) :
											new Trio<Integer, Integer, Integer>(0, 0, 0);

		Consumer<Trio<Integer, Integer, Integer>> algoritmo = f -> {
			Integer _a = f.first();
			Integer _b = f.second();
			Integer _c = f.third();
			
			Trio<Integer, Integer, Integer> num = null;

			// Primer caso: a < 3 ⋀ b < 3 ⋀ c < 3
			if (_a < 3 && _b < 3 && _c < 3) {
				mp.put(f, "(" + Integer.toString(_a*_b*_c) + ")");
			// Segundo caso: a < 3 ⋁ b < 3 ⋁ c < 3
			} else if (_a < 3 || _b < 3 || _c < 3) {
				mp.put(f, "(" + Integer.toString(_a+_b+_c) + ")");
			// Tercer caso: a es par ⋀ b es par ⋀ c es par
			} else if (Math2.esPar(_a) && Math2.esPar(_b) && Math2.esPar(_c)) {
				num = new Trio<Integer, Integer, Integer>(_a/2, _b-2, _c/2);
				mp.put(f, Integer.toString(_a*_b*_c) + mp.get(num));
			} else {
				num = new Trio<Integer, Integer, Integer>(_a/3, _b-3, _c/3);
				mp.put(f, Integer.toString(_a+_b+_c) + mp.get(num));
			}
		};
		
		Stream.iterate(new Trio<Integer, Integer, Integer>(0, 0, 0), fin, iteracion).forEach(algoritmo);
		return mp.get(new Trio<Integer, Integer, Integer>(a, b, c));
	}
}
