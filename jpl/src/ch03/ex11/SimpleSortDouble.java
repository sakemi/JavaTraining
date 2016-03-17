package ch03.ex11;

public class SimpleSortDouble extends SortDouble {
	private boolean called = false;

	protected void doSort() {
		double[] illegalData = { 0 };
		if (called) {
			// 無限ループ防止
			return;
		} else {
			called = true;
			for (int i = 0; i < getDataLength(); i++) {
				for (int j = i + 1; j < getDataLength(); j++) {
					if (compare(i, j) > 0) {
						swap(i, j);
					}
				}
			}
			// スーパークラスのsortを呼んでメトリクスを0にする
			super.sort(illegalData);
		}
	}
}
