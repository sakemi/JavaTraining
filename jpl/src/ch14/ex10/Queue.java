package ch14.ex10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queue<E> {
	private final int size;
	private final List<E> queue;

	public Queue(int size) {
		this.size = size;
		queue = Collections.synchronizedList(new ArrayList<E>());
	}

	/**
	 * 呼び出し側で同期をとること
	 *
	 * @return
	 */
	public E pop() {
		// System.out.println("pop was invoked");
		if (queue.size() == 0) {
			return null;
		}
		return queue.remove(0);
	}

	/**
	 * 呼び出し側で同期をとること
	 *
	 * @param element
	 */
	public void push(E element) {
		// System.out.println("push was invoked");
		while (queue.size() == 10) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				Thread.currentThread().interrupt();
			}
		}
		queue.add(element);
		// System.out.println("size:" + queue.size());
	}

	public int getSize() {
		return queue.size();
	}

	public boolean isEmpty() {
		if (queue.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
