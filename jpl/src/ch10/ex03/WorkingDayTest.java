package ch10.ex03;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorkingDayTest {
	WorkingDay wd;

	@Before
	public void setup() {
		wd = new WorkingDay();
	}

	@Test
	public void isWorkIfElseTest() {
		// execute
		boolean actual = wd.isWorkIfElse(DayOfWeek.SATURDAY);

		// test
		assertFalse(actual);
	}

	@Test
	public void isWorkSwitchTest() {
		// execute
		boolean actual = wd.isWorkSwitch(DayOfWeek.MONDAY);

		// test
		assertTrue(actual);
	}
}
