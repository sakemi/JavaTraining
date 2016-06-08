package dialog;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import button.CancelButton;
import button.OKButton;
import dialog.list.BackgroundColorList;
import gui4.ClockWindow;
import property.Property;

public class BackgroundDialog extends Dialog {
	private final GridBagLayout gbl = new GridBagLayout();
	private final Label backgroundLabel = new Label(Property.BACKGROUND.getLabel());
	private final List backgroundColorList;
	private final OKButton okButton;
	private final CancelButton cancelButton;
	private final GridBagConstraints gbc = new GridBagConstraints();

	public BackgroundDialog(ClockWindow w) {
		super(w);
		setSize(400, 200);
		okButton = new OKButton(this);
		cancelButton = new CancelButton(this, w);
		backgroundColorList = new BackgroundColorList(w);
		setLayout(gbl);
		addProperty(backgroundLabel, backgroundColorList, 0);
		addButton(okButton, cancelButton, 1);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				setVisible(false);
			}
		});
	}

	private void addProperty(Label label, List list, int pos) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = pos;
		gbc.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(label, gbc);
		add(label);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(list, gbc);
		add(list);
	}

	private void addButton(OKButton ok, CancelButton cancel, int pos) {
		gbc.gridx = 1;
		gbc.gridy = pos;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(cancel, gbc);
		add(cancel);

		gbc.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(ok, gbc);
		add(ok);
	}

	class DialogWindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}
}
