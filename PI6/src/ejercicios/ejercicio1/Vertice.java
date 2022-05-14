package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.graphs.virtual.VirtualVertex;

public record Vertice(Integer index, List<Integer> capacidadRestante)
		implements VirtualVertex<Vertice, Arista, Integer> {

	@SuppressWarnings("static-access")
	public static void from(List<String> ls) {
		Vertice.capacidadInicial = new Modelo(ls).memorias.stream().map(m -> m.storage()).toList();
	}

	public static Vertice of(Integer index, List<Integer> capacidadRestante) {
		return new Vertice(index, capacidadRestante);
	}

	public static Vertice initial() {
		return of(0, Vertice.capacidadInicial);
	}

	public static Predicate<Vertice> goal() {
		return v -> v.index == Modelo.lenFicheros();
	}

	public static List<Integer> capacidadInicial;

	public List<Integer> actions() {
		List<Integer> la = new ArrayList<Integer>();

		if (index >= Modelo.lenFicheros()) {
			return la;
		}

		List<Integer> sizes = IntStream.rangeClosed(0, Modelo.lenMemorias()).boxed().collect(Collectors.toList());

		for (int i : sizes) {
			if (i < Modelo.lenMemorias() && Modelo.ficheros.get(index).size() <= Modelo.memorias.get(i).maxSize()
					&& Modelo.ficheros.get(index).size() <= capacidadRestante.get(i)) {
				la.add(i);
			} else if (i >= Modelo.lenMemorias()) {
				la.add(i);
			}
		}

		return la;
	}

	public Vertice neighbor(Integer a) {
		List<Integer> copia = new ArrayList<Integer>(capacidadRestante);

		if (a < Modelo.memorias.size()) {
			copia.set(a, copia.get(a) - Modelo.fileSize(index));
		}

		return of(index + 1, copia);
	}

	public Arista edge(Integer a) {
		return Arista.of(this, this.neighbor(a), a);
	}

}
