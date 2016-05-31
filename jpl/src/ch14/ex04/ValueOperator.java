package ch14.ex04;

public class ValueOperator {
	public ValueOperator() {
		Runnable service = new Runnable() {

			@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ
				for (;;) {
					ValueHolder.increment();
				}
			}
		};
		new Thread(service).start();
	}
}
