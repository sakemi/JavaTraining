package gui;

public class DigitalClock {
	private static ClockFrame frame = new ClockFrame();
	private static Thread th = new Thread(frame);

	public static void main(String[] args) {
		frame.setVisible(true);
		th.start();
	}
}
