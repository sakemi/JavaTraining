package ch03.ex04;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestPassengerVehicle {
	private PassengerVehicle passengerVehicle;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGeneratePassengerVehicle_succeed(){
		try{
			passengerVehicle = new PassengerVehicle(4,2);
		}catch(IllegalArgumentException e){
			fail();
		}
	}

	@Test
	public void testGeneratePassengerVehicle_tooManyPassenger(){
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("座っている人数は0人以上座席数以下");
		passengerVehicle = new PassengerVehicle(2,4);
	}

	@Test
	public void testGeneratePassengerVehicle_minusPassenger(){
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("座っている人数は0人以上座席数以下");
		passengerVehicle = new PassengerVehicle(5,-1);
	}

	@Test
	public void testGetSeatStatus(){
		//setup
		passengerVehicle = new PassengerVehicle(4,2);

		//execute
		SeatStatus actual = passengerVehicle.getSeatStatus();

		//test
		assertEquals(4, actual.seatNum);
		assertEquals(2, actual.occupiedSeat);
	}
}
