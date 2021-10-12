package ejercicios;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Ejercicio1 {
	// Método provisto en el enunciado
	public static boolean funcional(List<String> ls, Predicate<String> pS,
			Predicate<Integer> pI, Function<String,Integer> f) {
		return ls.stream()
				.filter(pS)
				.map(f)
				.anyMatch(pI);
	}
	
	public static boolean recursiva(List<String> ls, Predicate<String> pS,
			Predicate<Integer> pI, Function<String,Integer> f) {
		
		return true;
	}
	
	public static boolean iterativa(List<String> ls, Predicate<String> pS,
			Predicate<Integer> pI, Function<String,Integer> f) {
		Integer loop = 0;
		Integer listSize = ls.size();
		Boolean match = false;

		while (loop < listSize || match) {
			String str = ls.get(loop);
			loop++;

			if (!pS.test(str)) {
				continue;
			}
			
			Integer result = f.apply(str);
			match = pI.test(result);
		}

		return match;
	}
}
