package ch01.ex06;

public class Fibonacci {
	private static final String TITLE = "==Fibonacci sequence==";
	public static void main(String[] args){
		int lo = 1;
		int hi = 1;
		System.out.println(TITLE);	//タイトル
		System.out.println(lo);
		while(hi < 50){
			System.out.println(hi);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}
