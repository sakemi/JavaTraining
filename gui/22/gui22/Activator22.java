package gui22;

import gui22.ui.ClockFrame;

public class Activator22 {
	private static final int SLEEP_TIME = 1000;

	public static void main(String[] args) {
		ClockFrame frame = new ClockFrame();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					frame.fit();
					frame.setBackGroundColor();
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
