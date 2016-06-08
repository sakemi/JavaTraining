package gui4;

public final class ClockRunner {
	private static Thread th = new Thread(new DigitalClock());
	public static void main(String[] args){
		th.start();
	}
}
