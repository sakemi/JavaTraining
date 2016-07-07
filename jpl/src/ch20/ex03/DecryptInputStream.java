package ch20.ex03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream{
	private static final byte KEY = 5;

	protected DecryptInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException{
		return super.read() ^ KEY;
	}

}
