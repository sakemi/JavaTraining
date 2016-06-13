package gui4;

import java.awt.Frame;
import java.awt.Window;

public class DigitalClock implements Runnable {
	private static final int MARGIN = 500; // ms
	Window win = new ClockWindow(new Frame());

	@Override
	public void run() {
		//win.setLayout(new FlowLayout(FlowLayout.CENTER));
		win.setVisible(true);
		win.toFront();
		while (true) {
			win.repaint();
			try {
				Thread.sleep(MARGIN);
			} catch (InterruptedException e) {
				// TODO:あとで何とかするかも
			}
		}
	}
}
