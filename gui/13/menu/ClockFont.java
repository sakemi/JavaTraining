package menu;

import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;

import gui3.ClockWindow;
import gui3.Property;

public class ClockFont extends Menu implements ClockMenu {
	private ClockWindow w;

	public ClockFont(ClockWindow w) {
		super(Property.FONT.getLabel());
		this.w = w;
		initItem();
	}

	private void initItem() {
		add(new FontItem(Font.SERIF, w));
		add(new FontItem(Font.SANS_SERIF, w));
		add(new FontItem(Font.MONOSPACED, w));
	}

	public class FontItem extends MenuItem implements ClockMenuItem {
		private String font;
		private ClockWindow w;

		public FontItem(String label, ClockWindow w) {
			super(label);
			this.w = w;
			font = label;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			w.setFont(font);
		}
	}
}
