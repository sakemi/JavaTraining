package ch01.ex12;

public class ImproveFibonacci {
	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String[] fibonacci = new String[MAX_INDEX];

		fibonacci[0] = String.valueOf(lo);
		for (int i = 2; i <= MAX_INDEX; i++) {
			fibonacci[i - 1] = String.valueOf(hi);
			if (hi % 2 == 0) {
				fibonacci[i - 1] += " * ";
			} else {
				// do nothing
			}
			hi = lo + hi;
			lo = hi - lo;
		}

		// 目視での動作確認用
		for (int i = 0; i < MAX_INDEX; i++) {
			System.out.println((i + 1) + ":" + fibonacci[i]);
		}

	}
}
