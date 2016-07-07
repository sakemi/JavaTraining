package ch20.ex05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class SearchWord {
	private static final String WORD = "you";
	private static final String FILE = "D:\\Doduments\\JavaTraining\\JavaTraining\\jpl\\src\\ch20\\ex05\\We Will Rock You.txt";

	public static void main(String[] args) {
		try (LineNumberReader in = new LineNumberReader(new FileReader(FILE))) {
			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains(WORD)) {
					System.out.println(in.getLineNumber() + " : " + line);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
