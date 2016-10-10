package gui23.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;

import gui23.ui.menu.Background;
import gui23.ui.menu.ClockFont;
import gui23.ui.menu.Closer;
import gui23.ui.menu.FontColor;
import gui23.ui.menu.FontSize;
import gui23.ui.property.PropertyDialog;

public class ClockWindow extends JWindow {
	//private static final String TITLE = "Digital Clock NX";
	private static final String LABEL_PROPERTY = "Property";
	private int x = 0;
	private int y = 0;
	private int width = 300;
	private int height = 200;
	private final JMenuBar menuBar = new JMenuBar();
	private ClockMouseListener listenler = new ClockMouseListener(this);
	private ClockPanel clockPane = new ClockPanel(listenler);
	private final PropertyDialog property = new PropertyDialog(clockPane);
	private final JPopupMenu popup = new JPopupMenu();
	private final List<JMenu> menu = new ArrayList<JMenu>();

	public ClockWindow() {
		setBounds(x, y, width, height);
		JMenuItem menuItem = new JMenuItem(LABEL_PROPERTY);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				property.setVisible(true);
			}
		});
		menuBar.add(menuItem);
		initPopupMenu();
		getContentPane().add(clockPane);
		setVisible(true);
	}

	public void showPopup(int x, int y) {
		popup.show(this, x, y);
	}

	public void fit() {
		setSize(clockPane.getPanelSize());
	}

	public void setBackGroundColor() {
		setBackground(clockPane.getBackground());
	}

	private void initPopupMenu() {
		menu.add(new FontSize(clockPane));
		menu.add(new ClockFont(clockPane));
		menu.add(new FontColor(clockPane));
		menu.add(new Background(clockPane));
		for (JMenu cm : menu) {
			popup.add(cm);
		}
		popup.add(new Closer());
		add(popup);
	}
}
