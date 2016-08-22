package ch22.ex07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVScanner {

	public static List<String[]> readCSVTable(Readable source, int cell) throws IOException {
		if (cell < 2) {
			throw new IllegalArgumentException();
		}
		@SuppressWarnings("resource")
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();

		StringBuilder sb = new StringBuilder().append("^");
		for (int i = 0; i < cell; i++) {
			sb.append("(.*),");
		}
		sb.deleteCharAt(sb.length() - 1);
		String exp = sb.toString();

		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[cell];
				MatchResult match = in.match();
				for (int i = 0; i < cell; i++) {
					cells[i] = match.group(i + 1);
				}
				vals.add(cells);
				in.nextLine();
			} else {
				throw new IOException("input format error");
			}
		}

		IOException ex = in.ioException();
		if (ex != null) {
			throw ex;
		}
		return vals;
	}

	public static void main(String[] args){
		try(Reader reader = new InputStreamReader(new FileInputStream(args[0]))){
			List<String[]> vals = readCSVTable(reader, 5);
			System.out.println(vals.get(0)[2]);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
