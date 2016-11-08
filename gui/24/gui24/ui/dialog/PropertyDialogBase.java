package gui24.ui.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;

import gui24.ui.ClockPanel;

public abstract class PropertyDialogBase extends JDialog {
	protected final ClockPanel panel;
	protected final GridBagLayout gbl = new GridBagLayout();
	protected final JLabel label;
	protected JList<String> list;
	protected final JButton okButton;
	protected final JButton cancelButton;
	protected final GridBagConstraints gbc = new GridBagConstraints();

	public PropertyDialogBase(ClockPanel panel, String label) {
		super();
		this.panel = panel;
		setSize(400, 200);
		this.label = new JLabel(label);
		okButton = new JButton();
		cancelButton = new JButton();
		initButtonAction();
		setLayout(gbl);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				cancel();
			}
		});
	}

	protected abstract void save();

	protected abstract void cancel();

	protected abstract JList<String> createList();

	private void initButtonAction() {
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				save();
				setVisible(false);
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				cancel();
				setVisible(false);
			}
		});
	}

	protected void addList() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(label, gbc);
		add(label);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(list, gbc);
		add(list);
	}

	protected void addButton() {
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(cancelButton, gbc);
		add(cancelButton);

		gbc.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(okButton, gbc);
		add(okButton);
	}
}
