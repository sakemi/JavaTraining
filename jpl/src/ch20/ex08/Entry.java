package ch20.ex08;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*良い方法が思いつかなかったのでギブアップ*/
public class Entry {
	private RandomAccessFile file;

	public Entry(String path) throws FileNotFoundException {
		file = new RandomAccessFile(path, "rw");
	}

	public Map<String, Long> generateTable() throws IOException {
		Map<String, Long> table = new HashMap<String, Long>();
		List<Long> entryStart = seekEntry(0);
		for (long l : entryStart) {
			file.seek(l);
			table.put(file.readLine(), l);
		}
		return table;
	}

	private List<Long> seekEntry(long start) throws IOException {
		long pos = start;
		long size = file.length();
		List<Long> entryStart = new ArrayList<Long>();
		file.seek(start);
		while (pos < size) {
			if (isSame('%')) {
				file.seek(pos + 1);
				if (isSame('%')) {
					entryStart.add(pos);
				}
			}
			pos++;
		}
		return entryStart;
	}

	private boolean isSame(char ch) throws IOException {
		return file.readChar() == ch;
	}
}
