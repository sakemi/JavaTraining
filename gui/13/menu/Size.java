package menu;

import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;

import gui3.ClockWindow;
import gui3.Property;

public class Size extends Menu implements ClockMenu {
	enum SizeProperty {
		SMALL("Small", 16), MEDIUM("Medium", 30), LARGE("Large", 44), ULTIMATE("Ultimate", 400);

		private int size;
		private String label;

		SizeProperty(String label, int size) {
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

	private ClockWindow w;

	public Size(ClockWindow w) {
		super(Property.SIZE.getLabel());
		this.w = w;
		initItem();
	}

	private void initItem() {
		add(new SizeItem(SizeProperty.SMALL.getLabel(), SizeProperty.SMALL.getSize(), w));
		add(new SizeItem(SizeProperty.MEDIUM.getLabel(), SizeProperty.MEDIUM.getSize(), w));
		add(new SizeItem(SizeProperty.LARGE.getLabel(), SizeProperty.LARGE.getSize(), w));
		add(new SizeItem(SizeProperty.ULTIMATE.getLabel(), SizeProperty.ULTIMATE.getSize(), w));
	}

	public class SizeItem extends MenuItem implements ClockMenuItem {
		private int size;
		private ClockWindow w;

		public SizeItem(String label, int size, ClockWindow w) {
			super(label);
			this.w = w;
			this.size = size;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			w.setFontSize(size);
		}
	}

}
