package ch21.ex01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ch20.ex04.LineReader;

public class LineHolder {
	private static final List<String> lines = new ArrayList<>();

	public static void main(String[] args) {
		try (LineReader lr = new LineReader(new InputStreamReader(new FileInputStream(args[0])))) {
			String line;
			while (!((line = lr.readLine()).equals(""))) {
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// 動作確認
		for (String s : lines) {
			System.out.println(s);
		}
	}

	public static List<String> getLine() {
		return lines;
	}
}
