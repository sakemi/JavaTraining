package ch20.ex03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {
	private static final byte KEY = 5;

	public EncryptOutputStream(OutputStream out) {
		super(out);
	}

	@Override
	public void write(int b) throws IOException {
		super.write(b ^ KEY);
	}
}
