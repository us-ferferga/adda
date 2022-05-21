package ejercicios.ejercicio1;

public class Heuristica {
	public static Integer heuristic(Vertice v) {
		Integer r;
		Integer n = Modelo.lenFicheros();

		if (v.index() == n) {
			r = 0;
		} else {
			r = n - v.index();
		}

		return r;
	}

	public static Integer dimension(Vertice v, Integer a) {
		Vertice vecino = v.neighbor(a);
		Integer dimension;

		if (a < Modelo.lenMemorias()) {
			/**
			 * Peso de la arista
			 */
			dimension = heuristic(vecino) + 1;
		} else {
			dimension = heuristic(vecino);
		}

		return dimension;
	}
}