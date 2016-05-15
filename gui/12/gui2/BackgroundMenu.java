package gui2;

import java.awt.Color;
import java.awt.Menu;
import java.util.Random;

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
		} else if(label.equals(ClockBackgroundColor.RANDOM.getLabel())){
			return randomColor();
		}else {
			throw new IllegalStateException("背景色変更内部エラー");
		}
	}

	private Color randomColor() {
		Random r = new Random();
		Random g = new Random();
		Random b = new Random();

		return new Color(r.nextInt(255), g.nextInt(255), b.nextInt(255));
	}
}
