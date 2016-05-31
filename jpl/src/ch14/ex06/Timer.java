package ch14.ex06;

public class Timer {
	private static final int INTERVAL = 1000; // ms
	private static int time = 0;// sec

	public Timer() {
		Runnable service = new Runnable() {
			@Override
			public void run() {
				synchronized (this) {
					while (true) {
						System.out.println(time);
						try {
							wait(INTERVAL);
							time++;
							notifyAll();
						} catch (InterruptedException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
				}
			}
		};

		new Thread(service).start();
	}

	public static synchronized int getTime() {
		return time;
	}
}
