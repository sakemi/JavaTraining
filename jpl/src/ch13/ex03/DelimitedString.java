package ch13.ex03;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimitedString {
	public static List<String> delimitedString(String from, char start, char end) {
		String regExp = start + ".*" + end;
		Pattern pat = Pattern.compile(regExp);
		Matcher matcher = pat.matcher(from);
		List<String> result = new ArrayList<String>();

		while (matcher.find()) {
			result.add(matcher.group());
		}

		return result;
	}
}
