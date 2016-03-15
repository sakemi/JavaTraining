package ch03.ex02;

public class Y extends X {
	protected int yMask = 0xff00;

	public Y() {
		System.out.printf("xMask:%x yMask:%x fullMask:%x%n", xMask, yMask, fullMask);
		fullMask |= yMask;
		System.out.printf("xMask:%x yMask:%x fullMask:%x%n", xMask, yMask, fullMask);
	}

	public static void main(String[] args) {
		Y y = new Y();
	}
}