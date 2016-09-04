package ch23.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//スレッド???
public class Prog {
	public static Process useProg(String cmd) throws IOException {
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
	}

	private static void plugTogether(InputStream in, OutputStream out) throws IOException {
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
	}

	private static void plugTogether(OutputStream out, InputStream in) throws IOException {
		plugTogether(in, out);
	}

	public static void main(String[] args) throws IOException{
		useProg(args[0]);
	}
}
