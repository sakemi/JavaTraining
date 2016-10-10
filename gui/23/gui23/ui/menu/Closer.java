package gui23.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class Closer extends JMenuItem implements ActionListener{
	private static final String label = "Close";

	public Closer() {
		super(label);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		System.exit(0);
	}

}
