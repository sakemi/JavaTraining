package ch17.ex01;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

public class GcTest {
	public static void main(String[] args) {
		showFreeMem();

		List<Frame> list = new ArrayList<Frame>();
		for (int i = 0; i < 100000; i++) {
			list.add(new Frame());
		}
		System.out.println("Generate a lot of Objects");
		showFreeMem();

		list = null;
		System.gc();
		System.out.println("Run gc");
		showFreeMem();
	}

	private static void showFreeMem() {
		System.out.println("Free memory:" + Runtime.getRuntime().freeMemory());
	}
}
