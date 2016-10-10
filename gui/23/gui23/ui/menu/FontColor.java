package gui23.ui.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import gui23.ui.ClockPanel;
import gui23.ui.property.FontColorChanger;

public class FontColor extends JMenu {
	private static ClockPanel panel;
	private static final String LABEL = "FontColor";

	public FontColor(ClockPanel panel) {
		super(LABEL);
		FontColor.panel = panel;
		initItems();
	}

	private void initItems() {
		add(new FontColorItem("Red", Color.RED));
		add(new FontColorItem("Blue", Color.BLUE));
		add(new FontColorItem("Yellow", Color.YELLOW));
		add(new FontColorItem("Color Chooser..."));
	}

	private static class FontColorItem extends JMenuItem implements ActionListener {
		private final Color color;

		private FontColorItem(String label, Color color) {
			super(label);
			this.color = color;
			addActionListener(this);
		}

		private FontColorItem(String label) {
			super(label);
			this.color = null;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			if (color == null) {
				new FontColorChanger(panel).setVisible(true);
			} else {
				panel.setFontColor(color);
			}
		}
	}
}
