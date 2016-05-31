package ch14.ex03;

public class ValueOperator {
	public ValueOperator(ValueHolder holder) {
		Runnable service = new Runnable() {

			@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ
				for (;;) {
					holder.increment();
				}
			}
		};
		new Thread(service).start();
	}
}
