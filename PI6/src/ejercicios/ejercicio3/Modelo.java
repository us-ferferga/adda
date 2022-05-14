package ejercicios.ejercicio3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Modelo {
	public static List<Componente> componentes;
	public static List<Producto> productos;
	private static int T_Prod;
	private static int T_Manual;

	public static Integer lenProductos() {
		return productos.size();
	}

	public static Integer lenComponentes() {
		return componentes.size();
	}

	public static Integer getTotalProd() {
		return T_Prod;
	}

	public static Integer getTotalManual() {
		return T_Manual;
	}

	public static Integer getPrecioVenta(Integer i) {
		return productos.get(i).precio();
	}

	public static Integer getMaxUds(Integer i) {
		return productos.get(i).maxUds();
	}

	public static Integer getUdsCompProd(Integer i, Integer j) {
		return productos.get(i).numComp().get(componentes.get(j));
	}

	public static Integer getTiempoProd(Integer j) {
		return componentes.get(j).tProd();
	}

	public static Integer getTiempoElab(Integer j) {
		return componentes.get(j).tElab();
	}

	/**
	 * Comprueba si un producto i requiere de un componente j
	 * 
	 * @param i - Índice del producto
	 * @param j - Indice del componente
	 * @return
	 */
	public static Boolean tieneComponente(Integer i, Integer j) {
		return productos.get(i).numComp().keySet().contains(componentes.get(j));
	}

	/**
	 * Obtiene el tiempo de producción de un producto i, multiplicando el tiempo de
	 * producción de un componente j por el número de componentes que tiene el
	 * producto
	 *
	 * @param i - Índice del producto
	 * @param j - Índice del componente
	 * @return
	 */
	public static Integer getTiempoProdTotalProducto(Integer i, Integer j) {
		return getTiempoProd(j) * getUdsCompProd(i, j);
	}

	/**
	 * Obtiene el tiempo de elaboración de un producto i, multiplicando el tiempo de
	 * producción de un componente j por el número de componentes que tiene el
	 * producto
	 *
	 * @param i - Índice del producto
	 * @param j - Índice del componente
	 * @return
	 */
	public static Integer getTiempoElabTotalProducto(Integer i, Integer j) {
		return getTiempoElab(j) * getUdsCompProd(i, j);
	}

	/**
	 * Obtiene el tiempo de producción de un producto i, multiplicando el tiempo de
	 * producción de todos sus componentes
	 *
	 * @param i - Índice del producto
	 * @return
	 */
	public static Integer getTiempoProduccionCompletaProducto(Integer i) {

		Integer res = 0;
		for (int j = 0; j < componentes.size(); j++) {
			if (tieneComponente(i, j)) {
				res += getTiempoProd(j) * getUdsCompProd(i, j);
			}
		}
		return res;
	}

	/**
	 * Obtiene el tiempo de elaboración de un producto i, multiplicando el tiempo de
	 * producción de todos sus componentes
	 *
	 * @param i - Índice del producto
	 * @return
	 */
	public static Integer getTiempoElaboracionCompletaProducto(Integer i) {

		Integer res = 0;
		for (int j = 0; j < componentes.size(); j++) {
			if (tieneComponente(i, j)) {
				res += getTiempoElab(j) * getUdsCompProd(i, j);
			}
		}
		return res;
	}

	public Modelo(List<String> ls) {
		List<Componente> tmpComponentes = new ArrayList<Componente>();
		List<Producto> tmpProductos = new ArrayList<Producto>();
		Boolean products = false;

		for (String l : ls) {
			if (l.contains("T_prod = ")) {
				T_Prod = Integer.valueOf(l.replace("T_prod = ", "").trim());
				continue;
			} else if (l.contains("T_manual = ")) {
				T_Manual = Integer.valueOf(l.replace("T_manual = ", "").trim());
				continue;
			} else if (l.contains("// COMPONENTES")) {
				continue;
			} else if (l.contains("// PRODUCTOS")) {
				products = true;
				continue;
			}

			if (!products) {
				tmpComponentes.add(Componente.create(l));
			} else {
				tmpProductos.add(Producto.create(l, tmpComponentes));
			}
		}

		componentes = Collections.unmodifiableList(tmpComponentes);
		productos = Collections.unmodifiableList(tmpProductos);
	}
}
