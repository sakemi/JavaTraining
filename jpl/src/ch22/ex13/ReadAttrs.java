package ch22.ex13;

import java.io.Reader;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import ch22.ex04.Attr;
import ch22.ex04.Attributed;

public class ReadAttrs {
	public static Attributed readAttrs(Reader source) {
		Scanner in = new Scanner(source);
		AttributedImpl attrs = new AttributedImpl();
		Pattern attrPat = Pattern.compile("(.*?)=(.*)");
		while (in.hasNext()) {
			in.next(attrPat);
			MatchResult m = in.match();
			attrs.add(new Attr(m.group(1), m.group(2)));
		}
		return attrs;
	}
}
