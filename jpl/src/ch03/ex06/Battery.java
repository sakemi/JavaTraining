package ch03.ex06;

public class Battery extends EnergySource {
	@Override
	public void empty() {
		while (quantity != 0) {
			discharge();
		}
	}

	private void discharge() {
		// 放電する
	}
}
