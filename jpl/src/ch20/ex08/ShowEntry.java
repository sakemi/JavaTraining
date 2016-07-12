package ch20.ex08;

import java.io.FileNotFoundException;

public class ShowEntry {
	private static final String PATH = "D:\\Doduments\\JavaTraining\\JavaTraining\\jpl\\src\\ch20\\ex08\\entry.txt";

	public static void main(String[] args) {
		try {
			Entry entry = new Entry(PATH);

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
