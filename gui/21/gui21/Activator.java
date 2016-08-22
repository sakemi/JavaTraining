package gui21;

import javax.swing.JFrame;

import gui21.ui.ClockFrame;

public class Activator {
	private static final int SLEEP_TIME = 1000;
	public static void main(String[] args) {
		JFrame frame = new ClockFrame();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					frame.repaint();
					try {
						Thread.sleep(SLEEP_TIME);
					} catch (InterruptedException e) {
						// TODO:あとで何とかするかも
					}
				}
			}
		});
		t.start();
	}
}
