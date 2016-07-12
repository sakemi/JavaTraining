package ch20.ex06;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class StatusParser {
	private static final String[] STATUS = { "ATK", "DEF", "INT" };
	private static final String FILE = "D:\\Doduments\\JavaTraining\\JavaTraining\\jpl\\src\\ch20\\ex06\\status.txt";

	private static final Map<String, Double> status = new HashMap<String, Double>();

	public static void main(String[] args) {
		try (FileReader file = new FileReader(FILE)) {
			StreamTokenizer in = new StreamTokenizer(file);
			String name = null;
			int op = 0;
			while (in.nextToken() != StreamTokenizer.TT_EOF) {
				if (in.ttype == StreamTokenizer.TT_WORD) {
					for (String s : STATUS) {
						if (in.sval.equals(s)) {
							name = s;
							break;
						}
					}
				} else if (in.ttype == '=' || in.ttype == '+' || in.ttype == '-') {
					if (name == null) {
						throw new IOException("misplaced '='");
					}
					op = in.ttype;
				} else if (in.ttype == StreamTokenizer.TT_NUMBER) {
					if (name == null || op == 0) {
						throw new IOException("misplaced number");
					}
					updateStatus(name, in.nval, (char) op);
					name = null;
					op = 0;
				}
			}
			for (Map.Entry<String, Double> e : status.entrySet()) {
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

	private static void updateStatus(String name, double value, char op) {
		if (status.get(name) == null) {
			if (op != '=') {
				throw new IllegalArgumentException();
			}
			status.put(name, value);
		} else {
			if (op == '+') {
				status.put(name, status.get(name) + value);
			} else if (op == '-') {
				status.put(name, status.get(name) - value);
			} else if (op == '=') {
				status.put(name, value);
			}
		}
	}
}
