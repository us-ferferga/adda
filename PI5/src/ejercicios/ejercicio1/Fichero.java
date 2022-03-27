package ejercicios.ejercicio1;

public record Fichero(Integer id, Integer size) implements Comparable<Fichero> {
	public static Fichero create(String linea) {
		String[] s = linea.split(":");
		Integer id = Integer.parseInt(s[0].trim().replace("F", ""));
		Integer tam = Integer.valueOf(s[1].trim());

		return new Fichero(id, tam);
	}

	@Override
	public String toString() {
		return "F" + id();
	}

	@Override
	public int compareTo(Fichero f) {
		if (this.id > f.id) {
			return 1;
		} else if (this.id == f.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
