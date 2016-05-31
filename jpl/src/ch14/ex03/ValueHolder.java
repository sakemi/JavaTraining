package ch14.ex03;

public class ValueHolder {
	private int value;

	public ValueHolder(int value){
		this.value = value;
	}

	public synchronized void increment(){
		value++;
		System.out.println(value);
	}
}
