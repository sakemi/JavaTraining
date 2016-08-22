package ch22.ex05;

import java.util.Random;

public class MonteCarlo {
	private static final int TRIAL = 10000;
	private static final int SIDE = 6;

	public static double calcDiceProbability(int dice, int sum) {
		double success = 0;
		for (int i = 0; i < TRIAL; i++) {
			int oneTrialSum = 0;
			for (int j = 0; j < dice; j++) {
				Random rand = new Random();
				oneTrialSum += rand.nextInt(SIDE) + 1;
			}
			if(oneTrialSum == sum){
				success++;
			}
		}

		return  success / TRIAL;
	}

	public static void main(String[] args) {
		System.out.println(calcDiceProbability(2, 7));	// #> 0.166 nearly equals 1/6
	}
}
