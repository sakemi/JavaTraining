package ch06.ex02;

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

	@Test
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

	// ex17 test
	@Test
	public void testTurnDouble() {
		// setup
		Vehicle vehicle = new Vehicle();
		vehicle.setAngle(0);
		vehicle.setOwner("hoge");
		vehicle.setSpeed(60);

		// execute
		vehicle.turn(30);
		double actual = vehicle.getAngle();

		// test
		assertEquals(30.0, actual, 0.1);
	}

	@Test
	public void testTurnBoolean() {
		// setup
		Vehicle vehicle = new Vehicle();
		vehicle.setAngle(0);
		vehicle.setOwner("hoge");
		vehicle.setSpeed(60);

		// execute
		vehicle.turn(ChangeAngle.TURN_LEFT);
		double actual = vehicle.getAngle();

		// test
		assertEquals(90.0, actual, 0.1);
	}

}
