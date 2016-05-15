package ch12.ex01;

public class LinkedList<E> {
	private Node<E> head;
	private Node<E> tail;

	public void add(E content) {
		if (head == null) {
			head = new Node<E>(content);
			tail = head;
			return;
		}
		tail.setNext(new Node<E>(content));
		tail = tail.getNext();
	}

	public Node<E> getHead() {
		return head;
	}

	public LinkedList<E> find(E target) throws ObjectNotFoundException {
		Node<E> node = head;
		while (node != null) {
			if (node.getContent().equals(target)) {
				return this;
			}
			node = node.getNext();
		}
		// ぬるぽはチェック例外でないので処理されない恐れがある
		throw new ObjectNotFoundException(target);
	}
}
