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
import dialog.list.FontColorList;
import dialog.list.FontList;
import dialog.list.SizeList;
import gui4.ClockWindow;
import property.Property;

public class FontDialog extends Dialog {
	private final GridBagLayout gbl = new GridBagLayout();
	private final Label sizeLabel = new Label(Property.SIZE.getLabel());
	private final Label fontLabel = new Label(Property.FONT.getLabel());
	private final Label colorLabel = new Label(Property.COLOR.getLabel());
	private final List sizeList;
	private final List fontList;
	private final List colorList;
	private final OKButton okButton;
	private final CancelButton cancelButton;
	private final GridBagConstraints gbc = new GridBagConstraints();

	public FontDialog(ClockWindow w) {
		super(w);
		setSize(400, 400);
		sizeList = new SizeList(w);
		fontList = new FontList(w);
		colorList = new FontColorList(w);
		okButton = new OKButton(this);
		cancelButton = new CancelButton(this, w);

		setLayout(gbl);
		addProperty(sizeLabel, sizeList, 0);
		addProperty(fontLabel, fontList, 1);
		addProperty(colorLabel, colorList, 2);
		addButton(okButton, cancelButton, 3);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				setVisible(false);
			}
		});
	}

	private void addProperty(Label label, List list, int pos) {
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
