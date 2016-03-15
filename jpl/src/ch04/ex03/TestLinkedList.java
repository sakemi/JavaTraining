package ch04.ex03;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestLinkedList {
	LinkedList foo;
	LinkedList bar;

	@Before
	public void setup() {
		foo = new LinkedListNode();
		bar = new LinkedListNode();
		foo.add("foo");
		bar.add("bar");
	}

	@Test
	public void testGetContent() {
		// execute
		String actual1 = (String) foo.getContent();
		String actual2 = (String) bar.getContent();

		// test
		assertEquals("foo", actual1);
		assertEquals("bar", actual2);
	}

	@Test
	public void testGetNext() {
		// execute
		String actual = (String) (bar.getNext().getContent());

		// test
		assertEquals("foo", actual);
	}
}
