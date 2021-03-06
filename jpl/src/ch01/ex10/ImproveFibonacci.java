package ch01.ex10;

public class ImproveFibonacci {
	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		int[] fibonacci = new int[MAX_INDEX];
		EvenChecker[] evenChecker = new EvenChecker[10];

		fibonacci[0] = lo;
		evenChecker[0] = new EvenChecker(lo);
		evenChecker[0].setEven(false);
		for (int i = 2; i <= MAX_INDEX; i++) {
			fibonacci[i-1] = hi;
			evenChecker[i-1] = new EvenChecker(hi);
			if (hi % 2 == 0) {
				evenChecker[i-1].setEven(true);
			} else {
				evenChecker[i-1].setEven(false);
			}
			hi = lo + hi;
			lo = hi - lo;
		}

		//目視での動作確認用
		for(int i = 0; i < MAX_INDEX; i++){
			System.out.println((i+1) + ":" + fibonacci[i] + "," + evenChecker[i].getNum() + "," + evenChecker[i].isEven());
		}


	}
}
