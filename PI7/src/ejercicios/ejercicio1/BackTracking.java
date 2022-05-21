package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BackTracking {

	public static Vertice start;
	public static State estado;
	public static Integer maxValue;
	public static Set<Solucion> soluciones;

	public static class State {
		Vertice vertice;
		Integer valorAcumulado;
		List<Integer> acciones;
		List<Vertice> vertices;

		private State(Vertice vertice, Integer valorAcumulado, List<Integer> acciones, List<Vertice> vertices) {
			super();
			this.vertice = vertice;
			this.valorAcumulado = valorAcumulado;
			this.acciones = acciones;
			this.vertices = vertices;
		}

		public static State of(Vertice vertex) {
			List<Vertice> vt = new ArrayList<Vertice>();
			vt.add(vertex);
			return new State(vertex, 0, new ArrayList<>(), vt);
		}

		void forward(Integer a) {
			this.acciones.add(a);
			Vertice vecino = this.vertice().neighbor(a);
			this.vertices.add(vecino);

			if (a < Modelo.lenMemorias()) {
				this.valorAcumulado = this.valorAcumulado() + 1;
			} else {
				this.valorAcumulado = this.valorAcumulado();
			}

			this.vertice = vecino;
		}

		void back(Integer a) {
			this.acciones.remove(this.acciones.size() - 1);
			this.vertices.remove(this.vertices.size() - 1);
			this.vertice = this.vertices.get(this.vertices.size() - 1);

			if (a < Modelo.lenMemorias()) {
				this.valorAcumulado = this.valorAcumulado() - 1;
			} else {
				this.valorAcumulado = this.valorAcumulado();
			}

		}

		Solucion solucion() {
			return new Solucion(this.acciones);
		}

		public Vertice vertice() {
			return vertice;
		}

		public Integer valorAcumulado() {
			return valorAcumulado;
		}
	}

	public static void search(List<Integer> capacidadInicial) {
		BackTracking.start = new Vertice(0, capacidadInicial);
		BackTracking.estado = State.of(start);
		BackTracking.maxValue = Integer.MIN_VALUE;
		BackTracking.soluciones = new HashSet<Solucion>();
		search();
	}

	public static void search() {
		if (BackTracking.estado.vertice().index() == Modelo.lenFicheros()) {
			Integer value = estado.valorAcumulado();

			if (value > BackTracking.maxValue) {
				BackTracking.maxValue = value;
				BackTracking.soluciones.add(BackTracking.estado.solucion());
			}
		} else {
			List<Integer> acciones = BackTracking.estado.vertice().actions();

			for (Integer a : acciones) {
				Integer cota = BackTracking.estado.valorAcumulado()
						+ Heuristica.dimension(BackTracking.estado.vertice(), a);

				if (cota < BackTracking.maxValue) {
					continue;
				}

				BackTracking.estado.forward(a);
				search();
				BackTracking.estado.back(a);
			}
		}
	}
}
