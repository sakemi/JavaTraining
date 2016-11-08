package gui24.ui.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import gui24.ui.ClockPanel;
import gui24.ui.property.BackgroundColorChanger;

public class Background extends JMenu {
	private static ClockPanel panel;
	private static final String LABEL = "BackgroundColor";

	public Background(ClockPanel panel) {
		super(LABEL);
		Background.panel = panel;
		initItems();
	}

	private void initItems() {
		add(new BackgroundItem("Red", Color.RED));
		add(new BackgroundItem("Blue", Color.BLUE));
		add(new BackgroundItem("Yellow", Color.YELLOW));
		add(new BackgroundItem("Color Chooser..."));
	}

	private static class BackgroundItem extends JMenuItem implements ActionListener {
		private final Color color;

		private BackgroundItem(String label, Color color) {
			super(label);
			this.color = color;
			addActionListener(this);
		}

		private BackgroundItem(String label) {
			super(label);
			this.color = null;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			if (color == null) {
				new BackgroundColorChanger(panel).setVisible(true);
			} else {
				panel.setBackgroundColor(color);
			}
		}
	}
}
