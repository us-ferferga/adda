package ejercicios.entidades;

import java.util.Objects;

public class Persona {
	private Integer x;
	private Integer y;
	private Boolean covid;

	private Persona(String[] format, Boolean covid) {
		super();
		this.x = Integer.valueOf(format[0]);
		this.y = Integer.valueOf(format[1]);
		this.covid = covid;
	}
	
	public static Persona create(String[] format, Boolean covid) {
		return new Persona(format, covid);
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Boolean getCovid() {
		return covid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Persona)) {
			return false;
		}
		Persona other = (Persona) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y);
	}

	@Override
	public String toString() {
		return x + ", " + y;
	}
}
