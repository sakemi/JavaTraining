package ch02.ex16;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLinkedList {
	@Test
	public void testCountNode() {
		// setup
		LinkedList node1 = new LinkedList("hoge");
		LinkedList node2 = new LinkedList(123);

		// execute
		int actual = LinkedList.countNode();

		// test
		assertEquals(actual, 2);
	}
}
