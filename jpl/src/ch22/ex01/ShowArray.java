package ch22.ex01;

public class ShowArray {
	private static int RAW_LENGTH = 80;

	public static void showArray(double[] array, int column) {
		int cnt = 0;
		// 列のセパレータとして','を1文字使うので1引く
		int width = RAW_LENGTH / column - 1;

		if (column <= 0) {
			throw new IllegalArgumentException();
		}

		for (double d : array) {
			System.out.printf("%" + width + "e,", d);
			cnt++;
			if (cnt == column) {
				System.out.printf("%n");
				cnt = 0;
			}
		}
	}

	public static void main(String[] args) {
		double[] array = { 1.23, 3.1415, 1.08, 2.176, 8.314, 6.002, 55123.332, 120, 6.022140857e23};
		showArray(array, 4);
	}
}
