package ch06.ex04;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTrafficLight {
	@Test
	public void testTrafficLight() {
		// setup
		Color green = TrafficLight.GREEN.getColor();

		// execute
		String actual = green.getName();

		// test
		assertEquals("green", actual);
	}
}
