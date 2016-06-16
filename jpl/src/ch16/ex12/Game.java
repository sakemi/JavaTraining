package ch16.ex12;

import java.util.Random;

public class Game {
	private static final int SCALE = 10;
	private boolean[][] field = new boolean[SCALE][SCALE];

	public static void main(String[] args) {
		String name;
		while ((name = getNextPlayer()) != null) {
			try {
				PlayerLoader loader = new PlayerLoader();
				// Exception in thread "main" java.lang.NoClassDefFoundError:
				// IllegalName: src/ch16/ex11/RandomPlayer.class
				Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
				Player player = classOf.newInstance();
				Game game = new Game();
				double score = player.play(game);
				game.reportScore(name, score);
			} catch (Exception e) {
				reportException(name, e);
			}
		}
	}

	public boolean end(boolean[][] field) {
		for (int i = 0; i < SCALE - 2; i++) {
			for (int j = 0; j < SCALE - 2; j++) {
				if (field[i][j] == field[i][j + 1] && field[i][j + 1] == field[i][j + 2]) {
					return true;
				} else if (field[i][j] == field[i + 1][j] && field[i + 1][j] == field[i + 2][j]) {
					return true;
				} else if (field[i][j] == field[i + 1][j + 1] && field[i + 1][j + 1] == field[i + 2][j + 2]) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean[][] CPUTurn(boolean[][] field) {
		return randomPut(field);
	}

	public boolean[][] getField() {
		return field;
	}

	private boolean[][] randomPut(boolean[][] field) {
		while (true) {
			Random rand = new Random();
			int x = rand.nextInt(SCALE);
			int y = rand.nextInt(SCALE);
			if (empty(x, y)) {
				field[x][y] = false;
				return field;
			}
		}
	}

	private boolean empty(int x, int y) {
		if (field[x][y] != true && field[x][y] != false) {
			return true;
		}
		return false;
	}

	private static String getNextPlayer() {
		String name = "src/ch16/ex11/RandomPlayer.class";
		return name;
	}

	private void reportScore(String name, double score) {
		System.out.println(name + ":" + score);
	}

	private static void reportException(String name, Exception e) {
		System.out.println(name + ":" + e.toString());
		e.printStackTrace();
	}
}
