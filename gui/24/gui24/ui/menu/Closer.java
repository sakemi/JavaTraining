package gui24.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import gui24.ui.ClockFrame;
import gui24.ui.ClockPanel;

public class Closer extends JMenuItem implements ActionListener {
	private static final String label = "Close";
	private final ClockPanel panel;
	private final ClockFrame frame;

	public Closer(ClockPanel panel, ClockFrame frame) {
		super(label);
		this.panel = panel;
		this.frame = frame;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		panel.save(frame.getLocationOnScreen());
		System.exit(0);
	}

}
