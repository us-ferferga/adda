package ejercicios.ejercicio1;

public record Memoria(Integer id, Integer storage, Integer maxSize) implements Comparable<Memoria> {
	public static Memoria create(String linea) {
		String[] s = linea.split(":");
		Integer mem = Integer.parseInt(s[0].trim().replace("MEM", ""));
		String[] capacidadTam = s[1].split(";");

		Integer capacidad = Integer.parseInt(capacidadTam[0].replace("capacidad=", "").trim());
		Integer tam_max = Integer.parseInt(capacidadTam[1].replace("tam_max=", "").trim());

		return new Memoria(mem, capacidad, tam_max);
	}

	@Override
	public String toString() {
		return "MEM" + id();
	}

	@Override
	public int compareTo(Memoria m) {
		if (this.id > m.id) {
			return 1;
		} else if (this.id == m.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
