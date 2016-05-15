package ch12.ex01;

public class Node<E> {
	private E content;
	private Node<E> next;

	public Node(E content) {
		this.content = content;
	}

	public E getContent() {
		return content;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}
}
