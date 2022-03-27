package ejercicios.ejercicio5;

public record Carretera(Integer id, Double km, String nombre) implements Comparable<Carretera> {

	private static int num = 0;

	public static Carretera ofFormat(String[] formato) {
		Double km = Double.parseDouble(formato[3]);
		String nomb = formato[2];
		Integer id = num;
		num++;
		return new Carretera(id, km, nomb);
	}

	public static Carretera create(String s) {
		String[] formato = s.split(",");
		Double km = Double.parseDouble(formato[3]);
		String nomb = formato[2];
		Integer id = num;
		num++;
		return new Carretera(id, km, nomb);
	}

	public static Carretera of(Double kms) {
		Double km = kms;
		String nomb = null;
		Integer id = num;
		num++;
		return new Carretera(id, km, nomb);
	}

	@Override
	public String toString() {
		String nn = this.nombre == null ? "" : this.nombre + ",";
		return nn + this.km + ")";
	}

	@Override
	public int compareTo(Carretera c) {
		if (this.id > c.id) {
			return 1;
		} else if (this.id == c.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
