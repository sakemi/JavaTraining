package ch21.ex02;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

public class DataHandler {
	private Map<File, byte[]> dataMap = new WeakHashMap<>();

	public byte[] readFile(File file) {
		byte[] data;

		if (dataMap.containsKey(file)) {
			return dataMap.get(file);
		}

		data = readBytesFromFile(file);
		dataMap.put(file, data);
		return data;
	}

	private byte[] readBytesFromFile(File file) {
		// 問題とは直接関係ないので空実装
		return null;
	}
}
