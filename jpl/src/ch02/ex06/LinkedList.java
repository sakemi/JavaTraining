package ch02.ex06;

public class LinkedList {
	private Object member;
	private LinkedList next;

	public static void main(String[] args) {
		Vehicle foo = new Vehicle();
		Vehicle bar = new Vehicle();

		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();

		list1.member = foo;
		list1.next = list2;

		list2.member = bar;
		list2.next = null;
	}
}
