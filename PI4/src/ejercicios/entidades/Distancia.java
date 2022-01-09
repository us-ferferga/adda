package ejercicios.entidades;

import java.util.Objects;

public class Distancia {
	private static int num = 0;
	private Double distance;
	private int id;

	private Distancia(Double distance) {
		super();
		this.id = num;
		num++;
		this.distance = distance;
	}
	
	public static Distancia of(Double distance) {
		return new Distancia(distance);
	}

	public Double getDistance() {
		return distance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Distancia)) {
			return false;
		}
		Distancia other = (Distancia) obj;
		return id == other.id;
	}
}
