package ch22.ex03;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class WhichChars {
	private Map<Integer, BitSet> bitSets = new HashMap<Integer, BitSet>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			int upper = str.charAt(i) & 0xff00;
			int lower = str.charAt(i) & 0x00ff;

			if (bitSets.get(upper) == null) {
				BitSet bs = new BitSet();
				bs.set(lower);
				bitSets.put(upper, bs);
			} else {
				BitSet b = bitSets.remove(upper);
				b.set(lower);
				bitSets.put(upper, b);
			}
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder().append("[");
		for (Map.Entry<Integer, BitSet> e : bitSets.entrySet()) {
			BitSet used = e.getValue();
			for (int i = used.nextClearBit(0); i >= 0; i = used.nextSetBit(i + 1)) {
				sb.append((char) (e.getKey() | i));
			}
		}
		return sb.append("]").toString();
	}

	public static void main(String[] args) {
		WhichChars wc = new WhichChars("Testing 1 2 3");
		System.out.println(wc.toString());
	}
}
