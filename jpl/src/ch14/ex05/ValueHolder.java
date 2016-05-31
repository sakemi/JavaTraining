package ch14.ex05;

public class ValueHolder {
	private static int value;
	protected final Object lockValue = new Object();

	public ValueHolder(int value) {
		ValueHolder.value = value;
	}

	public static synchronized void increment() {
		value++;
		System.out.println(value);
	}

	public void decrement() {
		synchronized (lockValue) {
			value--;
		}
		System.out.println(value);
	}
}
