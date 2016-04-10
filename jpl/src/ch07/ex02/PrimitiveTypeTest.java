package ch07.ex02;

public class PrimitiveTypeTest {
	private static byte b;
	private static short s;
	private static int i;
	private static long l;
	private static float f;
	private static double d;

	public static void main(String[] args) {
		// i = 3.5f; コンパイルエラー
		// i = 5l; コンパイルエラー
		i = (int) 5l;
		// f = 0x12p4; コンパイルエラー
		f = 0x12p0f;
		f = 40l;
		d = 40l;
	}
}