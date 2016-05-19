package ch13.ex03;

import java.util.ArrayList;
import java.util.List;

public class DelimitedString {
	public static List<String> delimitedString(String from, char start, char end) {
		int startPos = from.indexOf(start);
		int endPos = from.indexOf(end);
		List<String> result = new ArrayList<String>();

		while (true) {
			if (startPos == -1) {
				break;
			} else if (endPos == -1) {
				break;
			} else if (startPos > endPos) {
				endPos = from.indexOf(end, endPos + 1);
			} else {
				result.add(from.substring(startPos, endPos + 1));
				startPos = from.indexOf(start, startPos + 1);
			}
		}

		return result;
	}
}
