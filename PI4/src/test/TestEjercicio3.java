package test;

import java.util.List;

import util.Helper;

public class TestEjercicio3 {
	public static void main(String[] args) {
		List<String> lineasFichero = Helper
				.leerLineas(System.getProperty("user.dir") + "\\src\\ficheros\\PI4E3_DatosEntrada.txt");
	}
}