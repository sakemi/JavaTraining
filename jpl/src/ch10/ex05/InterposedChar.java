package ch10.ex05;

public class InterposedChar {
	public static void printInterposedChar(char start, char end) {
		for (int i = (int) start + 1; i < (int) end; i++) {
			System.out.print((char) i + ", ");
		}
	}

	public static void main(String[] args) {
		printInterposedChar('A', 'k');
	}
}
