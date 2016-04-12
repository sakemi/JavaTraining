package gui2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import gui2.MenuBarContents.ClockBackgroundColor;
import gui2.MenuBarContents.ClockFontColor;
import gui2.MenuBarContents.ClockFontSize;

public class ClockFrame extends Frame implements Runnable, ActionListener {
	private final int DEFAULT_FRAME_WIDTH = 300;
	private final int DEFAULT_FRAME_HIGHT = 200;
	private final int DIGIT_X = 150;
	private final int DIGIT_Y = 50;
	private final String TITLE = "Digital Clock";
	private final int COUNT_UP = 1000;// ms
	private String time = getNow();

	// default parameter
	private int size = 16;
	private String font = Font.SERIF;
	private Color color = Color.black;
	private Color background = Color.white;

	// menu contents
	private Menu fontMenu = new FontMenu();
	private Menu sizeMenu = new SizeMenu();
	private Menu colorMenu = new ColorMenu();
	private Menu backgroundMenu = new BackgroundMenu();

	Image buf;
	Graphics ct;

	public ClockFrame() {
		setTitle(TITLE);
		setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HIGHT);
		addWindowListener(new ClockWindowAdapter());
		setMenuBar(createMenue());
	}

	@Override
	public void paint(Graphics g) {
		Dimension d = getSize();
		buf = createImage(d.width, d.height);

		ct = buf.getGraphics();
		ct.setFont(new Font(font, Font.PLAIN, size));
		ct.setColor(color);
		FontMetrics fm = ct.getFontMetrics();
		ct.drawString(time, DIGIT_X, fm.getHeight() + DIGIT_Y);
		setSize(fm.stringWidth(time) + DIGIT_X * 2, fm.getHeight() + 80);

		setBackground(background);
		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void run() {
		while (true) {
			time = getNow();
			repaint();

			try {
				Thread.sleep(COUNT_UP);
			} catch (InterruptedException e) {
				// TODO:あとで何とかするかも
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (actionCommandAnalyzer(e.getActionCommand())) {
		case FONT:
			font = e.getActionCommand();
			break;
		case SIZE:
			size = ((SizeMenu) sizeMenu).getSize(e.getActionCommand());
			break;
		case COLOR:
			color = ((ColorMenu) colorMenu).getColor(e.getActionCommand());
			break;
		case BACKGROUND:
			background = ((BackgroundMenu) backgroundMenu).getColor(e.getActionCommand());
			break;
		default:
			break;
		}
	}

	private String getNow() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		String time = hour + ":" + minute + ":" + second;
		return time;
	}

	private MenuBar createMenue() {
		MenuBar mb = new MenuBar();
		// font
		mb.add(fontMenu);
		fontMenu.add(new MenuItem(Font.SERIF));
		fontMenu.add(new MenuItem(Font.SANS_SERIF));
		fontMenu.add(new MenuItem(Font.MONOSPACED));
		fontMenu.addActionListener(this);

		// size
		mb.add(sizeMenu);
		sizeMenu.add(new MenuItem(ClockFontSize.SMALL.getLabel()));
		sizeMenu.add(new MenuItem(ClockFontSize.MEDIUM.getLabel()));
		sizeMenu.add(new MenuItem(ClockFontSize.LARGE.getLabel()));
		sizeMenu.add(new MenuItem(ClockFontSize.ULTIMATE.getLabel()));
		sizeMenu.addActionListener(this);

		// color
		mb.add(colorMenu);
		colorMenu.add(new MenuItem(ClockFontColor.BLACK.getLabel()));
		colorMenu.add(new MenuItem(ClockFontColor.BLUE.getLabel()));
		colorMenu.add(new MenuItem(ClockFontColor.RED.getLabel()));
		colorMenu.add(new MenuItem(ClockFontColor.YELLOW.getLabel()));
		colorMenu.addActionListener(this);

		// background
		mb.add(backgroundMenu);
		backgroundMenu.add(new MenuItem(ClockBackgroundColor.WHITE.getLabel()));
		backgroundMenu.add(new MenuItem(ClockBackgroundColor.BLUE.getLabel()));
		backgroundMenu.add(new MenuItem(ClockBackgroundColor.RED.getLabel()));
		backgroundMenu.add(new MenuItem(ClockBackgroundColor.YELLOW.getLabel()));
		backgroundMenu.add(new MenuItem(ClockBackgroundColor.RANDOM.getLabel()));
		backgroundMenu.addActionListener(this);

		return mb;
	}

	private MenuBarContents actionCommandAnalyzer(String ac) {
		if (ac.equals(Font.SERIF) || ac.equals(Font.SANS_SERIF) || ac.equals(Font.MONOSPACED)) {
			return MenuBarContents.FONT;
		} else if (ac.equals(ClockFontSize.SMALL.getLabel()) || ac.equals(ClockFontSize.MEDIUM.getLabel())
				|| ac.equals(ClockFontSize.LARGE.getLabel()) || ac.equals(ClockFontSize.ULTIMATE.getLabel())) {
			return MenuBarContents.SIZE;
		} else if (ac.equals(ClockFontColor.BLACK.getLabel()) || ac.equals(ClockFontColor.BLUE.getLabel())
				|| ac.equals(ClockFontColor.RED.getLabel()) || ac.equals(ClockFontColor.YELLOW.getLabel())) {
			return MenuBarContents.COLOR;
		} else if (ac.equals(ClockBackgroundColor.WHITE.getLabel()) || ac.equals(ClockBackgroundColor.BLUE.getLabel())
				|| ac.equals(ClockBackgroundColor.RED.getLabel())
				|| ac.equals(ClockBackgroundColor.YELLOW.getLabel()) || ac.equals(ClockBackgroundColor.RANDOM.getLabel())) {
			return MenuBarContents.BACKGROUND;
		} else {
			throw new IllegalStateException();
		}
	}
}
