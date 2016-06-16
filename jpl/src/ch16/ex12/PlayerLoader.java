package ch16.ex12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

public class PlayerLoader extends ClassLoader {
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			byte[] buf = bytesForClass(name);
			return defineClass(name, buf, 0, buf.length);
		} catch (IOException e) {
			throw new ClassNotFoundException(e.toString());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public URL findResource(String name) {
		File f = fileFor(name);
		if (!f.exists()) {
			return null;
		}
		try {
			return f.toURL();
		} catch (MalformedURLException e) {
			return null;
		}
	}

	@Override
	public Enumeration<URL> findResources(String name) {
		// ??
		return null;
	}

	protected byte[] bytesForClass(String name) throws IOException, ClassNotFoundException {
		try (FileInputStream in = streamFor(name)) {
			int length = in.available();
			if (length == 0) {
				throw new ClassNotFoundException(name);
			}
			byte[] buf = new byte[length];
			in.read(buf);
			return buf;
		}
	}

	private FileInputStream streamFor(String byteCode) {
		FileInputStream in;
		try {
			in = new FileInputStream(byteCode);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			in = null;
			e.printStackTrace();
		}
		return in;
	}

	private File fileFor(String resource) {
		return new File(resource);
	}

}
