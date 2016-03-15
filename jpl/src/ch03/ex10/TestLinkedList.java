package ch03.ex10;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLinkedList {
	@Test
	public void testClone() {
		// setup
		LinkedList node1 = new LinkedList("hoge");
		LinkedList node2 = new LinkedList("fuga");
		LinkedList node2Cloned = node2.clone();
		node2.setContent("fugafuga");

		// execute
		Object actual = node2.getContent();
		Object actualCloned = node2Cloned.getContent();

		// test
		assertEquals("fugafuga", actual);
		assertEquals("fugafuga", actualCloned);
	}
}
