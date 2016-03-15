package ch04.ex02;

public interface SortHarness {
	SortMetrics sort(Object[] data);

	SortMetrics getMetrics();
}
