package gui2;

import java.awt.Color;
import java.awt.Menu;

import gui2.MenuBarContents.ClockFontColor;

public class ColorMenu extends Menu {
	public ColorMenu() {
		super(MenuBarContents.COLOR.getLabel());
	}

	public Color getColor(String label) {
		if (label.equals(ClockFontColor.BLACK.getLabel())) {
			return Color.black;
		} else if (label.equals(ClockFontColor.BLUE.getLabel())) {
			return Color.blue;
		} else if (label.equals(ClockFontColor.RED.getLabel())) {
			return Color.red;
		} else if (label.equals(ClockFontColor.YELLOW.getLabel())) {
			return Color.yellow;
		} else {
			throw new IllegalStateException("色変更内部エラー");
		}
	}
}
