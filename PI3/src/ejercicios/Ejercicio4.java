package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.Tree;

public class Ejercicio4 {
	public static List<String> recursiva(Tree<String> arbol) {		
		return recursiva_interna(arbol, "", new ArrayList<String>());
	}
	
	private static List<String> recursiva_interna(
			Tree<String> arbol,
			String cadena,
			List<String> salida) {
		String val = null;
		switch (arbol.getType()) {
			case Nary:
				val = arbol.getLabel();
				cadena = cadena.concat(val);
				
				for (Tree<String> hijo: arbol.getChildren()) {
					salida = recursiva_interna(hijo, cadena, salida);
				}
				break;
			case Leaf:
				val = arbol.getLabel();
				cadena = cadena.concat(val);
				
				Boolean esPalindroma = new StringBuilder(cadena).reverse().toString().equals(cadena);
				
				if (esPalindroma) {
					salida.add(cadena);
				}
				break;
			default:
				break;
		}

		return salida;
	}
}
