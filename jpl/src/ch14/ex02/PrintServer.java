package ch14.ex02;

import java.awt.PrintJob;

public class PrintServer implements Runnable {
	private final PrintQueue requests = new PrintQueue();
	Thread trueWorker;

	public PrintServer() {
		trueWorker = new Thread(this);
		trueWorker.start();
	}

	public void print(PrintJob job) {
		requests.add(job);
	}

	@Override
	public void run() {
		if (Thread.currentThread() == trueWorker) {
			for (;;) {
				realPrint(requests.remove());
			}
		} else {
			throw new IllegalStateException();
		}
	}

	private void realPrint(PrintJob job) {
		// 印刷する
	}

}
