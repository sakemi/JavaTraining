package gui2;

import java.awt.Color;
import java.awt.Menu;

import gui2.MenuBarContents.ClockBackgroundColor;

public class BackgroundMenu extends Menu {
	public BackgroundMenu() {
		super(MenuBarContents.BACKGROUND.getLabel());
	}

	public Color getColor(String label) {
		if (label.equals(ClockBackgroundColor.WHITE.getLabel())) {
			return Color.white;
		} else if (label.equals(ClockBackgroundColor.BLUE.getLabel())) {
			return Color.blue;
		} else if (label.equals(ClockBackgroundColor.RED.getLabel())) {
			return Color.red;
		} else if (label.equals(ClockBackgroundColor.YELLOW.getLabel())) {
			return Color.yellow;
		} else {
			throw new IllegalStateException("背景色変更内部エラー");
		}
	}
}
