package ejercicios.ejercicio5;

import java.util.List;
import java.util.function.Predicate;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.graphs.views.IntegerVertexGraphView;

public class AlgoritmoGenetico implements SeqNormalData<Solucion> {
	Integer n = Modelo.n;
	SimpleWeightedGraph<Ciudad, Carretera> gf = Modelo.gf;
	private IntegerVertexGraphView<Ciudad, Carretera> graph = Modelo.graph;
	private Predicate<Ciudad> predicadoCiudad = Modelo.predicadoCiudad;
	private Predicate<Carretera> predicadoCarretera = Modelo.predicadoCarretera;

	@Override
	public ChromosomeType type() {
		return ChromosomeType.PermutationSubList;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double objetivo = 0.;
		Double restricciones = 0.;
		Integer testCiudad = 0;
		Integer testCarretera = 0;

		if (value.size() >= 3) {
			for (int i = 0; i < value.size() - 1; i++) {
				Ciudad ciudadInicial = Modelo.graph.getVertex(value.get(i));
				Ciudad ciudadSiguiente = Modelo.graph.getVertex(value.get(i + 1));

				if (gf.containsEdge(ciudadInicial, ciudadSiguiente)) {
					Carretera carretera = gf.getEdge(ciudadInicial, ciudadSiguiente);
					testCarretera += predicadoCarretera.test(carretera) ? 0 : 1;
					objetivo += carretera.km();
				} else {
					restricciones += 1;
				}

				if (!ciudadInicial.equals(Modelo.origen)) {
					testCiudad += predicadoCiudad.test(ciudadInicial) ? 0 : 1;
				}
			}

			Integer origen = Modelo.origen.equals(graph.getVertex(value.get(0))) ? 0 : 1;
			Integer destino = Modelo.destino.equals(graph.getVertex(value.get(value.size() - 1))) ? 0 : 1;
			restricciones += origen + destino + testCiudad + testCarretera;
		} else {
			restricciones += 1;
		}

		return restricciones < 1 ? -objetivo : -objetivo - 145689.0 * restricciones;
	}

	@Override
	public Solucion solucion(List<Integer> value) {
		return new Solucion(value);
	}

	@Override
	public Integer itemsNumber() {
		return n;
	}
}
