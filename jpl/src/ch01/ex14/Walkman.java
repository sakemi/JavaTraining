package ch01.ex14;

public class Walkman {
	private int id;
	private Terminal termina = new Terminal();

	public Walkman(int id) {
		this.id = id;
	}

	public void play(Cassette cassette) {
		// カセットの音楽を再生する
	}

	public int getID() {
		return this.id;
	}

	public void connectHeadphone(Terminal terminal) {
		terminal.setIsConnect(true);
	}

	public void disconnectHeadphone(Terminal terminal) {
		terminal.setIsConnect(false);
	}
}
