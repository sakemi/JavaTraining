package gui24.ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import gui24.ui.dialog.ColorDialog;
import gui24.ui.dialog.PropertyDialog2;
import gui24.ui.menu.Background;
import gui24.ui.menu.ClockFont;
import gui24.ui.menu.Closer;
import gui24.ui.menu.FontColor;
import gui24.ui.menu.FontSize;
import gui24.ui.property.AbstractColorChanger;
import gui24.ui.property.BackgroundColorChanger;
import gui24.ui.property.FontChooser;
import gui24.ui.property.FontColorChanger;

public class ClockFrame extends JFrame {
	private static final String TITLE = "Digital Clock NX";
	private static final String LABEL_PROPERTY = "Property";
	private static final String LABEL_FONT = "Font";
	private static final String LABEL_FONTSIZE = "Font Size";
	private static final String LABEL_FONTCOLOR = "Font Color";
	private static final String LABEL_BACKGROUND = "Background Color";
	private int x = 0;
	private int y = 0;
	private int width = 300;
	private int height = 200;
	private final JMenuBar menuBar = new JMenuBar();
	private ClockMouseListener listenler = new ClockMouseListener(this);
	private ClockPanel clockPanel = new ClockPanel(listenler);
	// private final PropertyDialog property = new PropertyDialog(clockPane);
	private final JPopupMenu popup = new JPopupMenu();
	private final List<JMenu> menu = new ArrayList<JMenu>();
	private final AbstractColorChanger fontColorChanger = new FontColorChanger(clockPanel);
	private final AbstractColorChanger backgroundColorChanger = new BackgroundColorChanger(clockPanel);
	private final FontChooser fontChooser = new FontChooser(clockPanel);
	private final ColorDialog colorDialog = new ColorDialog(clockPanel, LABEL_FONTCOLOR);
	private final PropertyDialog2 dialog = new PropertyDialog2(clockPanel);

	public ClockFrame() {
		Point p = clockPanel.load();
		x = p.x;
		y = p.y;
		initWindow();
		initMenuItem();
		initPopupMenu();
		setJMenuBar(menuBar);
		getContentPane().add(clockPanel);
		setVisible(true);
	}

	private void initWindow() {
		setTitle(TITLE);
		setBounds(x, y, width, height);
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				clockPanel.save(getLocationOnScreen());
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}
		});
	}

	private void initMenuItem() {
		JMenuItem menu = new JMenuItem(LABEL_PROPERTY);
		// JMenuItem fontMenu = new JMenuItem(LABEL_FONT);
		// JMenuItem sizeMenu = new JMenuItem(LABEL_FONTSIZE);
		// JMenuItem colorMenu = new JMenuItem(LABEL_FONTCOLOR);
		// JMenuItem backgroundMenu = new JMenuItem(LABEL_BACKGROUND);
		// menu.add(fontMenu);
		// menu.add(sizeMenu);
		// menu.add(colorMenu);
		// menu.add(backgroundMenu);
		// fontMenu.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// fontChooser.setVisible(true);
		// }
		// });
		// sizeMenu.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// //TODO
		// }
		// });
		// colorMenu.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// //fontColorChanger.setVisible(true);
		// colorDialog.setVisible(true);
		// }
		// });
		// backgroundMenu.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// backgroundColorChanger.setVisible(true);
		// }
		// });
		menu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clockPanel.setTmp();
				dialog.setVisible(true);
			}
		});
		menuBar.add(menu);
	}

	public void showPopup(int x, int y) {
		popup.show(this, x, y);
	}

	public void fit() {
		setSize(clockPanel.getPanelSize());
	}

	public void setBackGroundColor() {
		setBackground(clockPanel.getBackground());
	}

	private void initPopupMenu() {
		menu.add(new FontSize(clockPanel));
		menu.add(new ClockFont(clockPanel));
		menu.add(new FontColor(clockPanel));
		menu.add(new Background(clockPanel));
		for (JMenu cm : menu) {
			popup.add(cm);
		}
		popup.add(new Closer(clockPanel, this));
		add(popup);
	}
}
