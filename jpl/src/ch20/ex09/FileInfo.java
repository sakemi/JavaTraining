package ch20.ex09;

import java.io.File;
import java.io.IOException;

public class FileInfo {
	private static final String ENTRY = "D:/Doduments/JavaTraining/JavaTraining/jpl/src/ch20/ex08/entry.txt";
	private static final String STATUS = "D:\\Doduments\\JavaTraining\\JavaTraining\\jpl\\src\\ch20\\ex06\\status.txt";
	private static final String QUEEN = "D:\\Doduments\\JavaTraining\\JavaTraining\\jpl\\src\\ch20\\ex05\\We Will Rock You.txt";

	public static void showFileInfo(String path, String... pathes) throws IOException {
		File[] file = new File[pathes.length + 1];
		file[0] = new File(path);
		for (int i = 0; i < pathes.length; i++) {
			file[i + 1] = new File(pathes[i]);
		}

		for (File f : file) {
			System.out.println(f.getName());
			System.out.println(f.getPath());
			System.out.println(f.getAbsolutePath());
			System.out.println(f.getCanonicalPath());
			System.out.println(f.getParent());
			System.out.println(f.lastModified());
			System.out.println(f.length());
		}
	}

	public static void main(String[] args) {
		try {
			showFileInfo(ENTRY, STATUS, QUEEN);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
