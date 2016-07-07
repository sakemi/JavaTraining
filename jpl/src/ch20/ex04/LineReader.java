package ch20.ex04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class LineReader extends FilterReader {

	protected LineReader(Reader in) {
		super(in);
	}

	public String readLine() throws IOException {
		StringBuffer sb = new StringBuffer();
		while (in.read() != -1 && in.read() != '\n') {
			sb.append((char) in.read());
		}
		return sb.toString();
	}
}
