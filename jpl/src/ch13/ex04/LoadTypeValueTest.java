package ch13.ex04;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class LoadTypeValueTest {
	@Test
	public void loadTypeValueTest() {
		// execute
		List<Object> values = LoadTypeValue.loadTypeValue("Integer 1\nBoolean true");
		int actual1 = (int) values.get(0);
		boolean actual2 = (boolean) values.get(1);

		// test
		assertEquals(1, actual1);
		assertTrue(actual2);
	}
}
