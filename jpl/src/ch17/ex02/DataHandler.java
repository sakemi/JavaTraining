package ch17.ex02;

import java.io.File;
import java.lang.ref.WeakReference;

public class DataHandler {
	private WeakReference<File> lastFile;
	private WeakReference<byte[]> lastData;

	public byte[] readFile(File file) {
		byte[] data;

		if (lastFile.get() != null) {
			if (file.equals(lastFile)) {
				data = lastData.get();
				if (data != null) {
					return data;
				}
			}
		} else {
			lastFile = new WeakReference<File>(file);
		}

		data = readBytesFromFile(file);
		lastData = new WeakReference<byte[]>(data);
		return data;
	}

	private byte[] readBytesFromFile(File file) {
		// 問題とは直接関係ないので空実装
		return null;
	}
}
