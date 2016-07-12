package ch20.ex11;

import java.io.File;
import java.io.FilenameFilter;

public class FileSearch {
	private static final String PATH = "D:/Doduments/JavaTraining/JavaTraining/jpl/src/ch20/ex06";

	public static void serchWithSuffix(String path, String suffix) {
		File dir = new File(path);
		String[] files = dir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains(suffix);
			}
		});

		for (String s : files) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		serchWithSuffix(PATH, ".java");
	}
}
