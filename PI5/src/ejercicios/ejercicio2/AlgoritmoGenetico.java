package ejercicios.ejercicio2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.ag.BinaryData;

public class AlgoritmoGenetico implements BinaryData<Solucion> {

	@Override
	public Integer size() {
		return Modelo.lenCandidatos();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double objetivo = 0.;
		Integer esCompatible;
		Integer cualidadCubierta = null;
		Double presupuesto = Double.valueOf(Modelo.getPresupuesto());
		Set<String> cualidadesTotales = new HashSet<String>();
		Double restricciones = 0.;

		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) > 0) {

				Candidato c = Modelo.candidatos.get(i);
				presupuesto -= c.sueldoMin();

				for (int k = 0; k < Modelo.lenCualidades(); k++) {
					cualidadCubierta = Modelo.cualidadCubierta(i, k);
					if (cualidadCubierta == 1) {
						cualidadesTotales.addAll(Modelo.candidatos.get(i).cualidades());
						break;
					}
				}

				restricciones += -145689 * (cualidadCubierta - 1);

				for (int j = 1; j < value.size() && j != i; j++) {
					if (value.get(j) > 0) {
						esCompatible = Modelo.esIncompatible(i, j) ? 1 : 0;
						restricciones += -145689 * esCompatible;
					}
				}
				objetivo += c.valoracion();
			}
		}

		cualidadCubierta = cualidadesTotales.stream().toList().equals(Modelo.cualidadesDeseadas) ? 1 : 0;
		restricciones += -145689 * (cualidadCubierta - 1);
		restricciones += -Math.pow(Math.abs(presupuesto), 2);
		return objetivo + restricciones;
	}

	@Override
	public Solucion solucion(List<Integer> value) {
		return new Solucion(value);
	}
}
