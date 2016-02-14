package ch02.ex11;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLinkedList {
	@Test
	public void testToString() {
		// setup
		LinkedList node1 = new LinkedList("hoge", null);
		LinkedList node2 = new LinkedList(123, node1);

		// execute
		String actual1 = node1.toString();
		String actual2 = node2.toString();

		// test
		assertEquals(actual1, "hoge");
		assertEquals(actual2, "123, hoge");
	}
}
