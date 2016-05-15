package ch13.ex05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditDigit {
	public static String insertComma(String digit) {
		// 数字が入力されたかチェック
		Integer.parseInt(digit);
		String revers = new StringBuilder(digit).reverse().toString();

		Pattern pat = Pattern.compile("[0-9]{3}");
		Matcher matcher = pat.matcher(revers);
		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group() + ",");
		}
		matcher.appendTail(sb);

		return sb.reverse().toString();

	}
}
