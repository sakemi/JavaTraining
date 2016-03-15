package ch04.ex03;

public class LinkedListNode extends Node implements LinkedList {
	private static LinkedListNode head;

	@Override
	public void add(Object content) {
		this.content = content;
		this.next = LinkedListNode.head;
		LinkedListNode.head = this;
	}

	@Override
	public Object getContent() {
		return content;
	}

	@Override
	public LinkedListNode getNext() {
		return (LinkedListNode) next;
	}
}
