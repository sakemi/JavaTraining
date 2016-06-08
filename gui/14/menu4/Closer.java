package menu4;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;

import gui4.ClockWindow;

public class Closer extends MenuItem implements ClockMenuItem {
	private static final String label = "Close";
	private final ClockWindow window;

	public Closer(ClockWindow window) {
		super(label);
		this.window = window;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		window.save();
		System.exit(0);
	}

}
