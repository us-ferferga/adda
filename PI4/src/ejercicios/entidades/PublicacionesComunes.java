package ejercicios.entidades;

import java.util.Objects;

public class PublicacionesComunes  {
	private Investigador inv1;
	private Investigador inv2;
	private Integer count;
	
	private PublicacionesComunes (String[] format) {
		this.inv1 = Investigador.fromId(format[0]);
		this.inv2 = Investigador.fromId(format[1]);
		this.count = Integer.parseInt(format[2]);
	}

	private PublicacionesComunes(Investigador i1, Investigador i2) {
		this.inv1 = i1;
		this.inv2 = i2;
		this.count = 0;
	}

	public static PublicacionesComunes fromArray(String[] formato) {
		return new PublicacionesComunes(formato);
	}
	
	public static PublicacionesComunes fromVertex(Investigador i1, Investigador i2) {
		return new PublicacionesComunes(i1, i2);
	}

	public Investigador getInv1() {
		return inv1;
	}

	public Investigador getInv2() {
		return inv2;
	}

	public Integer getCount() {
		return count;
	}
	
	public Investigador getOtroInvestigador(Investigador i) {
		if (this.getInv1().equals(i)) {
			return this.getInv2();
		} else if (this.getInv2().equals(i)) {
			return this.getInv1();
		}

		return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count, inv1, inv2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublicacionesComunes other = (PublicacionesComunes) obj;
		return Objects.equals(count, other.count) && Objects.equals(inv1, other.inv1) && Objects.equals(inv2, other.inv2);
	}

	@Override
	public String toString() {
		return "PublicacionesComunes [inv1=" + inv1 + ", inv2=" + inv2 + ", count=" + count + "]";
	}
}
