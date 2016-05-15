package ch13.ex01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CountCharTest {
	private CountChar c;

	@Before
	public void setup() {
		c = new CountChar();
	}

	@Test
	public void testCountChar() {
		// execute
		int actual = c.countChar("aabaca", 'a');

		// test
		assertEquals(4, actual);
	}
}
