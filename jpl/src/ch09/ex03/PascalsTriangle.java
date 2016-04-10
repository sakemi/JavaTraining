package ch09.ex03;

public class PascalsTriangle {
	private static final int DEPTH = 12;
	private int[][] p = new int[DEPTH][];

	public void showPascalsTriangle() {
		for (int i = 0; i < p.length; i++) {
			p[i] = new int[i + 1];

			p[i][0] = 1;
			System.out.print(p[i][0] + "\t");

			for (int j = 1; j < i; j++) {
				p[i][j] = p[i - 1][j - 1] + p[i - 1][j];
				System.out.print(p[i][j] + "\t");
			}

			if (i != 0) {
				p[i][i] = 1;
				System.out.print(p[i][i] + "\t");
			}

			System.out.printf("%n");
		}
	}

	public static void main(String[] args) {
		PascalsTriangle pas = new PascalsTriangle();
		pas.showPascalsTriangle();
	}
}
