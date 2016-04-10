package ch09.ex02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IntegerBitCountTest {
	IntegerBitCount bc;

	@Before
	public void setup() {
		bc = new IntegerBitCount();
	}

	@Test
	public void testBitCount_Positive() {
		// execute
		int actual = bc.myBitCount(0xaf);

		// test
		assertEquals(6, actual);
	}

	@Test
	public void testBitCount_Negative() {
		// execute
		int actual = bc.myBitCount(-2);

		// test
		assertEquals(31, actual);
	}
}
