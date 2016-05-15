package menu;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;

import gui3.ClockWindow;
import gui3.Property;

public class ClockColor extends Menu implements ClockMenu{
	private ClockWindow w;

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

	public ClockColor(ClockWindow w) {
		super(Property.COLOR.getLabel());
		this.w = w;
		initItem();
	}

	private void initItem() {
		add(new ColorItem(ClockFontColor.BLACK.getLabel(),Color.BLACK, w));
		add(new ColorItem(ClockFontColor.RED.getLabel(),Color.RED, w));
		add(new ColorItem(ClockFontColor.BLUE.getLabel(),Color.BLUE, w));
		add(new ColorItem(ClockFontColor.YELLOW.getLabel(),Color.YELLOW, w));
	}

	public class ColorItem extends MenuItem implements ClockMenuItem {
		private Color color;
		private ClockWindow w;

		public ColorItem(String label,Color c, ClockWindow w) {
			super(label);
			this.w = w;
			color = c;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			w.setColor(color);
		}
	}
}
