package ejercicios.ejercicio4;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class AlgoritmoGenetico implements ValuesInRangeData<Integer, Solucion> {
	private List<Contenedor> contenedores = Modelo.contenedores;
	private List<Elemento> elementos = Modelo.elementos;

	@Override
	public Integer size() {
		return Modelo.lenElementos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {

		Double objetivo = 0.;
		Integer m = Modelo.contenedores.size();
		List<Integer> capacidadContenedores = contenedores.stream().map(c -> c.capacidad())
				.collect(Collectors.toList());
		Integer esCompatible = 0;
		Double restricciones = 0.;

		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) < m) {
				esCompatible = Modelo.esCompatible(i, value.get(i)) ? 1 : 0;
				restricciones += -145689 * (1 - esCompatible);
				if (capacidadContenedores.get(value.get(i)) > 0) {
					Integer cap = capacidadContenedores.get(value.get(i));
					cap -= elementos.get(i).size();

					if (cap == 0) {
						objetivo++;
					}
					capacidadContenedores.set(value.get(i), cap);
				}
			}
		}

		Integer numContLlenos = (int) capacidadContenedores.stream().filter(x -> x == 0).count();
		if (numContLlenos < 1)
			restricciones += -145689.0;

		return objetivo + restricciones;
	}

	@Override
	public Solucion solucion(List<Integer> value) {
		return new Solucion(value);
	}

	@Override
	public Integer max(Integer i) {
		return Modelo.lenContenedores() + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
}
