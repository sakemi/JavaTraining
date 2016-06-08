package property;

public enum ClockBackgroundColor {
	WHITE("White"), BLUE("Blue"), RED("Red"), YELLOW("Yellow"), RANDOM("Random");

	private String label;

	ClockBackgroundColor(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
