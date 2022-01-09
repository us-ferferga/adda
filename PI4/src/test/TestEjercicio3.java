package test;

import java.io.File;
import java.util.Arrays;

import org.jgrapht.Graph;

import ejercicios.Ejercicio3;
import ejercicios.entidades.Calle;
import ejercicios.entidades.Interseccion;
import us.lsi.common.Pair;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio3 {
	public static void main(String[] args) {
		String graphPath = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E3_DatosEntrada.txt";
		Graph<Interseccion, Calle> graphDuracion = GraphsReader.newGraph(graphPath, Interseccion::fromArray, Calle::fromArray,
				Graphs2::simpleWeightedGraph, a -> Double.valueOf(a.getDuracion()));
		Graph<Interseccion, Calle> graphEsfuerzo = GraphsReader.newGraph(graphPath, Interseccion::fromArray, Calle::fromArray,
				Graphs2::simpleWeightedGraph, a -> Double.valueOf(a.getEsfuerzo()));

		System.out.println("-- EJERCICIO 3 --");
		// Apartado A
		String rutaSalidaA1 = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E3_ApartadoATest1.gv";
		String rutaSalidaA2 = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E3_ApartadoATest2.gv";
		new File(rutaSalidaA1).delete();
		new File(rutaSalidaA2).delete();
		Ejercicio3.a(graphDuracion, rutaSalidaA1, Pair.of("m7", "m2"));
		Ejercicio3.a(graphDuracion, rutaSalidaA2, Pair.of("m4", "m9"));

		// Apartado B
		String rutaSalidaB = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E3_ApartadoB.gv";
		new File(rutaSalidaB).delete();
		Ejercicio3.b(graphEsfuerzo, rutaSalidaB);

		// Apartado C
		String rutaSalidaC1 = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E3_ApartadoCTest1.gv";
		String rutaSalidaC2 = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E3_ApartadoCTest2.gv";
		new File(rutaSalidaC1).delete();
		new File(rutaSalidaC2).delete();
		Ejercicio3.c(graphDuracion, rutaSalidaC1, Arrays.asList(0, 3, 5, 6));
		Ejercicio3.c(graphDuracion, rutaSalidaC2, Arrays.asList(0, 3, 5, 6, 9));
	}
}