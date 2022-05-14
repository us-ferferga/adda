package ejercicios.ejercicio4;

public record Contenedor(Integer id, Integer capacidad, String tipo) implements Comparable<Contenedor> {
	public static Contenedor create(String linea) {
		String[] l1 = linea.split(":");
		Integer id = Integer.valueOf(l1[0].trim().replace("CONT", ""));

		String[] info = l1[1].trim().split(";");
		Integer capacidad = Integer.valueOf(info[0].replace("capacidad=", "").trim());
		String tipo = info[1].replace("tipo=", "").trim();
		return new Contenedor(id, capacidad, tipo);
	}

	@Override
	public String toString() {
		return "CONT" + id() + ": " + capacidad() + "; " + tipo;
	}

	@Override
	public int compareTo(Contenedor c) {
		if (this.id > c.id) {
			return 1;
		} else if (this.id == c.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
