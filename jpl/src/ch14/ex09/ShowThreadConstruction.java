package ch14.ex09;

public class ShowThreadConstruction {
	private static final int WAIT = 3000;

	public void showConstruction(ThreadGroup tg) {
		Thread[] list = new Thread[tg.activeCount() + 10];// 念のためちょっと大きめ
		tg.enumerate(list);
		Runnable service = new Runnable() {
			@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ
				synchronized (this) {
					while (true) {
						for (Thread t : list) {
							if (t != null) {
								System.out.println("Name: " + t.getName() + " Group: " + t.getThreadGroup().getName());
							}
						}
						System.out.println("====================");
						try {
							wait(WAIT);
						} catch (InterruptedException e) {
							// TODO 自動生成された catch ブロック
							Thread.currentThread().interrupt();
						}
					}
				}
			}
		};
		new Thread(service).start();
	}

	public static void main(String[] args) {
		ThreadGroup parent = new ThreadGroup("parent");
		ThreadGroup son = new ThreadGroup(parent, "son");
		ThreadGroup daughter = new ThreadGroup(parent, "child2");
		ShowThreadConstruction tc = new ShowThreadConstruction();

		tc.new AliveThread(parent, "p1").start();
		tc.new AliveThread(parent, "p2").start();
		;
		tc.new AliveThread(son, "s1").start();
		;
		tc.new AliveThread(daughter, "d1").start();
		;
		tc.new AliveThread(daughter, "d2").start();
		;

		tc.showConstruction(parent);
	}

	public class AliveThread extends Thread {
		private static final int ALIVE_TIME = 10000;

		public AliveThread(ThreadGroup tg, String name) {
			super(tg, name);
		}

		@Override
		public void run() {
			try {
				Thread.sleep(ALIVE_TIME);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				Thread.currentThread().interrupt();
			}
		}
	}
}
