package gui23;

import gui23.ui.ClockWindow;

public class Activator23 {
	private static final int SLEEP_TIME = 1000;

	public static void main(String[] args) {
		ClockWindow frame = new ClockWindow();
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
