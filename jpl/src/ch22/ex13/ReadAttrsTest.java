package ch22.ex13;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import ch22.ex04.Attr;
import ch22.ex04.Attributed;

public class ReadAttrsTest {

	@Test
	public void testReadAttrs() {
		String input = "Job=figfter ATK=135 DEF=80";
		Reader r = new StringReader(input);
		Attributed attr = ReadAttrs.readAttrs(r);

		Attr job = attr.find("Job");
		Attr atk = attr.find("ATK");
		Attr def = attr.find("DEF");

		assertEquals("figfter", (String) job.getValue());
		assertEquals("135", (String) atk.getValue());
		assertEquals("80", (String) def.getValue());
	}

	public void testReadAttrs_IllegalEqualPos() {
		String input = "Job=figfter=xxx ATK=135 DEF=80";
		Reader r = new StringReader(input);
		Attributed attr = ReadAttrs.readAttrs(r);

		Attr job = attr.find("Job");
		Attr atk = attr.find("ATK");
		Attr def = attr.find("DEF");
		Attr fighter = attr.find("fighter");

		assertEquals(null, fighter);
		assertEquals("figfter", (String) job.getValue());
		assertEquals("135", (String) atk.getValue());
		assertEquals("80", (String) def.getValue());
	}
}
