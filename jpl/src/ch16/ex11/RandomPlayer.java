package ch16.ex11;

import java.util.Random;

public class RandomPlayer extends Player {

	@Override
	public boolean[][] playerTurn(boolean[][] field) {
		// TODO 自動生成されたメソッド・スタブ
		return randomPut(field);
	}

	private boolean[][] randomPut(boolean[][] field) {
		while (true) {
			Random rand = new Random();
			int x = rand.nextInt(field.length);
			int y = rand.nextInt(field.length);
			if (field[x][y] != true && field[x][y] != false) {
				field[x][y] = false;
				return field;
			}
		}
	}
}
