package ejercicios.ejercicio5;

public record Ciudad(String nombre, Integer habitantes) {

	public static Ciudad ofFormat(String[] formato) {
		String nombre = formato[0];
		Double hab = Double.parseDouble(formato[1]);
		Integer habitantes = hab.intValue();
		return new Ciudad(nombre, habitantes);
	}

	public static Ciudad create(String s) {
		String[] v = s.split(",");
		String nombre = v[0].trim();
		Double hab = Double.parseDouble(v[1].trim());
		Integer habitantes = hab.intValue();
		return new Ciudad(nombre, habitantes);
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
