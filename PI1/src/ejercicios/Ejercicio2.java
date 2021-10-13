package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Ejercicio2 {
	// Método provisto en el enunciado
	 public static Map<Integer,List<String>> funcional (List<List<String>> listas) {
		 return listas.stream()
				 .flatMap(lista -> lista.stream())
				 .collect(Collectors.groupingBy(String::length));
	}
	 private static void recursiva_nivel2(
			 List<String> lista,
			 Integer index,
			 Map<Integer, List<String>> result
			 ) {
		 Integer listSize = lista.size();
		 
		 if (index > listSize - 1) {
			 return;
		 }
		 
		 String str = lista.get(index);
		 Integer strlen = str.length();
		 if (result.containsKey(strlen)) {
			 result.get(strlen).add(str);
		 } else {
			 List<String> newList = new ArrayList<String>();
			 newList.add(str);
			 result.put(strlen, newList);
		 }
		 
		 recursiva_nivel2(lista, index + 1, result);
	 }
	 
	 private static void recursiva_nivel1(
			 List<List<String>> listas,
			 Integer index,
			 Map<Integer, List<String>> result
			) {

		 	Integer listSize = listas.size();

		 	if (index > listSize - 1) {
		 		return;
		 	}
		 	
		 	List<String> sublist = listas.get(index);
		 	recursiva_nivel2(sublist, 0, result);
		 	recursiva_nivel1(listas, index+1, result);
		}
		
		public static Map<Integer,List<String>> recursiva(List<List<String>> listas) {
			Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
			
			recursiva_nivel1(listas, 0, result);
			return result;
		}
	 
	 public static Map<Integer,List<String>> iterativa (List<List<String>> listas) {
		 Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
		 Integer listSize = listas.size();
		 Integer loop = 0;
		 
		 while (loop < listSize) {
			 List<String> sublist = listas.get(loop);
			 loop++;
			 Integer subloop = 0;
			 Integer subloopSize = sublist.size();
			 
			 while (subloop < subloopSize) {
				 String str = sublist.get(subloop);
				 subloop++;
				 Integer strlen = str.length();
				 
				 if (result.containsKey(strlen)) {
					 result.get(strlen).add(str);
				 }
				 else {
					 List<String> list = new ArrayList<String>();
					 list.add(str);
					 result.put(strlen, list);
				 }
			 }
		 }
		 
		 return result;
	}
}
