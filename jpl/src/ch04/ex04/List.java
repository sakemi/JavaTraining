package ch04.ex04;

public interface List<E> extends Collection<E> {
	void remove(int index);

	E get(int index);
}
