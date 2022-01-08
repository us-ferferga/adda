package ejercicios.entidades;

import java.util.Objects;

public class Investigador  {
	private Integer id;
	private Integer year;
	private String uni;
	
	private Investigador(String[] format) {
		super();
		this.id = Integer.parseInt(format[0]);
		this.year = Integer.parseInt(format[1]);
		this.uni = format[2];
	}
	
	private Investigador(String id) {
		this.id = Integer.valueOf(id);
		this.year = 0;
		this.uni = null;
	}

	public static Investigador fromArray(String[] formato) {
		return new Investigador(formato);
	}
	
	public static Investigador fromId(String id) {
		return new Investigador(id);
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getYear() {
		return year;
	}
	public String getUni() {
		return uni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, uni, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investigador other = (Investigador) obj;
		return other.id == this.getId();
	}
	@Override
	public String toString() {
		return "INV-" + this.getId().toString();
	}
	
	public String toStringWithYear() { 
		 return "INV-" + this.getId().toString() + " " + this.getYear(); 
	} 
		 
	public String toStringWithUni() { 
		 return "INV-" + this.getId().toString() + " " + this.getUni(); 
	} 
}

