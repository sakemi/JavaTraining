package property;

public enum Property {
	FONT("Font"), SIZE("Size"), COLOR("FontColor"), BACKGROUND("BackgroundColor");

	private String label;

	Property(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
