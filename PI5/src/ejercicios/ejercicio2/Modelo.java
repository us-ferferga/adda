package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Modelo {
	public static List<String> cualidadesDeseadas;
	private static Integer presupuesto;
	public static List<Candidato> candidatos;

	public static Integer lenCandidatos() {
		return candidatos.size();
	}

	public static Integer lenCualidades() {
		return cualidadesDeseadas.size();
	}

	public static Integer getPresupuesto() {
		return presupuesto;
	}

	public static Integer valorCandidato(Integer i) {
		return candidatos.get(i).valoracion();
	}

	public static Double sueldoMin(Integer i) {
		return candidatos.get(i).sueldoMin();
	}

	/**
	 * Comprueba si dos candidatos son compatibles
	 * 
	 * @param i - Índice de uno de los candidatos (i)
	 * @param k - Índice de otro de los candidatos (k)
	 * @return
	 */
	public static Boolean esIncompatible(Integer i, Integer k) {
		return candidatos.get(i).incompatibilidades().contains(candidatos.get(k).id());
	}

	/**
	 * Comprueba que una cualidad está cubierta por un candidato
	 * 
	 * @param i - Índice del candidato a comprobar
	 * @param j - Índice de la cualidad a comprobar
	 * @return - 1 si la cualidad del candidato j está en el candidato i
	 */
	public static Integer cualidadCubierta(Integer i, Integer j) {
		return candidatos.get(i).cualidades().contains(cualidadesDeseadas.get(j)) ? 1 : 0;
	}

	public Modelo(List<String> ls) {
		List<String> tmpCualidades = new ArrayList<String>();
		List<Candidato> tmpCandidatos = new ArrayList<Candidato>();

		for (String l : ls) {
			if (l.contains("Cualidades Deseadas:")) {
				for (String m : l.replace("Cualidades Deseadas:", "").split(",")) {
					tmpCualidades.add(m.trim());
				}

				continue;
			}

			if (l.contains("Presupuesto Máximo:")) {
				presupuesto = Integer.valueOf(l.replace("Presupuesto Máximo:", "").trim());
				continue;
			}
			tmpCandidatos.add(Candidato.create(l));
		}

		candidatos = Collections.unmodifiableList(tmpCandidatos);
		cualidadesDeseadas = Collections.unmodifiableList(tmpCualidades);
	}
}
