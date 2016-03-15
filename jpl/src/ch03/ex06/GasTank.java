package ch03.ex06;

public class GasTank extends EnergySource {
	@Override
	public void empty() {
		while (quantity != 0) {
			release();
		}
	}

	private void release() {
		// ガソリンを捨てる
	}
}
