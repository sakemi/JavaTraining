package ch24.ex03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateParser {
	public static void main(String[] args) throws ParseException {
		parseDate(args[0]);
	}

	public static void parseDate(String str) throws ParseException {
		DateFormat shortStyle = DateFormat.getDateInstance(DateFormat.SHORT);
		Date shortDate = shortStyle.parse(str);
		System.out.println(shortDate);
	}
}
