package ejercicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import us.lsi.common.Matrix;
import us.lsi.common.View4;

public class Ejercicio2 {
	
	private static List<String> recursiva_interna(Matrix<String> matriz, List<String> lista) {
		// Al ser la matriz cuadrada, sabemos que tiene tanto el mismo número de filas como 
		// de columnas. Por tanto, usamos su tamaño (restándole 1) como índice y obtenemos la cadena
		// en el orden que se nos pide: superior izquierda (0,0 siempre), superior derecha (0, indice),
		// inferior izquierda (indice, 0), inferior derecha (indice, indice).
		//
		// Esto lo haremos recursivamente hasta que la matriz sea de orden 2x2.
		Integer indice = matriz.nf() - 1;
		String str = matriz.get(0, 0) + matriz.get(0, indice) + matriz.get(indice, 0) + matriz.get(indice, indice);

		if(matriz.nf() > 2) {
			 View4<Matrix<String>> submatriz = matriz.views4(); 
			 recursiva_interna(submatriz.d(), lista);
			 recursiva_interna(submatriz.c(), lista);
			 recursiva_interna(submatriz.b(), lista);
			 recursiva_interna(submatriz.a(), lista);
		 }

		 lista.add(str);
		 return lista;
	}

	public static List<String> recursiva(Matrix<String> matriz) {
		// Invertimos para que aparezca como en el enunciado
		List<String> lista = new ArrayList<String>();
		recursiva_interna(matriz, lista);
		Collections.reverse(lista);
		return lista;
	}
}
