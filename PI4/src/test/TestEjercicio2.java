package test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import ejercicios.Ejercicio2;
import util.Helper;

public class TestEjercicio2 {
	private static SimpleDirectedGraph<String, DefaultEdge> getGrafo(String path) {
		List<String> lines = Helper.leerLineas(path);

		SimpleDirectedGraph<String, DefaultEdge> graph = new SimpleDirectedGraph<>(String::new, DefaultEdge::new,
				false);

		for (String a : lines) {
			if (a.contains("#")) {
				continue;
			}

			String[] aristas = a.split(",");
			
			if (aristas.length > 1) {
				graph.addEdge(aristas[0], aristas[1]);
			} else if (!graph.containsVertex(aristas[0])) {
				graph.addVertex(aristas[0]);
			}
		}

		return graph;
	}

	private static void apartadoB(List<List<String>> lists, SimpleDirectedGraph<String, DefaultEdge> graph) {
		for (int i = 0; i < lists.size(); i++) {
			List<String> libros = lists.get(i);
			String rutaSalida = String.format(System.getProperty("user.dir") + "\\src\\ficheros\\PI4E2_ApartadoB%d.gv",
					i + 1);
			new File(rutaSalida).delete();
			Ejercicio2.b(graph, rutaSalida, libros);
		}
	}

	public static void main(String[] args) {
		SimpleDirectedGraph<String, DefaultEdge> graph = getGrafo(
				System.getProperty("user.dir") + "\\src\\ficheros\\PI4E2_DatosEntrada1.txt");

		System.out.println("-- EJERCICIO 2 --");
		// Apartado A
		String rutaSalidaA = System.getProperty("user.dir") + "\\src\\ficheros\\PI4E2_ApartadoA.gv";
		new File(rutaSalidaA).delete();
		Ejercicio2.a(graph, rutaSalidaA);

		// Apartado B
		List<List<String>> BTests = Stream.of(Arrays.asList("L1", "L6", "L8"), Arrays.asList("L1", "L8", "L3"),
				Arrays.asList("L10", "L2", "L4", "L3", "L5")).toList();
		apartadoB(BTests, graph);

		// Apartado C
		Ejercicio2.c(graph, "L3");
		Ejercicio2.c(graph, "L9");
		Ejercicio2.c(graph, "L7");
	}
}
