package ch13.ex06;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditDigitTest {
	@Test
	public void insertCommaTest() {
		// execute
		String actual = EditDigit.insertSeparator("1234567890", "|", 4);

		// test
		assertEquals("12|3456|7890", actual);
	}
}
