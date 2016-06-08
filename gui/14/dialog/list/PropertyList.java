package dialog.list;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui4.ClockWindow;

public abstract class PropertyList extends List {
	protected final ClockWindow window;

	public PropertyList(ClockWindow window) {
		super();
		this.window = window;
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				execute(getSelectedItem());
			}
		});
	}

	public abstract void execute(String label);
}
