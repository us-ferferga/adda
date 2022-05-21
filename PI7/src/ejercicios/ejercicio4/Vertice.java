package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Vertice(Integer index, List<Integer> capRestante) {

	public static List<Integer> capacidadInicial;
	public static List<Integer> completo;

	@SuppressWarnings("static-access")
	public static void from(List<String> ls) {
		Modelo model = new Modelo(ls);
		List<Contenedor> contenedores = model.getContenedores();
		Vertice.capacidadInicial = contenedores.stream().map(m -> m.capacidad()).collect(Collectors.toList());
		Vertice.completo = contenedores.stream().map(i -> 0).toList();
	}

	public static Vertice initial() {
		return new Vertice(0, capacidadInicial);
	}

	public static Predicate<Vertice> goal() {
		return v -> v.index == Modelo.lenElementos();
	}

	public static Predicate<Vertice> constraint() {

		return x -> IntStream.range(0, Modelo.lenContenedores()).boxed()
				.allMatch(c -> x.capRestante.get(c) == 0 || x.capRestante.get(c) == Vertice.capacidadInicial.get(c));
	}

	public Integer weight() {
		return (int) capRestante.stream().filter(c -> c == 0).count();
	}

	public List<Integer> actions() {

		List<Integer> la = new ArrayList<Integer>();

		if (index >= Modelo.lenElementos()) {
			return la;
		}

		List<Integer> containers = IntStream.rangeClosed(0, Modelo.lenContenedores()).boxed()
				.collect(Collectors.toList());

		for (int i : containers) {
			if (i < Modelo.lenContenedores() && Modelo.getElemento(index).size() <= capRestante.get(i)
					&& Modelo.getElemento(index).tipos().contains(Modelo.getContenedor(i).tipo())) {

				la.add(i);

			} else if (i == Modelo.lenContenedores()) {
				la.add(i);
			}
		}

		return la;
	}

	public Vertice neighbor(Integer a) {

		List<Integer> cap2 = new ArrayList<Integer>(capRestante);

		if (a < Modelo.lenContenedores()) {
			cap2.set(a, cap2.get(a) - Modelo.getElemento(index).size());
		}

		return new Vertice(index + 1, cap2);
	}
}
