package ch14.ex10;

/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 */

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to be exectued by a
 * thread.
 *
 * [Instruction] Implement one constructor and three methods. Don't forget to
 * write a Test program to test this class. Pay attention to @throws tags in the
 * javadoc. If needed, you can put "synchronized" keyword to methods. All
 * classes for implementation must be private inside this class. Don't use
 * java.util.concurrent package.
 */
public class ThreadPool {
	private final Queue<Runnable> queue;
	private final Thread[] threads;
	private boolean startFlg = false;
	private boolean endFlg = false;

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize
	 *            the max size of queue
	 * @param numberOfThreads
	 *            the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException
	 *             if either queueSize or numberOfThreads is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		if (queueSize < 1) {
			throw new IllegalArgumentException("queueSize must be bigger than 0");
		}
		if (numberOfThreads < 1) {
			throw new IllegalArgumentException("numberOfThreads must be bigger than 0");
		}
		queue = new Queue<Runnable>(queueSize);
		threads = new PooledThread[numberOfThreads];
		for (int i = 0; i < numberOfThreads; i++) {
			threads[i] = new PooledThread();
		}
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException
	 *             if threads has been already started.
	 */
	public void start() {
		for (Thread t : threads) {
			try {
				t.start();
			} catch (IllegalThreadStateException e) {
				throw new IllegalStateException("threads has been already started.");
			}
		}
		startFlg = true;
		endFlg = false;
	}

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException
	 *             if threads has not been started.
	 */
	public void stop() {
		for (Thread t : threads) {
			if (t.getState() == Thread.State.NEW) {
				throw new IllegalStateException("threads has not been started");
			}
		}
		synchronized (queue) {
			while (!queue.isEmpty()) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					Thread.currentThread().interrupt();
				}
			}
		}
		startFlg = false;
		endFlg = true;
		for (Thread t : threads) {
			try {
				t.join(500);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool. run()
	 * method will be invoked in the thread. If the queue is full, then this
	 * method invocation will be blocked until the queue is not full.
	 *
	 * @param runnable
	 *            Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException
	 *             if runnable is null.
	 * @throws IllegalStateException
	 *             if this pool has not been started yet.
	 */
	public void dispatch(Runnable runnable) {
		if (runnable == null) {
			throw new NullPointerException();
		}
		if (!startFlg) {
			throw new IllegalStateException("this pool has not been started yet");
		}
		synchronized (queue) {
			queue.push(runnable);
			queue.notifyAll();
		}
	}

	public int queueSize() {
		return queue.getSize();
	}

	private class PooledThread extends Thread {
		@Override
		public void run() {
			Runnable runnable;
			while (!endFlg) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						try {
							queue.wait();
						} catch (InterruptedException e) {
							// TODO 自動生成された catch ブロック
							Thread.currentThread().interrupt();
						}
					}
					runnable = queue.pop();
					queue.notifyAll();
				}
				runnable.run();
			}
		}
	}
}
