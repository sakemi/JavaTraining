package gui3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import menu.BackgroundColor;
import menu.ClockColor;
import menu.ClockFont;
import menu.ClockMenu;
import menu.Closer;
import menu.Size;

public class ClockWindow extends Window implements MouseMotionListener, MouseListener {
	private static final int DIGIT_X = 150;
	private static final int DIGIT_Y = 50;
	private static final int DEFAULT_FRAME_WIDTH = 300;
	private static final int DEFAULT_FRAME_HIGHT = 200;

	private String time = getNow();
	private int fontSize = 16;
	private String font = Font.SERIF;
	private Color color = Color.black;
	private Color background = Color.white;

	private final Point startPoint = new Point();
	private final PopupMenu popup = new PopupMenu();
	private final List<ClockMenu> menu = new ArrayList<ClockMenu>();

	private int windowX = 0;
	private int windowY = 0;

	private Image buf;
	private Graphics ct;

	public ClockWindow(Frame frame) {
		super(frame);
		setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HIGHT);
		addMouseMotionListener(this);
		addMouseListener(this);
		initMenu();
	}

	private void initMenu() {
		menu.add(new Size(this));
		menu.add(new ClockFont(this));
		menu.add(new ClockColor(this));
		menu.add(new BackgroundColor(this));
		for (ClockMenu cm : menu) {
			popup.add((MenuItem) cm);
		}
		popup.add(new Closer());
		add(popup);
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setBackgroundColor(Color color) {
		this.background = color;
	}

	@Override
	public void update(Graphics g) {
		time = getNow();
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Dimension d = getSize();

		buf = createImage(d.width, d.height);
		ct = buf.getGraphics();
		ct.setFont(new Font(font, Font.PLAIN, fontSize));
		ct.setColor(color);
		FontMetrics fm = ct.getFontMetrics();
		ct.drawString(time, DIGIT_X, fm.getHeight() + DIGIT_Y);
		setSize(fm.stringWidth(time) + DIGIT_X * 2, fm.getHeight() + 80);

		setBackground(background);
		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point eventLocation = e.getLocationOnScreen();
		windowX = eventLocation.x - startPoint.x;
		windowY = eventLocation.y - startPoint.y;
		setLocation(windowX, windowY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			popup.show(this, e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	private String getNow() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		String time = hour + ":" + minute + ":" + second;
		return time;
	}
}
