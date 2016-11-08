package gui24.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.EnumMap;
import java.util.Map;
import java.util.prefs.Preferences;

import javax.swing.JPanel;

import gui24.clock.Clock;
import gui24.preference.ClockPreference;
import gui24.preference.Data;

public class ClockPanel extends JPanel {
	private int fontSize = 16;
	private String font = Font.SERIF;
	private int style = Font.BOLD;
	private Color fontColor;
	private Color background;
	private final Clock clock = new Clock();
	private Font clockFont = new Font(font, style, fontSize);
	private Dimension panelSize = new Dimension(200, 100);

	private Font fontTmp;
	private int sizeTmp;
	private Color colorTmp;
	private Color backgroundTmp;

	private final Map<Data, Integer> data = new EnumMap<>(Data.class);
	private final ClockPreference pref = new ClockPreference(Preferences.userNodeForPackage(this.getClass()));

	public ClockPanel(ClockMouseListener listener) {
		addMouseMotionListener(listener);
		addMouseListener(listener);
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

	public Color getBackgroundColor() {
		return this.background;
	}

	public void save(Point point) {
		data.put(Data.SIZE, fontSize);
		data.put(Data.COLOR_R, fontColor.getRed());
		data.put(Data.COLOR_G, fontColor.getGreen());
		data.put(Data.COLOR_B, fontColor.getBlue());
		data.put(Data.BACKGROUND_R, background.getRed());
		data.put(Data.BACKGROUND_G, background.getGreen());
		data.put(Data.BACKGROUND_B, background.getBlue());
		pref.save(data, font, point);
	}

	public Point load() {
		Map<Data, Object> d = pref.load();
		this.font = (String) d.get(Data.FONT);
		this.fontSize = (int) d.get(Data.SIZE);
		this.fontColor = new Color((int) d.get(Data.COLOR_R), (int) d.get(Data.COLOR_G), (int) d.get(Data.COLOR_B));
		this.background = new Color((int) d.get(Data.BACKGROUND_R), (int) d.get(Data.BACKGROUND_G),
				(int) d.get(Data.BACKGROUND_B));
		setBackground(background);
		updateClockFont();
		return new Point(((Double) d.get(Data.X)).intValue(), ((Double) d.get(Data.Y)).intValue());
	}

	public void cancel() {
		clockFont = fontTmp;
		fontSize = sizeTmp;
		fontColor = colorTmp;
		background = backgroundTmp;
	}

	public void setTmp() {
		fontTmp = clockFont;
		sizeTmp = fontSize;
		colorTmp = fontColor;
		backgroundTmp = background;
	}

	private void updateClockFont() {
		clockFont = new Font(font, style, fontSize);
	}
}
