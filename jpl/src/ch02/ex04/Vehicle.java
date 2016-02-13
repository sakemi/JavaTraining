package ch02.ex04;

public class Vehicle {
	private double speed; // km/h
	private double angle;
	private String owner;
	//ex04 ans.
	//Vehicleオブジェクトが生成されるたびに更新されるので、finalにすべきでない
	private static int nextID;
	private int myID;
}
