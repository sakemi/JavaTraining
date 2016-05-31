package ch14.ex03;

public class Worker {
	public static void main(String[] args) {
		ValueHolder holder = new ValueHolder(0);

		new ValueOperator(holder);
		new ValueOperator(holder);
		new ValueOperator(holder);
	}
}
