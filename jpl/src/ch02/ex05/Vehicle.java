package ch02.ex05;

public class Vehicle {
	private double speed; // km/h
	private double angle;
	private String owner;
	private static int nextID = 0;

	private int myID;

	public Vehicle() {
		this.myID = Vehicle.nextID;
		Vehicle.nextID++;
	}

	//後半の問題で使いそうな気がするので一応アクセッサを用意しておく
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public static void main(String[] args) {

		Vehicle foo = new Vehicle();
		Vehicle bar = new Vehicle();

		Vehicle[] vehicleArray = { foo, bar };

		foo.setOwner("Michael Schumacher");
		foo.setAngle(0);
		foo.setSpeed(300);

		bar.setOwner("Ayrton Senna da Silva");
		bar.setAngle(90);
		bar.setSpeed(290);

		for (Vehicle v : vehicleArray) {
			System.out.println("========================");
			System.out.println("id:" + v.myID);
			System.out.println("nextID:" + Vehicle.nextID);
			System.out.println("owner:" + v.owner);
			System.out.println("angle:" + v.angle);
			System.out.println("speed:" + v.speed);
		}
	}
}
