package ch16.ex11;

import java.util.Random;

public class WisePlayer extends Player {
	@Override
	public boolean[][] playerTurn(boolean[][] field) {
		// TODO 自動生成されたメソッド・スタブ
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j]) {
					if (checkAround(field, i, j)) {
						return field;
					}
				}
			}
		}
		return randomPut(field);
	}

	private boolean[][] randomPut(boolean[][] field) {
		while (true) {
			Random rand = new Random();
			int x = rand.nextInt(field.length);
			int y = rand.nextInt(field.length);
			if (field[x][y] != true && field[x][y] != false) {
				field[x][y] = true;
				return field;
			}
		}
	}

	private boolean canPut(boolean[][] field, int x, int y) {
		if (x < 0 || y < 0 || x >= field.length || y >= field.length) {
			return false;
		}
		if (field[x][y] == true || field[x][y] == false) {
			return false;
		}

		return true;
	}

	private boolean checkAround(boolean[][] field, int x, int y) {
		for (int i = -1; i < 1; i++) {
			for (int j = -1; j < 1; j++) {
				if (canPut(field, x + i, y + j)) {
					field[x + i][y + j] = true;
					return true;
				}
			}
		}
		return false;
	}
}
