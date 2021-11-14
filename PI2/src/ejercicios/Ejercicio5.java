package ejercicios;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.Trio;
import us.lsi.math.Math2;

public class Ejercicio5 {
	public static Integer recursiva_sin_memoria(Trio<Integer, Integer, Integer> trio) {
		Integer a = trio.first();
		Integer b = trio.second();
		Integer c = trio.third();
		Trio<Integer, Integer, Integer> tr1 = null;
		Trio<Integer, Integer, Integer> tr2 = null;

		if (a < 3 || b < 3 || c < 3) {
			return  a+b*b+2*c;
		} else if (a % b==0) {
			tr1 = Trio.of(a - 1, b / 2, c / 2);
			tr2 = Trio.of(a - 3, b / 3, c / 3);

			return recursiva_sin_memoria(tr1) + recursiva_sin_memoria(tr2);
		} else {
			tr1 = Trio.of(a / 3, b - 3, c - 3);
			tr2 = Trio.of(a / 2, b - 2, c - 2);

			return recursiva_sin_memoria(tr1) + recursiva_sin_memoria(tr2);
		}
	}

	private static Integer recursiva_interna(Trio<Integer, Integer, Integer> trio, Map<Trio<Integer, Integer, Integer>, Integer> mp) {
		Integer a = trio.first();
		Integer b = trio.second();
		Integer c = trio.third();
		Trio<Integer, Integer, Integer> tr1 = null;
		Trio<Integer, Integer, Integer> tr2 = null;

		if (!mp.containsKey(trio)) {
			if (a < 3 || b < 3 || c < 3) {
				mp.put(trio, a+b*b+2*c);
			} else if (a % b==0) {
				tr1 = Trio.of(a - 1, b / 2, c / 2);
				tr2 = Trio.of(a - 3, b / 3, c / 3);

				mp.put(trio, recursiva_interna(tr1, mp) + recursiva_interna(tr2, mp));
			} else {
				tr1 = Trio.of(a / 3, b - 3, c - 3);
				tr2 = Trio.of(a / 2, b - 2, c - 2);

				mp.put(trio, recursiva_interna(tr1, mp) + recursiva_interna(tr2, mp));
			}
		}

		return mp.get(trio);
	}

	public static Integer recursiva_con_memoria(Trio<Integer, Integer, Integer> trio) {
		Map<Trio<Integer, Integer, Integer>, Integer> memoria = new HashMap<>();
		return recursiva_interna(trio, memoria);
	}

	public static Integer iterativa(Trio<Integer, Integer, Integer> trio) {
		Map<Trio<Integer, Integer, Integer>, Integer> mp = new HashMap<Trio<Integer, Integer, Integer>, Integer>();
		Trio<Integer, Integer, Integer> num = null;
		Trio<Integer, Integer, Integer> num1 = null;
		Trio<Integer, Integer, Integer> num2 = null;
		Integer a = trio.first();
		Integer b = trio.second();
		Integer c = trio.third();
		Integer _a = 0;

		while (_a <= a) {
			Integer _b = 0;
			while (_b <= b) {
				Integer _c = 0;
				while (_c <= c) {
					num = new Trio<Integer, Integer, Integer>(_a,_b,_c);
					num1 = null;
					num2 = null;

					if (_a < 3 || _b < 3 || _c < 3) {
						mp.put(num, _a+_b*_b+2*_c);
					} else if (_a % _b==0) {
						num1 = Trio.of(_a - 1, _b / 2, _c / 2);
						num2 = Trio.of(_a - 3, _b / 3, _c / 3);
						
						mp.put(num, mp.get(num1) + mp.get(num2));
					} else {
						num1 = Trio.of(_a / 3, _b - 3, _c - 3);
						num2 = Trio.of(_a / 2, _b - 2, _c - 2);
						
						mp.put(num, mp.get(num1) + mp.get(num2));
					}
					_c++;
				}
				_b++;
			}
			_a++;
		}

		num = new Trio<Integer, Integer, Integer>(a,b,c);
		return mp.get(num);
	}
}
