package ejercicios.ejercicio3;

public record Componente(Integer id, Integer tProd, Integer tElab) implements Comparable<Componente> {
	public static Componente create(String linea) {
		String[] l1 = linea.split(":");
		Integer id = Integer.valueOf(l1[0].trim().replace("C", ""));

		String[] l2 = l1[1].split(";");

		Integer tProd = Integer.parseInt(l2[0].replace("prod=", "").trim());
		Integer tElab = Integer.parseInt(l2[1].replace("elab=", "").trim());

		return new Componente(id, tProd, tElab);
	}

	@Override
	public String toString() {
		String prefix = this.id < 10 ? "C0" : "C";

		return prefix + id().toString();
	}

	@Override
	public int compareTo(Componente c) {
		if (this.id > c.id) {
			return 1;
		} else if (this.id == c.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
