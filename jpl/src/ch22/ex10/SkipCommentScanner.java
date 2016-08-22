package ch22.ex10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SkipCommentScanner {
	private static List<String> tokens = new ArrayList<String>();

	public static void skipCommentScan(Readable source) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(source);
		Pattern comment = Pattern.compile("#.*");
		String com;
		while (in.hasNext()) {
			if (in.hasNext(comment)) {
				com = in.nextLine();
			} else {
				tokens.add(in.nextLine());
			}
		}
	}

	public static void main(String[] args) {
		try (Reader reader = new InputStreamReader(new FileInputStream(args[0]))) {
			skipCommentScan(reader);
			for (String s : tokens) {
				System.out.println(s);
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
