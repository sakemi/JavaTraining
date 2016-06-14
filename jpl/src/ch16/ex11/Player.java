package ch16.ex11;

public abstract class Player {
	public double play(Game game) {
		boolean win;
		boolean[][] field = game.getField();
		int turn = 0;
		while (true) {
			turn++;
			field = game.CPUTurn(field);
			if (game.end(field)) {
				win = false;
				break;
			}

			field = playerTurn(field);
			if (game.end(field)) {
				win = true;
				break;
			}
		}

		if (win) {
			return 300.0 / turn;
		} else {
			return -300.0 / turn;
		}
	}

	public abstract boolean[][] playerTurn(boolean[][] field);
}
