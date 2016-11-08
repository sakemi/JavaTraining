package gui24.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class ColorIcon implements Icon {
	private static final int DEFAULT_HEIGHT = 10;
	private static final int DEFAULT_WIDTH = 10;

	private final Color color;
	private final int height;
	private final int width;

	public ColorIcon(Color color, int height, int width){
		this.color = color;
		this.height = height;
		this.width= width;
	}

	public ColorIcon(Color color) {
		this(color, DEFAULT_HEIGHT, DEFAULT_WIDTH);
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	@Override
	public int getIconWidth() {
		// TODO 自動生成されたメソッド・スタブ
		return width;
	}

	@Override
	public int getIconHeight() {
		// TODO 自動生成されたメソッド・スタブ
		return height;
	}

}
