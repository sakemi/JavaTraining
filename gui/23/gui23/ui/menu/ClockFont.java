package gui23.ui.menu;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import gui23.ui.ClockPanel;

public class ClockFont extends JMenu {
	private static final String LABEL = "Font";
	private ClockPanel panel;

	public ClockFont(ClockPanel panel) {
		super(LABEL);
		this.panel = panel;
		initItems();
	}

	private void initItems() {
		Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for (Font f : fonts) {
			add(new FontItem(f));
		}
	}

	private class FontItem extends JMenuItem implements ActionListener {
		private final String name;

		private FontItem(Font font) {
			super(font.getName());
			this.name = font.getName();
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			panel.setFont(name);
		}
	}
}
