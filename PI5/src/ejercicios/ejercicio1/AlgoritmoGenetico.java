package ejercicios.ejercicio1;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class AlgoritmoGenetico implements ValuesInRangeData<Integer, Solucion> {

	private List<Fichero> ficheros = Modelo.ficheros;
	private List<Memoria> memorias = Modelo.memorias;

	@Override
	public Integer size() {
		return ficheros.size();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double objetivo = 0.;
		List<Integer> capacidadesMem = memorias.stream().map(m -> m.storage()).collect(Collectors.toList());
		List<Integer> tamMaxMem = memorias.stream().map(m -> m.maxSize()).collect(Collectors.toList());
		Integer m = Modelo.lenMemorias();

		Double restricciones = 0.;

		for (Integer mem : value) {
			if (mem != m) {
				Integer cap = capacidadesMem.get(mem);
				Integer i = value.indexOf(mem);
				Double tamMax = Double.valueOf(tamMaxMem.get(mem));
				Integer tamMaxFicheroEnMemoria = 0;

				if (tamMax <= Modelo.fileSize(i)) {
					tamMaxFicheroEnMemoria = 1;
				}

				restricciones += -tamMaxFicheroEnMemoria * 145689;
				objetivo++;
				cap -= Modelo.fileSize(i);
				capacidadesMem.set(mem, cap);
			}
		}

		for (Integer mem : capacidadesMem) {
			restricciones += -Math.pow(Math.abs(mem), 2);
		}

		return objetivo + restricciones;
	}

	@Override
	public Solucion solucion(List<Integer> value) {
		return new Solucion(value);
	}

	@Override
	public Integer max(Integer i) {
		return Modelo.lenMemorias() + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
}
