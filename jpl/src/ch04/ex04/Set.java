package ch04.ex04;

public interface Set<E> extends Collection<E> {
	boolean hasContent(E content);

	void remove(E content);
}
