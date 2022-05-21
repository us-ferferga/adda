package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Modelo {
	private static List<Fichero> ficheros;
	private static List<Memoria> memorias;

	public static Integer lenFicheros() {
		return ficheros.size();
	}

	public static Integer lenMemorias() {
		return memorias.size();
	}

	public static List<Memoria> getMemorias() {
		return new ArrayList<Memoria>(memorias);
	}

	public static List<Fichero> getFicheros() {
		return new ArrayList<Fichero>(ficheros);
	}

	public static Fichero getFichero(Integer i) {
		return ficheros.get(i);
	}

	public static Memoria getMemoria(Integer i) {
		return memorias.get(i);
	}

	public static Integer fileSize(Integer i) {
		return ficheros.get(i).size();
	}

	public static Integer memStorage(Integer j) {
		return memorias.get(j).storage();
	}

	public static Integer maxSize(Integer j) {
		return memorias.get(j).maxSize();
	}

	public static List<Integer> capacidadesIniciales() {
		return memorias.stream().map(m -> m.storage()).toList();
	}

	public Modelo(List<String> ls) {
		Boolean files = false;
		List<Fichero> tmpFicheros = new ArrayList<Fichero>();
		List<Memoria> tmpMemorias = new ArrayList<Memoria>();

		for (String l : ls) {
			if (l.contains("// MEMORIAS")) {
				continue;
			}

			if (l.contains("// FICHEROS") || l.contains("// ARCHIVOS")) {
				files = true;
				continue;
			}

			if (!files) {
				tmpMemorias.add(Memoria.create(l));
			} else {
				tmpFicheros.add(Fichero.create(l));
			}
		}

		memorias = Collections.unmodifiableList(tmpMemorias);
		ficheros = Collections.unmodifiableList(tmpFicheros);
	}
}
