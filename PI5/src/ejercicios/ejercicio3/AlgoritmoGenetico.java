package ejercicios.ejercicio3;

import java.util.List;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class AlgoritmoGenetico implements ValuesInRangeData<Integer, Solucion> {
	private List<Componente> componentes = Modelo.componentes;
	private List<Producto> productos = Modelo.productos;

	@Override
	public Integer size() {
		return Modelo.lenProductos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double objetivo = 0.;
		Integer restricciones = 0;
		Integer tiempoTotalProd = Modelo.getTotalProd();
		Integer tiempoTotalElab = Modelo.getTotalManual();
		Integer tiempoProd = 0;
		Integer tiempoElab = 0;

		for (int i = 0; i < value.size(); i++) {

			if (value.get(i) > 0) {

				objetivo += value.get(i) * productos.get(i).precio();

				for (int j = 0; j < componentes.size(); j++) {

					if (Modelo.tieneComponente(i, j)) {

						tiempoProd += Modelo.getTiempoProdTotalProducto(i, j) * value.get(i);
						tiempoElab += Modelo.getTiempoElabTotalProducto(i, j) * value.get(i);

					}
				}
				restricciones += tiempoTotalProd < tiempoProd ? 1 : 0;
				restricciones += tiempoTotalElab < tiempoElab ? 1 : 0;
			}
		}
		return restricciones < 1 ? objetivo : objetivo - 145689.0 * (restricciones);
	}

	@Override
	public Solucion solucion(List<Integer> value) {
		return new Solucion(value);
	}

	@Override
	public Integer max(Integer i) {
		return productos.get(i).maxUds();
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
}
