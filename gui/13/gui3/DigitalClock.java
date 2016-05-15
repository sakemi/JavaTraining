package gui3;

import java.awt.Frame;
import java.awt.Window;

public class DigitalClock implements Runnable {
	private static final int MARGIN = 1000; // ms
	Window win = new ClockWindow(new Frame());

	@Override
	public void run() {
		//win.setLayout(new FlowLayout(FlowLayout.CENTER));
		win.setVisible(true);
		win.toFront();
		while (true) {
			try {
				win.repaint();
				Thread.sleep(MARGIN);
			} catch (InterruptedException e) {
				// TODO:あとで何とかするかも
			}
		}
	}
}
