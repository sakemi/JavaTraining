package ch02.ex15;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestVehicle {

	// ex09 test
	@Test
	public void testGetMaxID() {
		// setup
		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();

		// execute
		int actual = Vehicle.getMaxID();

		// test
		assertEquals(1, actual);
	}

	// ex10 test
	@Test
	public void testToString() {
		// setup
		Vehicle vehicle = new Vehicle();
		vehicle.setAngle(0);
		vehicle.setOwner("hoge");
		vehicle.setSpeed(60);

		// execute
		String actual = vehicle.toString();

		// test
		assertEquals("Owner:hoge, Speed:60.0, Angle:0.0, ID:2", actual);
	}

	// ex15 test
	@Test
	public void testChangeSpeed() {
		// setup
		Vehicle vehicle = new Vehicle();
		vehicle.setAngle(0);
		vehicle.setOwner("hoge");
		vehicle.setSpeed(60);

		// execute
		vehicle.changeSpeed(12.34);
		double actual = vehicle.getSpeed();

		// test
		assertEquals(12.34, actual, 0.01);
	}

	public void testStop() {
		// setup
		Vehicle vehicle = new Vehicle();
		vehicle.setAngle(0);
		vehicle.setOwner("hoge");
		vehicle.setSpeed(60);

		// execute
		vehicle.stop();
		double actual = vehicle.getSpeed();

		// test
		assertTrue(0.0 == actual);
	}
}
