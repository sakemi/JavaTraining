package ch22.ex11;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

//???
public class CSVScanner {
	private static final int CELLS = 4;

	public static List<String[]> readCSVTable(String path) throws IOException {
		try (Reader reader = new InputStreamReader(new FileInputStream(path))) {
			StreamTokenizer st = new StreamTokenizer(reader);
			st.ordinaryChar(' ');
			st.quoteChar(',');
			List<String[]> ret = new ArrayList<String[]>();
			String[] tokens = new String[4];
			int pos = 0;
			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				if (st.nextToken() == StreamTokenizer.TT_EOL) {
					ret.add(tokens);
					pos = 0;
				}
				if (pos > CELLS) {
					throw new IllegalStateException();
				}
				tokens[pos] = st.sval;
				pos++;
			}
			return ret;
		}
	}

	public static void main(String[] args) throws IOException {
		List<String[]> vals = readCSVTable(args[0]);
		System.out.println(vals.get(0)[2]);
	}

}
