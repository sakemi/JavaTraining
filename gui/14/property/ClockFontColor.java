package property;

public enum ClockFontColor {
	BLACK("Black"), BLUE("Blue"), RED("Red"), YELLOW("Yellow");

	private String label;

	ClockFontColor(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
