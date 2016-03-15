package ch03.ex02;

public class X {
	protected int xMask = 0x00ff;
	protected int fullMask;

	public X() {
		System.out.printf("xMask:%x yMask:%x fullMask:%x%n", xMask, 0, fullMask);
		fullMask = xMask;
		System.out.printf("xMask:%x yMask:%x fullMask:%x%n", xMask, 0, fullMask);
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}
}
