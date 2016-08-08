package ch21.ex04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStringsFilter implements ListIterator<String> {
	private ListIterator<String> strings;
	private final int maxLen;

	public ShortStringsFilter(ListIterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
	}

	@Override
	public boolean hasNext() {
		int cnt = 0;
		while (strings.hasNext()) {
			cnt++;
			if (strings.next().length() <= maxLen) {
				back(cnt);
				return true;
			}
		}
		back(cnt);
		return false;
	}

	@Override
	public String next() {
		String next = null;
		while (strings.hasNext()) {
			next = strings.next();
			if (next.length() <= maxLen) {
				return next;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public boolean hasPrevious() {
		int cnt = 0;
		while (strings.hasPrevious()) {
			cnt++;
			if (strings.next().length() <= maxLen) {
				forward(cnt);
				return true;
			}
		}
		forward(cnt);
		return false;
	}

	@Override
	public String previous() {
		String prev = null;
		while (strings.hasPrevious()) {
			prev = strings.previous();
			if (prev.length() <= maxLen) {
				return prev;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public int nextIndex() {
		return strings.nextIndex();
	}

	@Override
	public int previousIndex() {
		return strings.previousIndex();
	}

	@Override
	public void remove() {
		strings.remove();
	}

	@Override
	public void set(String e) {
		strings.set(e);
	}

	@Override
	public void add(String e) {
		strings.add(e);
	}

	/** イテレータをcntだけ戻す **/
	private void back(int cnt) {
		for (int i = 0; i < cnt; i++) {
			strings.previous();
		}
	}

	/** イテレータをcntだけ進める **/
	private void forward(int cnt) {
		for (int i = 0; i < cnt; i++) {
			strings.next();
		}
	}

}
