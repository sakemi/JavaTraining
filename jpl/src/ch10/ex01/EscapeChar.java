package ch10.ex01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EscapeChar {
	private static final String[] SPECIALCHARS = { "\n", "\t", "\b", "\r", "\f", "\\", "\'", "\"" };

	public static String escapeChar(String str) {
		for (CharSequence cs : SPECIALCHARS) {
			if (cs.equals("\n")) {
				str = str.replace(cs, "\\n");
			} else if (cs.equals("\t")) {
				str = str.replace(cs, "\\t");
			} else if (cs.equals("\b")) {
				str = str.replace(cs, "\\b");
			} else if (cs.equals("\r")) {
				str = str.replace(cs, "\\r");
			} else if (cs.equals("\f")) {
				str = str.replace(cs, "\\f");
			} else if (cs.equals("\\")) {
				str = str.replace(cs, "\\\\");
			} else if (cs.equals("\'")) {
				str = str.replace(cs, "\\\'");
			} else if (cs.equals("\"")) {
				str = str.replace(cs, "\\\"");
			}
		}

		//\ddd
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
