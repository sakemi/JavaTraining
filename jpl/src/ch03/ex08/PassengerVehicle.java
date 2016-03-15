package ch03.ex08;

public class PassengerVehicle extends Vehicle implements Cloneable {
	private final int seatNum;
	private int occupiedSeat;

	PassengerVehicle(int seatNum, int occupiedSeat) {
		super();
		this.seatNum = seatNum;
		if (occupiedSeat < 0 || occupiedSeat > seatNum) {
			throw new IllegalArgumentException("座っている人数は0人以上座席数以下");
		}
		this.occupiedSeat = occupiedSeat;
	}

	@Override
	public Vehicle clone() throws CloneNotSupportedException {
		try {
			PassengerVehicle nObj = (PassengerVehicle) super.clone();
			// 座ってる人数は新しいオブジェクトを生成し、複製したオブジェクトが異なる値を持てるようにする
			nObj.setOccupiedSeat(new Integer(nObj.getSeatStatus().occupiedSeat));
			return nObj;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	// フィールド増えるかも
	public SeatStatus getSeatStatus() {
		return new SeatStatus(seatNum, occupiedSeat);
	}

	public void setOccupiedSeat(int occupiedSeat) {
		if (occupiedSeat < 0 || occupiedSeat > seatNum) {
			throw new IllegalArgumentException("座っている人数は0人以上座席数以下");
		}
		this.occupiedSeat = occupiedSeat;
	}

	public static void main(String[] args) {
		PassengerVehicle smallCar = new PassengerVehicle(4, 2);
		PassengerVehicle largeCar = new PassengerVehicle(8, 5);

		SeatStatus smallCarSeatStatus = smallCar.getSeatStatus();
		SeatStatus largeCarSeatStatus = largeCar.getSeatStatus();

		System.out.println("Small car");
		System.out.printf("SeatNum:%d Passenger:%d%n", smallCarSeatStatus.seatNum, smallCarSeatStatus.occupiedSeat);
		System.out.println("Large car");
		System.out.printf("SeatNum:%d Passenger:%d", largeCarSeatStatus.seatNum, largeCarSeatStatus.occupiedSeat);
	}
}
