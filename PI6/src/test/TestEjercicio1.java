package test;

import java.util.List;
import java.util.Optional;

import org.jgrapht.GraphPath;

import ejercicios.ejercicio1.Arista;
import ejercicios.ejercicio1.Vertice;
import ejercicios.ejercicio1.Heuristica;
import ejercicios.ejercicio1.Solucion;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;
import util.Helper;

public class TestEjercicio1 {

	private static void AStar() {
		EGraph<Vertice, Arista> graph = SimpleVirtualGraph.sum(Vertice.initial(),Vertice.goal(), e -> e.weight());
		AStar<Vertice, Arista> a = AStar.of(graph, Heuristica::heuristic, AStarType.Max);
		Optional<GraphPath<Vertice, Arista>> op = a.search();

		if (op.isPresent()) {
			System.out.println("#### Algoritmo A* ####");
			System.out.println(new Solucion(op.get()));
		} else {
			System.out.println("No hay solucion");
		}
	}

	private static void PDR() {
		EGraph<Vertice, Arista> graph = SimpleVirtualGraph.sum(Vertice.initial(),Vertice.goal(),e->e.weight());
		DynamicProgrammingReduction<Vertice, Arista> a = DynamicProgrammingReduction.of(graph, Heuristica::heuristic, PDType.Max);
		Optional<GraphPath<Vertice, Arista>> op = a.search();
		
		if (op.isPresent()) {
			System.out.println("#### Algoritmo PD ####");
			System.out.println(new Solucion(op.get()));
		}
		else {
			System.out.println("No hay solucion");
		}
	}

	private static void BT() {
		EGraph<Vertice, Arista> graph = SimpleVirtualGraph.sum(Vertice.initial(), Vertice.goal(), e -> e.weight());
		BackTracking<Vertice, Arista, Solucion> a = BackTracking.of(graph, Heuristica::heuristic, Solucion::new, BTType.Max);
		a.search();
		Optional<Solucion> op = a.getSolution();
		
		if (op.isPresent()) {
			System.out.println("#### Algoritmo BT ####");
			System.out.println(op.get());
			
		} else {
			System.out.println("No hay solucion");
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i < 3; i++) {
			String inputPath = System.getProperty("user.dir") + "\\tests\\PI6Ej1DatosEntrada" + i + ".txt";
			System.out.println("Para el fichero PI6Ej1DatosEntrada" + i + ".txt");
			List<String> lineas = Helper.leerLineas(inputPath);
			
			Vertice.from(lineas);

			AStar();
			BT();
			PDR();
		}
	}
}
