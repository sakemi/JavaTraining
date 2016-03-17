package ch03.ex10;

//ex12 ans.
//フィールドの値は各オブジェクトに対して1つなので可変長引数をとるメソッドは不要
public class Vehicle {
	private double speed; // km/h
	private double angle;
	private String owner;
	private static int nextID = 0;
	private static int max = 0;
	private int myID;
	public static final boolean TURN_LEFT = true;
	public static final boolean TURN_RIGHT = false;

	public Vehicle() {
		this.myID = Vehicle.nextID;
		Vehicle.nextID++;
		if (Vehicle.max < this.myID) {
			Vehicle.max = this.myID;
		}
	}

	public Vehicle(String owner) {
		this();
		this.owner = owner;
	}

	// ex17
	public void turn(double angle) {
		this.angle = angle;
	}

	public void turn(boolean leftOrRight) {
		if (leftOrRight == Vehicle.TURN_LEFT) {
			angle += 90;
		} else if (leftOrRight == Vehicle.TURN_RIGHT) {
			angle -= 90;
		} else {
			// do nothing
		}
	}

	// ex15
	public void changeSpeed(double speed) {
		this.setSpeed(speed);
		System.out.println("スピード変更：" + this.speed + "km/h");
	}

	public void stop() {
		this.speed = 0;
	}

	// ex09
	public static int getMaxID() {
		return Vehicle.max;
	}

	// ex10
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Owner:").append(this.owner).append(", ");
		sb.append("Speed:").append(this.speed).append(", ");
		sb.append("Angle:").append(this.angle).append(", ");
		sb.append("ID:").append(this.myID);

		return sb.toString();
	}

	// ch02.ex13
	// ch02の最初のほうですでに作っていた。仕様の詳細は不明だが、おそらくIDは変更するようなものではないのでセッターは不要
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

	public int getID() {
		return this.myID;
	}

	// ex18
	// 結果をコンソール上で確認した
	public static void main(String[] args) {

		Vehicle foo = new Vehicle(args[0]);
		Vehicle bar = new Vehicle(args[1]);

		Vehicle[] vehicleArray = { foo, bar };

		foo.setAngle(0);
		foo.setSpeed(300);

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
