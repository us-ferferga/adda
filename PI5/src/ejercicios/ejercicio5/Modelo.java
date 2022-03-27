package ejercicios.ejercicio5;

import java.util.List;
import java.util.function.Predicate;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.views.IntegerVertexGraphView;

public class Modelo {

	public static SimpleWeightedGraph<Ciudad, Carretera> gf;
	public static Integer n;
	public static Integer habitantes;
	public static Double kms;
	public static IntegerVertexGraphView<Ciudad, Carretera> graph;
	public static Predicate<Ciudad> predicadoCiudad;
	public static Predicate<Carretera> predicadoCarretera;
	public static Ciudad origen;
	public static Ciudad destino;

	public Modelo(String fichero) {
		gf = GraphsReader.newGraph(fichero, Ciudad::ofFormat, Carretera::ofFormat, Graphs2::simpleWeightedGraph,
				Carretera::km);
		graph = IntegerVertexGraphView.of(gf);
		n = graph.vertexSet().size();
	}

	public static void predicadosOrigenDestino(List<String> ls, Integer i) {
		ls = ls.stream().map(s -> s.trim()).toList();

		Integer puntero = ls.indexOf("PI5Ej5DatosEntrada" + i + ".txt:");

		if (puntero == 0) {

			habitantes = Integer.parseInt(ls.get(puntero + 1).replace("- Al menos una ciudad intermedia con mas de", "")
					.replace("habitantes", "").trim());
			predicadoCiudad = v -> v.habitantes() > habitantes;
			kms = Double.parseDouble(
					ls.get(puntero + 2).replace("- Al menos una carretera con mas de", "").replace("kms", "").trim());
			predicadoCarretera = e -> e.km() > kms;

		} else if (puntero == 5) {

			habitantes = Integer.parseInt(ls.get(puntero + 1).replace("- Al menos una ciudad intermedia con máximo", "")
					.replace("habitantes", "").trim());
			predicadoCiudad = v -> v.habitantes() <= habitantes;
			kms = Double.parseDouble(
					ls.get(puntero + 2).replace("- Al menos una carretera con al menos", "").replace("kms", "").trim());
			predicadoCarretera = e -> e.km() >= kms;

		} else {

			habitantes = Integer.parseInt(ls.get(puntero + 1).replace("- Al menos una ciudad intermedia con mas de", "")
					.replace("habitantes", "").trim());
			predicadoCiudad = v -> v.habitantes() > habitantes;
			kms = Double.parseDouble(
					ls.get(puntero + 2).replace("- Al menos una carretera con menos de", "").replace("kms", "").trim());
			predicadoCarretera = e -> e.km() < kms;

		}

		String[] origenDestino = ls.get(puntero + 3).split(";");
		origen = gf.vertexSet().stream()
				.filter(c -> c.nombre().equals(origenDestino[0].replace("- Origen:", "").trim())).findFirst().get();
		destino = gf.vertexSet().stream()
				.filter(c -> c.nombre().equals(origenDestino[1].replace("Destino:", "").trim())).findFirst().get();
	}
}
