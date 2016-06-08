package property;

public enum SizeProperty {
	SMALL("Small", 16), MEDIUM("Medium", 30), LARGE("Large", 44), ULTIMATE("Ultimate", 400);

	private int size;
	private String label;

	SizeProperty(String label, int size) {
		this.label = label;
		this.size = size;
	}

	public String getLabel() {
		return label;
	}

	public int getSize() {
		return size;
	}
}
