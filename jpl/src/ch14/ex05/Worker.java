package ch14.ex05;

public class Worker {
	public static void main(String[] args) {
		ValueHolder holder = new ValueHolder(0);

		new ValueOperator(holder);
		new ValueOperator(holder);
		new ValueOperator(holder);
	}
}
