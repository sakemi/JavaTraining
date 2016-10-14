package ch22.ex08;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CSVScannerTest {
	private static final String PATH = "D:/Doduments/JavaTraining/JavaTraining/jpl/src/ch22/ex08/test.csv";
	private static final String PATH2 = "D:/Doduments/JavaTraining/JavaTraining/jpl/src/ch22/ex08/test2.csv";

	private Reader r;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@After
	public void tearDown() throws IOException {
		r.close();
	}

	@Test
	public void testReadCSVTable() throws IOException {
		r = new InputStreamReader(new FileInputStream(PATH));
		List<String[]> actual = CSVScanner.readCSVTable(r);

		assertEquals(actual.get(0)[0], "aa");
		assertEquals(actual.get(0)[1], "bb");
		assertEquals(actual.get(0)[2], "cc");
		assertEquals(actual.get(0)[3], "dd");
		assertEquals(actual.get(1)[0], "ee");
		assertEquals(actual.get(1)[1], "ff");
		assertEquals(actual.get(1)[2], "gg");
		assertEquals(actual.get(1)[3], "hh");
	}

	@Test(expected = IOException.class)
	public void testReadCSV_tooManyComma() throws IOException {
		r = new InputStreamReader(new FileInputStream(PATH2));
		CSVScanner.readCSVTable(r);
	}
}
