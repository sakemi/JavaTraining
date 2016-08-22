package ch22.ex06;

import java.util.Random;

public class NextGaussianTest {
	private static final int TRIAL = 100000;
	private static final int SCARE = 100;
	private static final double RANGE = 0.2;
	private static final double MIN = -3;
	private static final double MAX = 3;

	private static int[] freq;
	private static int raw;

	public static void main(String[] args) {
		raw = (int) ((MAX - MIN) / RANGE + 2);
		freq = new int[raw];
		for (int i = 0; i < raw; i++) {
			freq[i] = 0;
		}

		for (int i = 0; i < TRIAL; i++) {
			Random rand = new Random();
			countUpFreq(rand.nextGaussian());
		}
		showResult();
	}

	private static void countUpFreq(double x) {
		double pivot = MIN;
		for (int i = 0; i < raw - 1; i++) {
			if (x < pivot) {
				freq[i]++;
				return;
			}

			pivot += RANGE;
		}

		freq[raw - 1]++;
	}

	private static void showResult() {
		for (int i : freq) {
			for (int j = 0; j < i / SCARE; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
	}
}
