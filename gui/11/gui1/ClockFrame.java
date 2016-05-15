package gui1;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.Calendar;

public class ClockFrame extends Frame implements Runnable {
	private final int FRAME_WIDTH = 300;
	private final int FRAME_HIGHT = 100;
	private final int DIGIT_X = 50;
	private final int DIGIT_Y = 50;
	private final String TITLE = "Digital Clock";
	private final int COUNT_UP = 1000;// ms
	private String time = getNow();

	public ClockFrame() {
		setTitle(TITLE);
		setSize(FRAME_WIDTH, FRAME_HIGHT);
		addWindowListener(new ClockWindowAdapter());
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(time, DIGIT_X, DIGIT_Y);
		g.dispose();
	}

	@Override
	public void run() {
		while (true) {
			time = getNow();
			repaint();

			try {
				Thread.sleep(COUNT_UP);
			} catch (InterruptedException e) {
				// TODO:あとで何とかするかも
			}
		}
	}

	private String getNow() {
		Calendar c = Calendar.getInstance();
		return c.getTime().toString();
	}
}
