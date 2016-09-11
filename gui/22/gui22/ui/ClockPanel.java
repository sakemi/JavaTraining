package gui22.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import gui21.clock.Clock;

public class ClockPanel extends JPanel {
	private int fontSize = 16;
	private String font = Font.SERIF;
	private int style = Font.BOLD;
	private Color fontColor;
	private Color background;
	private final Clock clock = new Clock();
	private Font clockFont = new Font(font, style, fontSize);
	private Dimension panelSize = new Dimension(200, 100);

	public ClockPanel() {
		setOpaque(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(fontColor);
		drawCenteredString(g, clock.getNow(), clockFont);
	}

	private void drawCenteredString(Graphics g, String txt, Font font) {
		Dimension size = getSize();
		FontMetrics metrics = g.getFontMetrics(font);
		panelSize = new Dimension(metrics.stringWidth(txt) + 100, metrics.getHeight() + 100);
		int x = (size.width - metrics.stringWidth(txt)) / 2;
		int y = ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(txt, x, y);
		g.dispose();
	}

	public Dimension getPanelSize() {
		return panelSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		updateClockFont();
	}

	public void setFont(String font) {
		this.font = font;
		updateClockFont();
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
		updateClockFont();
	}

	public void setBackgroundColor(Color background) {
		this.background = background;
		setBackground(background);
	}

	public Color getBackgroundColor(){
		return this.background;
	}

	private void updateClockFont() {
		clockFont = new Font(font, style, fontSize);
	}
}
