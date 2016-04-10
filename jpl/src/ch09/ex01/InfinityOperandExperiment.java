package ch09.ex01;

public class InfinityOperandExperiment {
	private static final int POSITIVE_CONST = 1;
	private static final int NEGATIVE_CONST = -1;

	public static void main(String[] args) {
		System.out.println("+∞と同符号の演算");
		System.out.println("+ : " + (Double.POSITIVE_INFINITY + POSITIVE_CONST));
		System.out.println("-: " + (Double.POSITIVE_INFINITY - POSITIVE_CONST));
		System.out.println("*: " + (Double.POSITIVE_INFINITY * POSITIVE_CONST));
		System.out.println("/: " + (Double.POSITIVE_INFINITY / POSITIVE_CONST));

		System.out.println("+∞と異符号の演算");
		System.out.println("+ : " + (Double.POSITIVE_INFINITY + NEGATIVE_CONST));
		System.out.println("-: " + (Double.POSITIVE_INFINITY - NEGATIVE_CONST));
		System.out.println("*: " + (Double.POSITIVE_INFINITY * NEGATIVE_CONST));
		System.out.println("/: " + (Double.POSITIVE_INFINITY / NEGATIVE_CONST));

		System.out.println("-∞と同符号の演算");
		System.out.println("+ : " + (Double.NEGATIVE_INFINITY + NEGATIVE_CONST));
		System.out.println("-: " + (Double.NEGATIVE_INFINITY - NEGATIVE_CONST));
		System.out.println("*: " + (Double.NEGATIVE_INFINITY * NEGATIVE_CONST));
		System.out.println("/: " + (Double.NEGATIVE_INFINITY / NEGATIVE_CONST));

		System.out.println("-∞と異符号の演算");
		System.out.println("+ : " + (Double.NEGATIVE_INFINITY + POSITIVE_CONST));
		System.out.println("-: " + (Double.NEGATIVE_INFINITY - POSITIVE_CONST));
		System.out.println("*: " + (Double.NEGATIVE_INFINITY * POSITIVE_CONST));
		System.out.println("/: " + (Double.NEGATIVE_INFINITY / POSITIVE_CONST));
	}
}
