package ch22.ex02;

import java.util.HashSet;
import java.util.Set;

public class WhichChars {
	private Set<Character> used = new HashSet<Character>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			used.add(str.charAt(i));
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder().append("[");
		for (char c : used) {
			sb.append(c);
		}
		return sb.append("]").toString();
	}

	public static void main(String[] args) {
		WhichChars wc = new WhichChars("Testing 1 2 3");
		System.out.println(wc.toString());
	}
}
