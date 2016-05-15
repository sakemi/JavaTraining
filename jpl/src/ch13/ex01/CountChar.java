package ch13.ex01;

public class CountChar {
	public int countChar(String str, char target) {
		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == target) {
				cnt++;
			}
		}
		return cnt;
	}
}
