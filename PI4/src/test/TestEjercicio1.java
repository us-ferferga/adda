package test;

import java.io.File;

import org.jgrapht.Graph;


import ejercicios.Ejercicio1;
import ejercicios.entidades.*;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio1 {	
	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E1_DatosEntrada.txt";
		
		Graph<Investigador, PublicacionesComunes> graph = GraphsReader.newGraph(path, Investigador::fromArray,
				PublicacionesComunes::fromArray, Graphs2::simpleWeightedGraph, a -> Double.valueOf(a.getCount()));

		System.out.println("-- EJERCICIO 1 --");
		// Apartado A
		String rutaSalidaA = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E1_ApartadoA.gv";
		new File(rutaSalidaA).delete();
		Ejercicio1.a(graph, rutaSalidaA);
		
		// Apartado B
		String rutaSalidaB = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E1_ApartadoB.gv";
		new File(rutaSalidaB).delete();
		Ejercicio1.b(graph, rutaSalidaB);
		
		// Apartado C
		String rutaSalidaC = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E1_ApartadoC.gv";
		new File(rutaSalidaC).delete();
		Ejercicio1.c(graph, rutaSalidaC);
		
		// Apartado D
		String rutaSalidaD = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E1_ApartadoD.gv";
		new File(rutaSalidaD).delete();
		Ejercicio1.d(graph, rutaSalidaD);
		
		// Apartado E
		String rutaSalidaE = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E1_ApartadoE.gv";
		new File(rutaSalidaE).delete();
		Ejercicio1.e(graph, rutaSalidaE);
	}
}
