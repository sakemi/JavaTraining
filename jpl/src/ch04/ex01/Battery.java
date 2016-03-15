package ch04.ex01;

public class Battery implements EnergySource {
	private double quantity;

	@Override
	public void empty() {
		while (quantity != 0) {
			discharge();
		}
	}

	@Override
	public double getQuantity() {
		return quantity;
	}

	private void discharge() {
		// 放電する
	}
}
