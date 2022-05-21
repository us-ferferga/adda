package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Vertice(Integer index, List<Integer> capacidadRestante) {
	
	public static List<Integer> capacidadInicial;

	@SuppressWarnings("static-access")
	public static void from(List<String> ls) {
		Vertice.capacidadInicial = new Modelo(ls).getMemorias().stream().map(m -> m.storage()).toList();
	}

	public static Vertice initial() {
		return new Vertice(0, Vertice.capacidadInicial);
	}

	public static Predicate<Vertice> goal() {
		return v -> v.index == Modelo.lenFicheros();
	}

	public List<Integer> actions() {
		List<Integer> la = new ArrayList<Integer>();

		if (index >= Modelo.lenFicheros()) {
			return la;
		}

		List<Integer> sizes = IntStream.rangeClosed(0, Modelo.lenMemorias()).boxed().collect(Collectors.toList());

		for (int i : sizes) {
			if (i < Modelo.lenMemorias() && Modelo.fileSize(index) <= Modelo.maxSize(i)
					&& Modelo.fileSize(index) <= capacidadRestante.get(i)) {
				la.add(i);
			} else if (i >= Modelo.lenMemorias()) {
				la.add(i);
			}
		}

		return la;
	}

	public Vertice neighbor(Integer a) {
		List<Integer> copia = new ArrayList<Integer>(capacidadRestante);

		if (a < Modelo.lenMemorias()) {
			copia.set(a, copia.get(a) - Modelo.fileSize(index));
		}

		return new Vertice(index + 1, copia);
	}
}
