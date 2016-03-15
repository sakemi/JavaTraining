package ch03.ex06;

public abstract class EnergySource {
	double quantity;

	public double getQuantity() {
		return quantity;
	}

	abstract void empty();
}
