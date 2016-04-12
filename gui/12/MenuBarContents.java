package gui2;

public enum MenuBarContents {
	FONT("Font"), SIZE("Size"), COLOR("FontColor"), BACKGROUND("BackgroundColor");

	private String label;

	MenuBarContents(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public enum ClockFontSize {
		SMALL("Small", 16), MEDIUM("Medium", 30), LARGE("Large", 44),ULTIMATE("Ultimate",400);

		private int size;
		private String label;

		ClockFontSize(String label, int size) {
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

	public enum ClockBackgroundColor {
		WHITE("WhiteBack"), BLUE("BlueBack"), RED("RedBack"), YELLOW("YellowBack"),RANDOM("Random");

		private String label;

		ClockBackgroundColor(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}
}
