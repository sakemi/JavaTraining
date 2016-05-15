package ch11.ex01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestLinkedList {
	LinkedList<Integer> list;

	@Before
	public void setup() {
		list = new LinkedList<Integer>();
	}

	@Test
	public void testList() {
		list.add(1);
		list.add(2);

		Node<Integer> head = list.getHead();
		int headContent = head.getContent();

		Node<Integer> next = head.getNext();
		int nextContent = next.getContent();

		// test
		assertEquals(1, headContent);
		assertEquals(2, nextContent);
	}
}
