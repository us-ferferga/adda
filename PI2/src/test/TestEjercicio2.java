package test;

import java.util.List;

import ejercicios.Ejercicio2;
import util.Helper;
import us.lsi.common.Matrix;

public class TestEjercicio2 {
	
	public static Matrix<String> procesado(List<String> lineasFichero) {
		Integer len = lineasFichero.size();
		String[][] matriz = new String[len][len];
		Integer loop = 0;
		for (String linea : lineasFichero) {
			matriz[loop] = linea.split(" ");
			loop++;
		}
		return Matrix.of(matriz);
	}
	
	public static void print(List<String> resultados) {
		Integer loop = 1;
		for (String s: resultados) {
			System.out.println(String.format("%d) %s", loop, s));
			loop++;
		}
	}

	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI2E2_DatosEntrada1.txt");
		
		List<String> lineasFichero2 = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI2E2_DatosEntrada2.txt");
		
		Matrix<String> matrix1 = procesado(lineasFichero);
		Matrix<String> matrix2 = procesado(lineasFichero2);
		
		System.out.println("Lista de cadenas obtenida (PI2E2_DatosEntrada1.txt):\n");
		print(Ejercicio2.recursiva(matrix1));
		System.out.println("______________________________________________________\n");
		System.out.println("Lista de cadenas obtenida (PI2E2_DatosEntrada2.txt):\n");
		print(Ejercicio2.recursiva(matrix2));
	}
}
