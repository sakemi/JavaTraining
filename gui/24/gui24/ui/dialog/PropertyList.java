package gui24.ui.dialog;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui24.ui.ClockPanel;

public abstract class PropertyList<T> extends JList<T> {
	protected final ClockPanel panel;

	public PropertyList(ClockPanel panel) {
		super();
		this.panel = panel;
		addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				execute(getSelectedValue());
			}
		});
	}

	public abstract void execute(T item);
}
