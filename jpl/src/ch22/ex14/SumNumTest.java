package ch22.ex14;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SumNumTest {
	private SumNum sumNum;

	@Before
	public void setUp() {
		this.sumNum = new SumNum();
	}

	@Test
	public void testSum() {
		// execute
		Double actual = sumNum.sum("0.1 1.1 0.05");

		// test
		assertEquals(new Double(1.25), actual);	//???
	}
}
