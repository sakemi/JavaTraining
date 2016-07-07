package ch20.ex03;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestEncryptFIlter {
	public static void main(String[] args) {
		FilterInputStream in = new DecryptInputStream(System.in);
		FilterOutputStream out = new EncryptOutputStream(System.out);
		InputStreamReader reader = new InputStreamReader(in);
		OutputStreamWriter writer = new OutputStreamWriter(out);

	}
}
