package ch21.ex06;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class Concat {
	public static void main(String[] args) throws IOException {
		InputStream in;
		if (args.length == 0) {
			in = System.in;
		} else {
			List<String> path = Arrays.asList(args);
			Enumeration<InputStream> files = new FileInputStreamEnumeration<InputStream>(path);
			in = new SequenceInputStream(files);
		}
		int ch;
		while((ch = in.read())!= -1){
			System.out.write(ch);
		}
	}

	public static class FileInputStreamEnumeration<E> implements Enumeration<E> {
		private List<String> path;
		private int pos;

		public FileInputStreamEnumeration(List<String> path) {
			this.path = path;
			this.pos = 0;
		}

		@Override
		public boolean hasMoreElements() {
			if (pos < path.size()) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E nextElement() {
			try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path.get(pos)))) {
				return (E) stream;
			} catch (IOException e) {
				return null;
			}
		}

	}
}
