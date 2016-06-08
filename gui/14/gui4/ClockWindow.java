package gui4;

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
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import dialog.BackgroundDialog;
import dialog.FontDialog;
import menu4.BackgroundColor;
import menu4.ClockColor;
import menu4.ClockFont;
import menu4.ClockMenu;
import menu4.Closer;
import menu4.Size;
import preference.ClockPreference;
import preference.Data;

public class ClockWindow extends Frame implements MouseMotionListener, MouseListener {
	private static final int DIGIT_X = 150;
	private static final int DIGIT_Y = 50;
	private static final int DEFAULT_FRAME_WIDTH = 300;
	private static final int DEFAULT_FRAME_HIGHT = 200;
	private static final String PROPERTY_LABEL = "Property";
	private static final String FONT_LABEL = "Font";
	private static final String BACKGROUND_LABEL = "Background";

	private String time = getNow();
	private int fontSize = 16;
	private String font = Font.SERIF;
	private Color color = Color.black;
	private Color background = Color.white;
	private int fontSizeTmp;
	private String fontTmp;
	private Color colorTmp;
	private Color backgroundTmp;

	private final Point startPoint = new Point();
	private final PopupMenu popup = new PopupMenu();
	private final List<ClockMenu> menu = new ArrayList<ClockMenu>();
	private final FontDialog fontDialog = new FontDialog(this);
	private final BackgroundDialog backgroundDialog = new BackgroundDialog(this);
	private final MenuBar menuBar = new MenuBar();
	private final Menu property = new Menu(PROPERTY_LABEL);
	private final MenuItem fontProperty = new MenuItem(FONT_LABEL);
	private final MenuItem backGroundProperty = new MenuItem(BACKGROUND_LABEL);
	private final Map<Data, Integer> data = new HashMap<>();
	private final ClockPreference pref = new ClockPreference(Preferences.userNodeForPackage(this.getClass()));

	private int windowX = 0;
	private int windowY = 0;

	private Image buf;
	private Graphics ct;

	public ClockWindow(Frame frame) {
		super("Digital Clock");
		setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HIGHT);
		load();
		setLocation(windowX, windowY);
		addMouseMotionListener(this);
		addMouseListener(this);
		initPopup();
		initMenuBar();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				data.put(Data.SIZE, fontSize);
				data.put(Data.COLOR_R, color.getRed());
				data.put(Data.COLOR_G, color.getGreen());
				data.put(Data.COLOR_B, color.getBlue());
				data.put(Data.BACKGROUND_R, background.getRed());
				data.put(Data.BACKGROUND_G, background.getGreen());
				data.put(Data.BACKGROUND_B, background.getBlue());
				save();
				System.exit(0);
			}
		});
	}

	public void save() {
		pref.save(data, font, getLocationOnScreen());
	}

	private void load() {
		Map<Data, Object> d = pref.load();
		this.font = (String) d.get(Data.FONT);
		this.windowX = ((Double) d.get(Data.X)).intValue();
		this.windowY = ((Double) d.get(Data.Y)).intValue();
		this.fontSize = (int) d.get(Data.SIZE);
		this.color = new Color((int) d.get(Data.COLOR_R), (int) d.get(Data.COLOR_G), (int) d.get(Data.COLOR_B));
		this.background = new Color((int) d.get(Data.BACKGROUND_R), (int) d.get(Data.BACKGROUND_G),
				(int) d.get(Data.BACKGROUND_B));
	}

	private void saveTmp() {
		fontTmp = font;
		fontSizeTmp = fontSize;
		colorTmp = color;
		backgroundTmp = background;
	}

	public void cancel() {
		font = fontTmp;
		fontSize = fontSizeTmp;
		color = colorTmp;
		background = backgroundTmp;
	}

	private void initMenuBar() {
		fontProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveTmp();
				fontDialog.setVisible(true);
			}
		});
		property.add(fontProperty);
		backGroundProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveTmp();
				backgroundDialog.setVisible(true);
			}
		});
		property.add(backGroundProperty);
		menuBar.add(property);
		setMenuBar(menuBar);
	}

	private void initPopup() {
		menu.add(new Size(this));
		menu.add(new ClockFont(this));
		menu.add(new ClockColor(this));
		menu.add(new BackgroundColor(this));
		for (ClockMenu cm : menu) {
			popup.add((MenuItem) cm);
		}
		popup.add(new Closer(this));
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
