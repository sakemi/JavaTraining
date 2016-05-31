package ch14.ex04;

public class ValueHolder {
	private static int value;

	public ValueHolder(int value){
		ValueHolder.value = value;
	}

	public static synchronized void increment(){
		value++;
		System.out.println(value);
	}
}
