package ch14.ex06;

public class Worker {
	public static void main(String[] args) {
		Timer timer = new Timer();
		new ShowMessage(15, timer);
		new ShowMessage(7, timer);
	}
}
