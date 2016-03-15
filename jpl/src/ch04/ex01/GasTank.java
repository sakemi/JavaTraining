package ch04.ex01;

public class GasTank implements EnergySource {
	private double quantity;

	@Override
	public void empty() {
		while (quantity != 0) {
			release();
		}
	}

	@Override
	public double getQuantity() {
		return quantity;
	}

	private void release() {
		// ガソリンを捨てる
	}
}
