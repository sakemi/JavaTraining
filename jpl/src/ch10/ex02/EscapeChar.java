package ch10.ex02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EscapeChar {
	private static final String[] SPECIALCHARS = { "\n", "\t", "\b", "\r", "\f", "\\", "\'", "\"" };

	public static String escapeChar(String str) {
		for (String cs : SPECIALCHARS) {
			if (str.contains(cs)) {
				switch (cs) {
				case "\n":
					str = str.replace(cs, "\\n");
					break;
				case "\t":
					str = str.replace(cs, "\\t");
					break;
				case "\b":
					str = str.replace(cs, "\\b");
					break;
				case "\r":
					str = str.replace(cs, "\\r");
					break;
				case "\f":
					str = str.replace(cs, "\\f");
					break;
				case "\\":
					str = str.replace(cs, "\\\\");
					break;
				case "\'":
					str = str.replace(cs, "\\\'");
					break;
				case "\"":
					str = str.replace(cs, "\\\"");
					break;
				}
			}
		}

		// \ddd
		Pattern p = Pattern.compile("\\[0-7][0-7][0-7]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			// マッチしない？
			System.out.println("match");
			str = "\\" + m.toString();
		}
		return str;
	}

	public static void main(String[] args) {
		System.out.println(escapeChar("\n\t\b\r\f\\\'\"\123"));
	}
}
