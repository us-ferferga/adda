package test;

import java.util.List;

import util.Helper;

public class TestEjercicio4 {
	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI4E4_DatosEntrada1.txt");
		List<String> lineasFichero2 = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI4E4_DatosEntrada1.txt");
	}

}
