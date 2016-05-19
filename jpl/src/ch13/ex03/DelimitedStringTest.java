package ch13.ex03;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DelimitedStringTest {
	@Test
	public void testDelimitedString() {
		// execute
		List<String> subStrs = DelimitedString.delimitedString("aasaaaesasaea", 's', 'e');
		String actual1 = subStrs.get(0);
		String actual2 = subStrs.get(1);
		String actual3 = subStrs.get(2);

		//test
		assertEquals("saaae", actual1);
		assertEquals("sasae", actual2);
		assertEquals("sae", actual3);

	}
}
