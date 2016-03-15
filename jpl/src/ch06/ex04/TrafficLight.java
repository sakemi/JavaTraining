package ch06.ex04;

public enum TrafficLight {
	GREEN(new Color("green")), YELLOW(new Color("yellow")), RED(new Color("red"));

	Color color;

	TrafficLight(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
