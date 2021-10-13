package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio2 {
	// Método provisto en el enunciado
	 public static Map<Integer,List<String>> funcional (List<List<String>> listas) {
		 return listas.stream()
				 .flatMap(lista -> lista.stream())
				 .collect(Collectors.groupingBy(String::length));
	}
	 
	 public static Map<Integer,List<String>> recursiva (List<List<String>> listas) {
		 return listas.stream()
				 .flatMap(lista -> lista.stream())
				 .collect(Collectors.groupingBy(String::length));
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
