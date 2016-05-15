package ch12.ex01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestLinkedList {
	LinkedList<Integer> list;

	@Before
	public void setup() {
		list = new LinkedList<Integer>();
	}

	@Test
	public void testFind_found() {
		// setup
		list.add(1);
		LinkedList<Integer> actual = null;

		// execute
		try {
			actual = list.find(1);
		} catch (ObjectNotFoundException e) {
			fail();
		}

		// test
		assertEquals(list, actual);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test(expected = ObjectNotFoundException.class)
	public void testFind_notFound() throws ObjectNotFoundException {
		// setup
		list.add(1);
		list.find(2);
	}
}
