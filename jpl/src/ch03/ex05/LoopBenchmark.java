package ch03.ex05;

public class LoopBenchmark extends Benchmark {
	private int count;

	public LoopBenchmark(int count) {
		this.count = count;
	}

	@Override
	public void benchmark() {
		for (int i = 0; i < count; i++) {
		}
	}

	public static void main(String[] args) {
		int benchmarkCount = Integer.parseInt(args[0]);
		int loopCount = Integer.parseInt(args[1]);

		long time = new LoopBenchmark(loopCount).repeat(benchmarkCount);
		System.out.println(benchmarkCount + "methods in " + time + " nanoseconds");
	}

}
