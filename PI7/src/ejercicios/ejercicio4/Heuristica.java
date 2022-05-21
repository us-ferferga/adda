package ejercicios.ejercicio4;

public class Heuristica {
	public static Integer heuristic(Vertice actual) {
		return actual.weight();
	}

	public static Integer dimension(Vertice v, Integer a) {
		return heuristic(v.neighbor(a));
	}
}
