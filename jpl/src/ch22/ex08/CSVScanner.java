package ch22.ex08;

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
	private static int CELLS = 4;

	public static List<String[]> readCSVTable(Readable source) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();

		String exp = "^(.*),(.*),(.*),(.*)";
		String illegalExp = "^(.*),(.*),(.*),(.*),(.*)";

		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		Pattern illegalPat = Pattern.compile(illegalExp, Pattern.MULTILINE);

		while (in.hasNextLine()) {
			if (in.findInLine(illegalPat) != null) {
				throw new IllegalStateException("Too many cells: the number of cell must be 4");
			}

			if (in.findInLine("^$") != null) {
				continue;
			}

			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[CELLS];
				MatchResult match = in.match();
				for (int i = 0; i < CELLS; i++) {
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

	public static void main(String[] args) {
		try (Reader reader = new InputStreamReader(new FileInputStream(args[0]))) {
			List<String[]> vals = readCSVTable(reader);
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
