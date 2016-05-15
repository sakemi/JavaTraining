package gui3;

public enum Property {
	FONT("Font"), SIZE("Size"), COLOR("FontColor"), BACKGROUND("BackgroundColor");

	private String label;

	Property(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
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
