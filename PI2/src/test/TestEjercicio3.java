package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ejercicios.Ejercicio3;
import us.lsi.common.IntegerSet;
import util.Helper;

public class TestEjercicio3 {

	private static List<Map<List<Integer>, List<Integer>>> procesado(List<String> lineasFichero) {
		List<Map<List<Integer>, List<Integer>>> ls = new ArrayList<Map<List<Integer>, List<Integer>>>();
		for (String s : lineasFichero) {
			// Clave: Rango
			// Valor: Conjunto
			Map<List<Integer>, List<Integer>> mp = new HashMap<List<Integer>, List<Integer>>();
			List<String> lista = Arrays.asList(s.split("#"));
			List<String> str_num = Arrays.asList(lista.get(0).split(","));
			List<String> str_rango = Arrays.asList(lista.get(1).split(","));

			List<Integer> num = new ArrayList<Integer>();
			List<Integer> rango = new ArrayList<Integer>();
			for (String _num : str_num) {
				num.add(Integer.valueOf(_num));
			}

			for (String _rango : str_rango) {
				rango.add(Integer.valueOf(_rango));
			}
			mp.put(rango, num);
			ls.add(mp);
		}

		return ls;
	}

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI2E3_DatosEntrada.txt");
		List<Map<List<Integer>, List<Integer>>> conjuntos = procesado(lineasFichero);

		for (Map<List<Integer>, List<Integer>> mp : conjuntos) {
			List<Integer> rango = mp.keySet().stream().limit(1).toList().get(0);
			List<Integer> conjunto = mp.get(rango);

			IntegerSet resultado = Ejercicio3.recursiva(rango, conjunto);
			System.out.println();
			System.out.println("Lista: " + conjunto);
			String rango_str = rango.toString();
			rango_str = rango_str.substring(0, rango_str.length() - 1);
			System.out.println("Rango: " + rango_str + ")");
			System.out.println("Conjunto obtenido: " + resultado);
		}
	}
}