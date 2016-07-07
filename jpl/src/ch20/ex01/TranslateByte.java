package ch20.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {
	public static void main(String[] args) {
		try {
			translate('b','B',System.in,System.out);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	public static OutputStream translate(char from, char to, InputStream in, OutputStream out) throws IOException {
		int b;
		while ((b = in.read()) != -1) {
			out.write(b == from ? to : b);
		}
		return out;
	}
}
