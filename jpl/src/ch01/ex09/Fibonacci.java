package ch01.ex09;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		// サイズがわからないから一旦リストに入れる
		List<Integer> fibonacciList = new ArrayList<Integer>();

		System.out.println("==Fibonacci sequence=="); // タイトル
		fibonacciList.add(lo);
		while (hi < 50) {
			fibonacciList.add(hi);
			hi = lo + hi;
			lo = hi - lo;
		}

		// 題意を満たすために一応配列にする
		Integer[] fibonacciArray = fibonacciList.toArray(new Integer[0]);

		for (Integer f : fibonacciArray) {
			System.out.println(f);
		}
	}
}
