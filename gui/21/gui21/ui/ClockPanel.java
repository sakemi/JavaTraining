package gui21.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import gui21.clock.Clock;

public class ClockPanel extends JPanel {
	private int fontSize = 16;
	private final Clock clock = new Clock();
	private final Font font = new Font(Font.SERIF, Font.BOLD, fontSize);

	@Override
	public void paintComponent(Graphics g) {
		drawCenteredString(g, clock.getNow(), font);
	}

	private void drawCenteredString(Graphics g, String txt, Font font) {
		Dimension size = getSize();
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (size.width - metrics.stringWidth(txt)) / 2;
		int y = ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(txt, x, y);
		g.dispose();
	}
}
