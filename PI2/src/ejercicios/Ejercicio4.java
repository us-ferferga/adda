package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ejercicio4 {
	public static Long recursiva_sin_memoria(Integer num) {
		
		if (num>2) {
			return 2*recursiva_sin_memoria(num-1)+4*recursiva_sin_memoria(num-2)+6*recursiva_sin_memoria(num-3);
		} else if (num == 2) {
			return Integer.toUnsignedLong(6);
		} else if (num == 1) {
			return Integer.toUnsignedLong(4);
		} else {
			return Integer.toUnsignedLong(2);
		}
	}
	
	private static Long recursiva_interna(Integer num, Map<Integer, Long> mp) {
		if (!mp.containsKey(num)) {
			Long lng = 2*recursiva_interna(num-1, mp)+4*recursiva_interna(num-2, mp)+6*recursiva_interna(num-3, mp);
			mp.put(num, lng);
		}
		
		return mp.get(num);
	}
	
	public static Long recursiva_con_memoria(Integer inicial) {
		Map<Integer,Long> memoria = new HashMap<Integer, Long>();
		
		memoria.put(0, Integer.toUnsignedLong(2));
		memoria.put(1, Integer.toUnsignedLong(4));
		memoria.put(2, Integer.toUnsignedLong(6));
		
		return recursiva_interna(inicial, memoria);
	}

	public static Long iterativa(Integer n) {
		List<Long> ls = new ArrayList<Long>();
		ls.add(Integer.toUnsignedLong(2));
		ls.add(Integer.toUnsignedLong(4));
		ls.add(Integer.toUnsignedLong(6));
		Integer i = 3;

		while(i<=n) {
			Long lng = (2*ls.get(i-1)+4*ls.get(i-2)+6*ls.get(i-3));
			ls.add(lng);
			i++;
		}

		return ls.get(n);
	}
}
