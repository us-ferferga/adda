package ejercicios.entidades;

import java.util.Objects;

public class Calle {
	private static int num = 0; 
	private Integer id;
	private Interseccion int1;
	private Interseccion int2;
	private Integer duracion;
	private Integer esfuerzo;

	private Calle(String[] s) {
		this.int1 = Interseccion.of(Integer.valueOf(s[0]));
		this.int2 = Interseccion.of(Integer.valueOf(s[1]));
		this.duracion = Integer.valueOf(s[2].replace("min", ""));
		this.esfuerzo = Integer.valueOf(s[3].replace("esf", ""));
		this.id = 0;
		this.id = num;
		num++;
	}
	
	private Calle(String s) {
		super();
		this.id = Integer.valueOf(s.replace("c", ""));
		this.int1 = null;
		this.int2 = null;
		this.duracion = null;
		this.esfuerzo = null;
	}

	public Integer getId() {
		return id;
	}

	public Interseccion getInt1() {
		return int1;
	}

	public Interseccion getInt2() {
		return int2;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public Integer getEsfuerzo() {
		return esfuerzo;
	}

	public static Calle fromArray(String[] formato) {
		return new Calle(formato);
	}

	public static Calle of(String s) {
		return new Calle(s);
	}

	@Override
	public int hashCode() {
		return Objects.hash(duracion, esfuerzo, id, int1, int2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calle other = (Calle) obj;
		return Objects.equals(id, other.id);
	}
}
