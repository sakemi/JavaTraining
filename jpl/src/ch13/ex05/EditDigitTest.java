package ch13.ex05;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditDigitTest {
	@Test
	public void insertCommaTest() {
		// execute
		String actual = EditDigit.insertComma("1234567890");

		// test
		assertEquals("1,234,567,890", actual);
	}
}
