package ch21.ex07;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * ArrayListを用いたStackの実装。 Stack固有のメソッド以外使わせたくないので、ArrayListは継承しない。
 *
 * @author sakemi
 */
public class Stack<E> {
	private final List<E> elementData = new ArrayList<E>();

	public E pop() {
		if (elementData.size() == 0) {
			throw new EmptyStackException();
		}

		return elementData.remove(elementData.size() - 1);
	}

	public void push(E elem) {
		elementData.add(elem);
	}

	public E peek() {
		if (elementData.size() == 0) {
			throw new EmptyStackException();
		}

		return elementData.get(elementData.size() - 1);
	}

	public boolean empty() {
		if (elementData.size() == 0) {
			return true;
		}
		return false;
	}

	public int search(E elem) {
		int index = 0;
		for (E e : elementData) {
			if (e.equals(elem)) {
				return index;
			}
			index++;
		}
		return -1;
	}
}
