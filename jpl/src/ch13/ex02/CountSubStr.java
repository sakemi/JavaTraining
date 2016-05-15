package ch13.ex02;

public class CountSubStr {
	public int countSubStr(String str, String subStr) {
		int count = 0;
		int len = subStr.length();
		for (int i = 0; i < str.length() - len; i++) {
			if ((str.subSequence(i, i + len)).equals(subStr)) {
				count++;
			}
		}
		return count;
	}
}
