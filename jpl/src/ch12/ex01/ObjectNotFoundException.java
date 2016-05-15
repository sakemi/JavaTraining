package ch12.ex01;

public class ObjectNotFoundException extends Exception {
	public Object target;

	public ObjectNotFoundException(Object target) {
		super("Target:" + target.toString() + " is not found.");
		this.target = target;
	}
}
