package ejercicios;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import ejercicios.entidades.Distancia;
import ejercicios.entidades.Persona;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;

public class Ejercicio4 {
	static Predicate<Persona> tieneCovid = p -> p.getCovid();
	
	private static Boolean existeRiesgo(Collection<Persona> p) {
		return p.stream().anyMatch(tieneCovid);
	}
	
	private static Color getColor(Persona persona, ConnectivityInspector<Persona, Distancia> connInspector) {
		return persona.getCovid() ? Color.red
				: existeRiesgo(connInspector.connectedSetOf(persona)) ? Color.orange
						: Color.green;
	}
	
	private static String getLabel(Persona persona, ConnectivityInspector<Persona, Distancia> connInspector,
			DijkstraShortestPath<Persona, Distancia> shtpath) {
		String result = persona.toString();
		Stream<Persona> personasConCovid = connInspector.connectedSetOf(persona).stream().filter(tieneCovid);
		Function<Persona, Integer> distanciaPersonas = p -> shtpath.getPath(persona, p).getLength();

		if (getColor(persona, connInspector) == Color.orange) {
			result += " -> " + Collections.min(personasConCovid.map(distanciaPersonas).toList()).toString();
		} else if (getColor(persona, connInspector) == Color.red) {
			result += " -> 0";
		}

		return result;
	}
	
	public static Distancia pitagoras(Persona p1, Persona p2, Double distFilas, Double distColumnas) {
		return Distancia.of(Math.sqrt(Math.pow((p1.getX() - p2.getX()) * distFilas, 2)
				+ Math.pow((p1.getY() - p2.getY()) * distColumnas, 2)));
	}

	public static Integer AyB(SimpleWeightedGraph<Persona, Distancia> graph, String salidaVistas) {
		ConnectivityInspector<Persona, Distancia> connInspector = new ConnectivityInspector<Persona, Distancia>(graph);
		DijkstraShortestPath<Persona, Distancia> shtpath = new DijkstraShortestPath<Persona, Distancia>(graph);

		GraphColors.toDot(graph, salidaVistas,v -> getLabel(v, connInspector, shtpath),
				e -> String.format("%.3f", e.getDistance()),
				v -> GraphColors.all(GraphColors.color(getColor(v, connInspector)), GraphColors.style(Style.filled)),
				e -> GraphColors.color(Color.blue));

		System.out.println(String.format("APARTADO A) y B): Dibujado el grafo en %s correctamente", salidaVistas));

		List<Integer> listOfMins = graph.vertexSet().stream().filter(v -> getColor(v, connInspector) == Color.orange)
				.map(v -> Collections.min(connInspector.connectedSetOf(v).stream().filter(tieneCovid)
						.map(p -> shtpath.getPath(v, p).getLength()).toList()))
				.toList();

		return Collections.max(listOfMins);
	}
}
