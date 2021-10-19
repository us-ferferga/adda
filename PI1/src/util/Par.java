package util;

public record Par<A, B>(A v1,B v2) {
	
	public static <A,B>Par<A,B> of (A a, B b){
		return new Par<>(a,b);
	}

	@Override
	public String toString() {
		return String.format("(%s,%s)", v1, v2);
	}
		
}