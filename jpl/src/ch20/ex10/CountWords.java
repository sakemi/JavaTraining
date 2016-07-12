package ch20.ex10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class CountWords {
	private static final String QUEEN = "D:/Doduments/JavaTraining/JavaTraining/jpl/src/ch20/ex05/We Will Rock You.txt";

	public static void main(String[] args) {
		Map<String, Integer> words = new HashMap<String, Integer>();
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(QUEEN))) {
			StreamTokenizer in = new StreamTokenizer(reader);
			while (in.nextToken() != StreamTokenizer.TT_EOF) {
				if (words.get(in.sval) == null) {
					words.put(in.sval, 1);
				} else {
					words.put(in.sval, words.get(in.sval) + 1);
				}
			}

			for (Map.Entry<String, Integer> e : words.entrySet()) {
				System.out.println(e.getKey() + ":" + e.getValue());
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
