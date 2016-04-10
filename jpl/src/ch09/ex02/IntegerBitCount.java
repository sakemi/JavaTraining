package ch09.ex02;

public class IntegerBitCount {
	public int myBitCount(int n) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((n & 0x1) == 1) {
				count++;
			}
			n >>>= 1;
		}

		return count;
	}

	/* Integer.bitCountの実装 */
	// public static int bitCount(int i) {
	// // HD, Figure 5-2
	// i = i - ((i >>> 1) & 0x55555555);					//2bitごとの1の数を2進数
	// i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);		//隣の2bitと加算し、4bitごとの1の数
	// i = (i + (i >>> 4)) & 0x0f0f0f0f;					//同様に8bit
	// i = i + (i >>> 8);									//同様に16bit
	// i = i + (i >>> 16);									//同様に32bit
	// return i & 0x3f;										//下位6bitを返す
	// }
}
