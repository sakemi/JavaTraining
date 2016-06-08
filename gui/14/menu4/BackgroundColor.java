package menu4;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.util.Random;

import gui4.ClockWindow;
import property.ClockBackgroundColor;
import property.Property;

public class BackgroundColor extends Menu implements ClockMenu {
	private ClockWindow w;

//	public enum ClockBackgroundColor {
//		WHITE("White"), BLUE("Blue"), RED("Red"), YELLOW("Yellow"), RANDOM("Random");
//
//		private String label;
//
//		ClockBackgroundColor(String label) {
//			this.label = label;
//		}
//
//		public String getLabel() {
//			return label;
//		}
//	}

	public BackgroundColor(ClockWindow w) {
		super(Property.BACKGROUND.getLabel());
		this.w = w;
		initItem();
	}

	private void initItem() {
		add(new BackgroundColorItem(ClockBackgroundColor.WHITE.getLabel(), Color.WHITE, w));
		add(new BackgroundColorItem(ClockBackgroundColor.RED.getLabel(), Color.RED, w));
		add(new BackgroundColorItem(ClockBackgroundColor.BLUE.getLabel(), Color.BLUE, w));
		add(new BackgroundColorItem(ClockBackgroundColor.YELLOW.getLabel(), Color.YELLOW, w));
		add(new BackgroundColorItem(ClockBackgroundColor.RANDOM.getLabel(), randomColor(), w));
	}

	public class BackgroundColorItem extends MenuItem implements ClockMenuItem {
		private Color color;
		private ClockWindow w;

		public BackgroundColorItem(String label, Color c, ClockWindow w) {
			super(label);
			this.w = w;
			color = c;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			w.setBackgroundColor(color);
		}
	}

	private Color randomColor() {
		Random r = new Random();
		Random g = new Random();
		Random b = new Random();

		return new Color(r.nextInt(255), g.nextInt(255), b.nextInt(255));
	}
}
