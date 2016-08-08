package ch21.ex05;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayBunchList<E> extends AbstractList<E> {
	private final E[][] arrays;
	private final int size;

	public ArrayBunchList(E[][] arrays) {
		this.arrays = arrays.clone();
		int s = 0;
		for (E[] array : arrays) {
			s += array.length;
		}
		size = s;
	}

	@Override
	public E get(int index) {
		int off = 0;
		for (int i = 0; i < arrays.length; i++) {
			if (index < off + arrays[i].length) {
				return arrays[i][index - off];
			}
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E set(int index, E value) {
		int off = 0;
		for (int i = 0; i < arrays.length; i++) {
			if (index < off + arrays[i].length) {
				E ret = arrays[i][index - off];
				arrays[i][index - off] = value;
				return ret;
			}
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ABLListIterator();
	}

	private class ABLListIterator implements ListIterator<E> {
		private int off;
		private int array;
		private int pos;
		private boolean setable = false;

		ABLListIterator() {
			off = 0;
			array = 0;
			pos = 0;
			for (array = 0; array < arrays.length; array++) {
				if (arrays[array].length > 0) {
					break;
				}
			}
		}

		@Override
		public boolean hasNext() {
			return off + pos < size();
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			setable = true;
			E ret = arrays[array][pos++];

			while (pos >= arrays[array].length) {
				off += arrays[array++].length;
				pos = 0;
				if (array >= arrays.length) {
					break;
				}
			}
			return ret;
		}

		@Override
		public boolean hasPrevious() {
			if (pos != 0) {
				return true;
			}

			for (int i = 0; i < array; i++) {
				if (arrays[array].length != 0) {
					return true;
				}
			}

			return false;
		}

		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			setable = true;
			E ret = arrays[array][pos--];

			if (pos < 0) {
				array--;
				while (arrays[array].length == 0 && array != 0) {
					array--;
				}
				off -= arrays[array].length;
				pos = arrays[array].length - 1;
			}
			return ret;
		}

		@Override
		public int nextIndex() {
			return off + pos + 1;
		}

		@Override
		public int previousIndex() {
			return off + pos - 1;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

		@Override
		public void set(E e) {
			if (!setable) {
				throw new IllegalStateException();
			}
			arrays[array][pos] = e;
		}

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();

		}

	}

}
