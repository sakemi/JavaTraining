package menu;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;

public class Closer extends MenuItem implements ClockMenuItem {
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
