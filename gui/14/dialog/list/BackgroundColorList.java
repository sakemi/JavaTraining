package dialog.list;

import java.awt.Color;
import java.util.Random;

import gui4.ClockWindow;
import menu.BackgroundColor.ClockBackgroundColor;

public class BackgroundColorList extends PropertyList {

	public BackgroundColorList(ClockWindow window) {
		super(window);
		add(ClockBackgroundColor.WHITE.getLabel());
		add(ClockBackgroundColor.RED.getLabel());
		add(ClockBackgroundColor.BLUE.getLabel());
		add(ClockBackgroundColor.YELLOW.getLabel());
		add(ClockBackgroundColor.RANDOM.getLabel());
	}

	@Override
	public void execute(String label) {
		// TODO 自動生成されたメソッド・スタブ
		if (label.equals(ClockBackgroundColor.WHITE.getLabel())) {
			window.setBackgroundColor(Color.WHITE);
		} else if (label.equals(ClockBackgroundColor.RED.getLabel())) {
			window.setBackgroundColor(Color.RED);
		} else if (label.equals(ClockBackgroundColor.BLUE.getLabel())) {
			window.setBackgroundColor(Color.BLUE);
		} else if (label.equals(ClockBackgroundColor.YELLOW.getLabel())) {
			window.setBackgroundColor(Color.YELLOW);
		} else if (label.equals(ClockBackgroundColor.RANDOM.getLabel())) {
			window.setBackgroundColor(randomColor());
		}
	}

	private Color randomColor() {
		Random r = new Random();
		Random g = new Random();
		Random b = new Random();

		return new Color(r.nextInt(255), g.nextInt(255), b.nextInt(255));
	}

}
