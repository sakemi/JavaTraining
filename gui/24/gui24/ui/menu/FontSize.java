package gui24.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import gui24.ui.ClockPanel;

public final class FontSize extends JMenu {
	private static ClockPanel panel;
	private static final String LABEL = "FontSize";

	public FontSize(ClockPanel panel) {
		super(LABEL);
		FontSize.panel = panel;
		initItems();
	}

	private void initItems() {
		for (int i = 10; i <= 100; i += 10) {
			add(FontSizeItem.newInstance(Integer.toString(i)));
		}
	}

	private static class FontSizeItem extends JMenuItem implements ActionListener {

		private FontSizeItem(String label) {
			super(label);
			addActionListener(this);
		}

		private static FontSizeItem newInstance(String label) {
			return new FontSizeItem(label);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			panel.setFontSize(Integer.parseInt(getText()));
		}
	}
}
