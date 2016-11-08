package gui24.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;

public enum PropertyComponent {
	FONT("Font", initFont()), FONT_SIZE("Size", initFontSize()), COLOR("Color", initColor()), BACKGROUND("Background",
			initColor());

	private final String label;
	private final Map<String, ?> elements;

	public String getLabel(){
		return label;
	}

	public Map<String, ?> getElements(){
		return elements;
	}

	private PropertyComponent(String label, Map<String, ?> elements) {
		this.label = label;
		this.elements = elements;
	}

	private static Map<String, Font> initFont() {
		Map<String, Font> map = new HashMap<>();
		Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for (Font f : fonts) {
			map.put(f.getName(), f);
		}
		return map;
	}

	private static Map<String, Integer> initFontSize() {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 10; i <= 300; i += 10) {
			map.put(String.valueOf(i), i);
		}
		return map;
	}

	private static Map<String, Color> initColor() {
		Map<String, Color> map = new HashMap<>();
		map.put("Red", Color.RED);
		map.put("Green", Color.GREEN);
		map.put("Blue", Color.BLUE);
		return map;
	}
}
