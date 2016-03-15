package ch06.ex05;

public enum TrafficLight {
	GREEN {
		Color getColor() {
			return new Color("green");
		}
	},
	YELLOW() {
		Color getColor() {
			return new Color("yellow");
		}
	},
	RED() {
		Color getColor() {
			return new Color("red");
		}
	};
	abstract Color getColor();
}
