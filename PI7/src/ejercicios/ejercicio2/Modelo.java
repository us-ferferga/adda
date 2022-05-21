package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Modelo {
	private static List<String> cualidadesDeseadas;
	private static Integer presupuesto;
	private static List<Candidato> candidatos;

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

	public static List<String> cualidades(Integer i) {
		return candidatos.get(i).cualidades();
	}

	public static List<Candidato> getCandidatos() {
		return new ArrayList<Candidato>(candidatos);
	}

	public static List<String> getCualidadesDeseadas() {
		return new ArrayList<String>(cualidadesDeseadas);
	}

	public static Candidato getCandidato(Integer i) {
		return candidatos.get(i);
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
