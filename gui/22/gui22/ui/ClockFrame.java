package gui22.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import gui22.ui.property.PropertyDialog;

public class ClockFrame extends JFrame {
	private static final String TITLE = "Digital Clock NX";
	private static final String LABEL_PROPERTY = "Property";
	private int x = 0;
	private int y = 0;
	private int width = 300;
	private int height = 200;
	private ClockPanel clockPane = new ClockPanel();
	private final JMenuBar menuBar = new JMenuBar();
	private final PropertyDialog property = new PropertyDialog(clockPane);

	public ClockFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(x, y, width, height);
		setTitle(TITLE);
		JMenuItem menuItem = new JMenuItem(LABEL_PROPERTY);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				property.setVisible(true);
			}
		});
		menuBar.add(menuItem);
		setJMenuBar(menuBar);
		getContentPane().add(clockPane);
		setVisible(true);
	}

	public void fit(){
		setSize(clockPane.getPanelSize());
	}

	public void setBackGroundColor(){
		setBackground(clockPane.getBackground());
	}
}
