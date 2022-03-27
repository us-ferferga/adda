package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Modelo {
	public static List<Elemento> elementos;
	public static List<Contenedor> contenedores;

	public static Integer lenElementos() {
		return elementos.size();
	}

	public static Integer lenContenedores() {
		return contenedores.size();
	}

	public static Integer tamElem(Integer i) {
		return elementos.get(i).size();
	}

	public static Integer capacidadContenedor(Integer j) {
		return contenedores.get(j).capacidad();
	}

	public static Boolean esCompatible(Integer i, Integer j) {
		return elementos.get(i).tipos().contains(contenedores.get(j).tipo());
	}

	public Modelo(List<String> ls) {
		Boolean elements = false;
		List<Elemento> tmpElementos = new ArrayList<Elemento>();
		List<Contenedor> tmpContenedores = new ArrayList<Contenedor>();

		for (String l : ls) {
			if (l.contains("// CONTENEDORES")) {
				continue;
			}

			if (l.contains("ELEMENTOS")) {
				elements = true;
				continue;
			}

			if (!elements) {
				tmpContenedores.add(Contenedor.create(l));
			} else {
				tmpElementos.add(Elemento.create(l));
			}
		}

		elementos = Collections.unmodifiableList(tmpElementos);
		contenedores = Collections.unmodifiableList(tmpContenedores);
	}
}
