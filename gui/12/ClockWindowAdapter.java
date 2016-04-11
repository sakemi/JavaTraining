package gui2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClockWindowAdapter extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
