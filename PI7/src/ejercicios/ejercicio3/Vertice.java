package ejercicios.ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public record Vertice(Integer index, Integer tProdRestante, Integer tElabRestante) {

	public static Integer tProdInicial;
	public static Integer tElabInicial;

	@SuppressWarnings("static-access")
	public static void from(List<String> ls) {
		Modelo model = new Modelo(ls);

		Vertice.tProdInicial = model.getTotalProd();
		Vertice.tElabInicial = model.getTotalManual();
	}

	public static Vertice initial() {
		return new Vertice(0, Vertice.tProdInicial, Vertice.tElabInicial);
	}

	public static Predicate<Vertice> goal() {
		return v -> v.index == Modelo.lenProductos();
	}

	public Integer getRatioUds(Integer i) {
		Integer tProdTotal = Modelo.getTiempoProduccionCompletaProducto(i);
		Integer tElabTotal = Modelo.getTiempoElaboracionCompletaProducto(i);

		return Math.min(Modelo.getMaxUds(i), Math.min(tProdRestante / tProdTotal, tElabRestante / tElabTotal));
	}

	public List<Integer> actions() {
		List<Integer> la = new ArrayList<Integer>();

		if (index >= Modelo.lenProductos())
			return la;

		la = IntStream.rangeClosed(0, getRatioUds(index)).boxed().toList();

		return la;
	}

	public Vertice neighbor(Integer a) {
		Integer tProd2 = tProdRestante;
		Integer tElab2 = tElabRestante;
		for (int j = 0; j < Modelo.lenComponentes(); j++) {
			if (Modelo.tieneComponente(index, j)) {
				tProd2 = tProd2 - a * Modelo.getTiempoProdTotalProducto(index, j);
				tElab2 = tElab2 - a * Modelo.getTiempoElabTotalProducto(index, j);
			}
		}
		return new Vertice(index + 1, tProd2, tElab2);
	}
}
