package ch13.ex02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CountSubStrTest {
	private CountSubStr c;

	@Before
	public void setup() {
		c = new CountSubStr();
	}

	@Test
	public void testCountSubStr() {
		// execute
		int actual = c.countSubStr("abcacabcaabcc", "abc");

		// test
		assertEquals(3, actual);
	}
}
