package ejercicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;

public class Ejercicio2 {
	public static List<String> a(SimpleDirectedGraph<String, DefaultEdge> graph, String salidaVistas) {
		List<String> ls = graph.vertexSet().stream().toList();
		List<String> books = new ArrayList<String>();

		for (int i = 0; i < ls.size(); i++) {
			if (graph.inDegreeOf(ls.get(i)) == 0) {
				books.add(ls.get(i));
			}
		}

		GraphColors.toDot(graph, salidaVistas, v -> v, e -> "",
				v -> GraphColors.colorIf(Color.blue, Color.black, books.contains(v)),
				a -> GraphColors.style(Style.solid));
		System.out.println(String.format("APARTADO A): Dibujado el grafo en %s correctamente", salidaVistas));

		return books;
	}

	public static void b(SimpleDirectedGraph<String, DefaultEdge> graph, String salidaVistas, List<String> ls) {
		DijkstraShortestPath<String, DefaultEdge> shtpath = new DijkstraShortestPath<String, DefaultEdge>(graph);
		Boolean isValid = null;
		GraphPath<String, DefaultEdge> gpath = null;
		Set<String> vertexes = new HashSet<String>();
		Set<DefaultEdge> edges = new HashSet<DefaultEdge>();

		for (int i = 0; i < ls.size() - 1; i++) {
			gpath = shtpath.getPath(ls.get(i), ls.get(i + 1));
			if (gpath != null) {
				vertexes.addAll(gpath.getVertexList());
				edges.addAll(gpath.getEdgeList());
				isValid = true;
			} else {
				isValid = false;
			}
		}

		if (isValid) {
			GraphColors.toDot(graph, salidaVistas, v -> v, e -> "",
					v -> GraphColors.colorIf(Color.blue, Color.black, vertexes.contains(v)),
					a -> GraphColors.colorIf(Color.blue, Color.black, edges.contains(a)));

			System.out.println(String.format(
					"APARTADO B): Para los libros %s hay solución.\nDibujado el grafo correspondiente en %s correctamente",
					ls.toString(), salidaVistas));
		} else {
			System.out.println(String.format("APARTADO B): Para los libros %s no hay solución", ls.toString()));
		}

	}

	public static void c(SimpleDirectedGraph<String, DefaultEdge> graph, String libro) {
		DijkstraShortestPath<String, DefaultEdge> shtpath = new DijkstraShortestPath<String, DefaultEdge>(graph);
		List<String> ls = graph.vertexSet().stream().filter(x -> !x.equals(libro) && shtpath.getPath(x, libro) != null)
				.toList();

		List<String> result = new ArrayList<String>();
		TopologicalOrderIterator<String, DefaultEdge> topologicIterator = new TopologicalOrderIterator<String, DefaultEdge>(graph);
		topologicIterator.forEachRemaining(v -> result.add(v));
		graph.vertexSet().stream().filter(x -> !ls.contains(x)).forEach(x -> result.remove(x));
		
		System.out.println(String.format("Para %s previamente hay que leer %s", libro, result.toString()));
	}
}
