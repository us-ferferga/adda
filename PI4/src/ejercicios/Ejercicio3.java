package ejercicios;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.alg.tour.HeldKarpTSP;

import ejercicios.entidades.Calle;
import ejercicios.entidades.Interseccion;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.Pair;

public class Ejercicio3 {
	public static void a(Graph<Interseccion, Calle> graph, String salidaVistas, Pair<String, String> pair) {
		Interseccion i1 = null;
		Interseccion i2 = null;

		for (Interseccion i : graph.vertexSet()) {
			if (i.getNombreMonumento().equals(pair.first())) {
				i1 = i;
			} else if (i.getNombreMonumento().equals(pair.second())) {
				i2 = i;
			} else if (i1 != null && i2 != null) {
				break;
			}
		}

		FloydWarshallShortestPaths<Interseccion, Calle> floydShtPath = new FloydWarshallShortestPaths<Interseccion, Calle>(
				graph);
		GraphPath<Interseccion, Calle> gp = floydShtPath.getPath(i1, i2);

		GraphColors.toDot(graph, salidaVistas, Interseccion::toString, c -> c.getDuracion().toString(),
				v -> GraphColors.colorIf(Color.blue, Color.black, gp.getVertexList().contains(v)),
				a -> GraphColors.colorIf(Color.blue, Color.black, gp.getEdgeList().contains(a)));

		System.out.println(String.format("APARTADO A): Dibujado el grafo en %s correctamente", salidaVistas));
	}

	public static void b(Graph<Interseccion, Calle> graph, String salidaVistas) {
		HeldKarpTSP<Interseccion, Calle> alg = new HeldKarpTSP<Interseccion, Calle>();
		GraphPath<Interseccion, Calle> gp = alg.getTour(graph);

		GraphColors.toDot(graph, salidaVistas, Interseccion::toString, a -> a.getEsfuerzo().toString(),
				v -> GraphColors.colorIf(Color.blue, Color.black, gp.getVertexList().contains(v)),
				a -> GraphColors.colorIf(Color.blue, Color.black, gp.getEdgeList().contains(a)));

		System.out.println(String.format("APARTADO B): Dibujado el grafo en %s correctamente", salidaVistas));
	}

	public static void c(Graph<Interseccion, Calle> graph, String salidaVistas, List<Integer> callesCortadas) {
		List<Calle> calles = new ArrayList<Calle>(graph.edgeSet());
		for (Calle c : calles) {
			if (callesCortadas.contains(c.getId())) {
				graph.removeEdge(c);
			}
		}

		ConnectivityInspector<Interseccion, Calle> connInspector = new ConnectivityInspector<Interseccion, Calle>(
				graph);

		List<Set<Interseccion>> ls = connInspector.connectedSets();
		Set<Interseccion> monumentos = new HashSet<Interseccion>();

		Integer total = 0;
		for (int i = 0; i < ls.size(); i++) {
			Integer sum = ls.get(i).stream().mapToInt(in -> in.getRelevancia()).sum();

			if (sum >= total) {
				total = sum;
				monumentos = ls.get(i);
			}
		}

		final Set<Interseccion> finalMonumentos = monumentos;
		GraphColors.toDot(graph, salidaVistas, Interseccion::toString, c -> "",
				v -> GraphColors.colorIf(Color.blue, Color.black, finalMonumentos.contains(v)),
				a -> GraphColors.colorIf(Color.blue, Color.black, finalMonumentos.contains(graph.getEdgeSource(a))));

		System.out.println(String.format("APARTADO C): Dibujado el grafo en %s correctamente quitando las calles %s",
				salidaVistas, callesCortadas.toString()));
	}
}
