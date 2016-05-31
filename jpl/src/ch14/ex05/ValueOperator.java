package ch14.ex05;

public class ValueOperator {
	public ValueOperator(ValueHolder holder) {
		Runnable service = new Runnable() {

			@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ
				for (;;) {
					holder.decrement();
				}
			}
		};
		new Thread(service).start();
	}
}
