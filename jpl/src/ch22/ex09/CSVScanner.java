package ch22.ex09;

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
	public enum Expression {
		GREEDY("^(.*),(.*),(.*),(.*)"), NO_COMMA("^([^,]*),([^,]*),([^,]*),([^,]*)"), POSSESSIVE(
				"^(.*+),(.*+),(.*+),(.*+)"), RELUCTANT("^(.*?),(.*?),(.*?),(.*?)");

		private String exp;

		private Expression(String exp) {
			this.exp = exp;
		}

		public String getExp() {
			return exp;
		}
	}

	private static final int CELLS = 4;
	private static final int TRIAL = 100000;

	public static List<String[]> readCSVTable(Readable source, Expression e) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String exp = e.getExp();

		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
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
			benchmark(Expression.GREEDY, reader);
			benchmark(Expression.NO_COMMA, reader);
			benchmark(Expression.POSSESSIVE, reader);
			benchmark(Expression.RELUCTANT, reader);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private static void benchmark(Expression exp, Readable r) throws IOException {
		long start = System.currentTimeMillis();
		for (int i = 0; i < TRIAL; i++) {
			readCSVTable(r, exp);
		}
		long end = System.currentTimeMillis();
		System.out.print(exp.toString() + ":");
		System.out.println(end - start);
	}

}
