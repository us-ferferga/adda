package ejercicios.ejercicio2;

public class Heuristica {
	public static Integer heuristic(Vertice actual) {
		Integer h = 0;

		if (!actual.getCualidadesRestantes().isEmpty()) {
			for (int i = actual.index(); i < Modelo.lenCandidatos(); i++) {
				Integer valor = Modelo.valorCandidato(i);
				h += valor;
			}
		}

		return h;
	}
	
	public static Integer dimension(Vertice v, Integer a) {
		return heuristic(v.neighbor(a)) + a * Modelo.valorCandidato(v.index());
	}
}
