package ch14.ex06;

public class ShowMessage {
	private final Timer t;

	/**
	 *
	 * @param interval:
	 *            sec
	 */
	public ShowMessage(int interval, Timer t) {
		this.t = t;
		Runnable service = new Runnable() {

			@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ
				showMessage(interval);
			}
		};
		new Thread(service).start();

	}

	private synchronized void showMessage(int interval) {
		synchronized (t) {
			while (!(Timer.getTime() % interval == 0) || Timer.getTime() == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			System.out.println(interval + " sec passes");
		}
	}
}
