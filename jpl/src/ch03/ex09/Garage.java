package ch03.ex09;

public class Garage implements Cloneable {
	private static final int CAPACITY = 5;
	Vehicle[] vehicle = new Vehicle[CAPACITY];

	@Override
	public Garage clone() {
		try {
			Garage nObj = (Garage) super.clone();
			nObj.vehicle = vehicle.clone();
			return nObj;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public void addVehicle(Vehicle v) {
		for (int i = 0; i < CAPACITY; i++) {
			if (vehicle[i] == null) {
				vehicle[i] = v;
				return;
			}
		}

		System.out.println("満車");
	}

	public int countVehicle() {
		int count = 0;
		for (int i = 0; i < CAPACITY; i++) {
			if (vehicle[i] != null) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Garage garage = new Garage();
		garage.addVehicle(new Vehicle());

		Garage garageCloned = garage.clone();
		garageCloned.addVehicle(new Vehicle());

		// garageに1台、garageClonedに2台入っていれば期待通り
		System.out.println("garage:" + garage.countVehicle());
		System.out.println("garageCloned:" + garageCloned.countVehicle());
	}
}
