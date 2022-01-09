package ejercicios.entidades;

import java.util.Objects;

public class Interseccion {
	private Integer id;
	private String nombreMonumento;
	private Integer relevancia;
	
	private Interseccion(String[] formato) {
		super();
		this.id = Integer.valueOf(formato[0]);
		this.nombreMonumento = formato[2].strip().trim();
		this.relevancia = Integer.valueOf(formato[3].replace("int", ""));
	}

	private Interseccion(Integer id) {
		super();
		this.id = id;
		this.nombreMonumento = null;
		this.relevancia = 0;
	}

	public Integer getId() {
		return id;
	}

	public boolean isRelevante() {
		return relevancia == null ? false: relevancia > 0;
	}

	public Integer getRelevancia() {
		return relevancia;
	}

	public String getNombreMonumento() {
		return nombreMonumento;
	}

	public static Interseccion fromArray(String[] formato) {
		return new Interseccion(formato);
	}

	public static Interseccion of(Integer id) {
		return new Interseccion(id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombreMonumento, relevancia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interseccion other = (Interseccion) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		if (nombreMonumento != null && !nombreMonumento.isBlank() && !nombreMonumento.isEmpty() && nombreMonumento.length() > 0) {
			return String.format("INT-%d, %s, %dint", id, nombreMonumento, relevancia);
		}

		return String.format("INT-%d", id);
	}
}
