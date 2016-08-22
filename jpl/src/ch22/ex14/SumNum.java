package ch22.ex14;

import java.util.StringTokenizer;

public class SumNum {
	private static final String DELIMITER = " ";

	public double sum(String num) {
		StringTokenizer st = new StringTokenizer(num, DELIMITER);
		double sum = 0;

		while (st.hasMoreTokens()) {
			sum += Double.parseDouble(st.nextToken());
		}
		return sum;
	}
}
