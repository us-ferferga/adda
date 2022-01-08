package ejercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import ejercicios.entidades.Investigador;
import ejercicios.entidades.PublicacionesComunes;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;

public class Ejercicio1 {
	private static Function<Investigador, String> investigador = Investigador::toString;
	private static Function<PublicacionesComunes, String> numPub = pc -> pc.getCount().toString();

	public static void a(Graph<Investigador, PublicacionesComunes> graph, String salidaVistas) {
		Predicate<Investigador> pvertice = v -> (v.getYear() < 1982
				|| graph.edgesOf(v).stream().anyMatch(e -> e.getCount() > 5));
		Predicate<PublicacionesComunes> parista = pc -> graph.getEdgeWeight(pc) > 5;

		GraphColors.toDot(graph, salidaVistas, Investigador::toStringWithYear, numPub,
				v -> GraphColors.colorIf(Color.blue, Color.black, pvertice.test(v)),
				a -> GraphColors.colorIf(Color.blue, Color.black, parista.test(a)));

		System.out.println(String.format("APARTADO A): Dibujado el grafo en %s correctamente", salidaVistas));
	}

	public static void b(Graph<Investigador, PublicacionesComunes> graph, String salidaVistas) {
		Comparator<Investigador> cmp = Comparator.comparingInt(v -> graph.degreeOf(v));
		Set<Investigador> s = graph.vertexSet().stream().sorted(cmp.reversed()).limit(5).collect(Collectors.toSet());

		GraphColors.toDot(graph, salidaVistas, investigador, numPub,
				v -> GraphColors.colorIf(Color.blue, Color.green, s.contains(v)), a -> GraphColors.color(Color.green));

		System.out.println(String.format("APARTADO B): Dibujado el grafo en %s correctamente", salidaVistas));
	}

	public static Map<Investigador, List<Investigador>> c(Graph<Investigador, PublicacionesComunes> graph,
			String salidaVistas) {
		Map<Investigador, List<Investigador>> mp = new HashMap<>();
		Set<Investigador> investigadores = graph.vertexSet();

		for (Investigador i : investigadores) {
			if (!mp.containsKey(i)) {
				List<Investigador> colaboradoresMayorAMenor = graph.edgesOf(i).stream()
						.sorted(Comparator.comparing(PublicacionesComunes::getCount).reversed())
						.map(pc -> pc.getOtroInvestigador(i)).collect(Collectors.toList());

				mp.put(i, colaboradoresMayorAMenor);
			}
		}

		List<PublicacionesComunes> masPublicacionesCompartidas = new ArrayList<>();
		for (Entry<Investigador, List<Investigador>> keyValue : mp.entrySet()) {
			Investigador i = keyValue.getValue().get(0);
			Investigador IPublicacion = graph.vertexSet().stream().filter(v -> v.equals(i)).findFirst().get();
			masPublicacionesCompartidas.add(graph.getEdge(IPublicacion, keyValue.getKey()));
		}

		GraphColors.toDot(graph, salidaVistas, investigador, numPub, v -> GraphColors.color(Color.black),
				a -> GraphColors.colorIf(Color.blue, Color.black, masPublicacionesCompartidas.contains(a)));

		System.out.println(String.format("APARTADO C): Dibujado el grafo en %s correctamente", salidaVistas));

		return mp;
	}

	public static void d(Graph<Investigador, PublicacionesComunes> graph, String salidaVistas) {
		DijkstraShortestPath<Investigador, PublicacionesComunes> shtpath = new DijkstraShortestPath<>(graph);
		Set<Investigador> investigadores = graph.vertexSet();
		GraphPath<Investigador, PublicacionesComunes> gpath = null;

		Integer len = 0;
		Investigador orig = null;
		Investigador dst = null;

		for (Investigador i1 : investigadores) {
			for (Investigador i2 : investigadores) {
				Integer max = shtpath.getPath(i1, i2).getLength();

				if (len < max) {
					len = max;
					orig = i1;
					dst = i2;

					gpath = shtpath.getPath(orig, dst);
				}
			}
		}
		final GraphPath<Investigador, PublicacionesComunes> gpathFinal = gpath;

		GraphColors.toDot(graph, salidaVistas, investigador, numPub,
				v -> GraphColors.colorIf(Color.blue, Color.black, gpathFinal.getVertexList().contains(v)),
				a -> GraphColors.colorIf(Color.blue, Color.black, gpathFinal.getEdgeList().contains(a)));

		System.out.println(String.format("APARTADO D): Dibujado el grafo en %s correctamente", salidaVistas));
	}

	public static void e(Graph<Investigador, PublicacionesComunes> graph, String salidaVistas) {
		Graph<Investigador, PublicacionesComunes> copia = new SimpleWeightedGraph<Investigador, PublicacionesComunes>(
				null, null);
		Graphs.addGraph(copia, graph);

		for (Investigador i1 : copia.vertexSet()) {
			for (Investigador i2 : copia.vertexSet()) {
				if (!i1.equals(i2) && copia.getEdge(i1, i2) == null && i1.getUni().equals(i2.getUni())) {
					PublicacionesComunes pub = PublicacionesComunes.fromVertex(i2, i1);
					copia.addEdge(i1, i2, pub);
				}
			}
		}

		GreedyColoring grcoloring = new GreedyColoring<>(copia);
		Coloring coloring = grcoloring.getColoring();
		Map<Investigador, Integer> colors = coloring.getColors();
		Map<Investigador, String> attendeeName = new HashMap<Investigador, String>();
		List<Set<Investigador>> asistentes = coloring.getColorClasses();

		Integer loop = 1;
		for (Set<Investigador> list : asistentes) {
			if (list.size() > 1) {
				for (Investigador i : list) {
					attendeeName.put(i, i.toStringWithUni() + " / r" + loop.toString());
				}
			}
			loop++;
		}

		GraphColors.toDot(graph, salidaVistas,
				i -> attendeeName.get(i) != null ? attendeeName.get(i) : i.toStringWithUni(), numPub,
				v -> GraphColors.color(colors.get(v)), a -> GraphColors.style(Style.solid));

		System.out.println(String.format("APARTADO E): Dibujado el grafo en %s correctamente", salidaVistas));

	}
}
