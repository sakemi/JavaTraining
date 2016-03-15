package ch03.ex09;

//条件付きサポート
//複製できないサブクラスが出てくるかもしれない
public class Vehicle implements Cloneable {
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

	@Override
	public Vehicle clone() throws CloneNotSupportedException {
		try {
			Vehicle nObj = (Vehicle) super.clone();
			// スピード、向き、所有者は新しいオブジェクトを生成し、複製したオブジェクトが異なる値を持てるようにする
			nObj.setSpeed(new Double(nObj.getSpeed()));
			nObj.setAngle(new Double(nObj.getAngle()));
			nObj.setOwner(new String(nObj.getOwner()));
			return nObj;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public final void turn(double angle) {
		this.angle = angle;
	}

	public final void turn(boolean leftOrRight) {
		if (leftOrRight == Vehicle.TURN_LEFT) {
			angle += 90;
		} else if (leftOrRight == Vehicle.TURN_RIGHT) {
			angle -= 90;
		} else {
			// do nothing
		}
	}

	// スピードの単位変わるかも
	public void changeSpeed(double speed) {
		this.setSpeed(speed);
		System.out.println("スピード変更：" + this.speed + "km/h");
	}

	public final void stop() {
		this.speed = 0;
	}

	public static int getMaxID() {
		return Vehicle.max;
	}

	// フィールド増えるかも
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Owner:").append(this.owner).append(", ");
		sb.append("Speed:").append(this.speed).append(", ");
		sb.append("Angle:").append(this.angle).append(", ");
		sb.append("ID:").append(this.myID);

		return sb.toString();
	}

	public final double getSpeed() {
		return speed;
	}

	public final void setSpeed(double speed) {
		this.speed = speed;
	}

	public final double getAngle() {
		return angle;
	}

	public final void setAngle(double angle) {
		this.angle = angle;
	}

	public final String getOwner() {
		return owner;
	}

	public final void setOwner(String owner) {
		this.owner = owner;
	}

	public final int getID() {
		return this.myID;
	}

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
