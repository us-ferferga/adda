package ejercicios;

import java.util.List;

import us.lsi.common.IntegerSet;

public class Ejercicio3 {
	
	private static void recursiva_interna(List<Integer> rango, List<Integer> conjunto, Integer idx_inferior, Integer idx_superior, IntegerSet set) {
		Integer inicio = rango.get(0);
		Integer fin = rango.get(1);
		
		Integer inicio_conjunto = conjunto.get(idx_inferior);
		Integer fin_conjunto = conjunto.get(idx_superior);
		
		if (inicio_conjunto >= inicio && inicio_conjunto < fin) {
			set.add(inicio_conjunto);
		}
		
		if (fin_conjunto < fin && fin_conjunto >= inicio) {
			set.add(fin_conjunto);
		}
		
		if (idx_superior != idx_inferior) {
			recursiva_interna(rango, conjunto, idx_inferior + 1, idx_superior - 1, set);
		}
	}

	public static IntegerSet recursiva(List<Integer> rango, List<Integer> conjunto) {
		IntegerSet set = IntegerSet.empty();
		recursiva_interna(rango, conjunto, 0, conjunto.size() - 1, set);
		return set;
	}
}
