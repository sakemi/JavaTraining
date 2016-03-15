package ch04.ex02;

public abstract class SortDouble implements SortHarness {
	private Double[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	public final SortMetrics sort(Object[] data) {
		values = (Double[]) data;
		curMetrics.init();
		doSort();
		return getMetrics();
	}

	public final SortMetrics getMetrics() {
		return curMetrics.clone();
	}

	protected final int getDataLength() {
		return values.length;
	}

	protected final double probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	protected final int compare(int i, int j) {
		curMetrics.compareCnt++;
		double d1 = values[i];
		double d2 = values[j];
		if (d1 == d2) {
			return 0;
		} else {
			return (d1 < d2 ? -1 : 1);
		}
	}

	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		double tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	abstract void doSort();
}
