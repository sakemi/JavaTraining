package gui24.ui.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui24.ui.ClockPanel;
import gui24.ui.ColorIcon;
import gui24.ui.PropertyComponent;

public class PropertyDialog2 extends JDialog {
	private final ClockPanel panel;

	private final GridBagLayout gbl = new GridBagLayout();
	private final GridBagConstraints gbc = new GridBagConstraints();
	private final JPanel base = new JPanel();

	private final JLabel sizeLabel = new JLabel(PropertyComponent.FONT_SIZE.getLabel());
	private final JLabel fontLabel = new JLabel(PropertyComponent.FONT.getLabel());
	private final JLabel colorLabel = new JLabel(PropertyComponent.COLOR.getLabel());
	private final JLabel backgroundLabel = new JLabel(PropertyComponent.BACKGROUND.getLabel());

	private final JScrollPane fontPane = new JScrollPane();
	private final JScrollPane sizePane = new JScrollPane();
	private final JScrollPane colorPane = new JScrollPane();
	private final JScrollPane backgroundPane = new JScrollPane();

	private final JPanel fontPanel = new JPanel();
	private final JPanel sizePanel = new JPanel();
	private final JPanel colorPanel = new JPanel();
	private final JPanel backgroundPanel = new JPanel();

	private JList<String> fontList;
	private JList<String> sizeList;
	private JList<String> colorList;
	private JList<String> backgroundList;

	private final JButton ok = new JButton("OK");
	private final JButton cancel = new JButton("Cancel");

	public PropertyDialog2(ClockPanel panel) {
		this.panel = panel;

		initWindow();
		initListElement();
		initButton();
		initListListener();

		base.setLayout(gbl);
		addProperty(fontLabel, fontPanel, 0, 0, 0);
		addProperty(sizeLabel, sizePanel, 1, 0, 0);
		addProperty(colorLabel, colorPanel, 2, 0, 0);
		addProperty(backgroundLabel, backgroundPanel, 3, 0, 0);
		addButton(ok, cancel, 4);
		getContentPane().add(base);
	}

	private void initListListener(){
		fontList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				panel.setFont(fontList.getSelectedValue());
			}
		});

		sizeList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				panel.setFontSize(new Integer(sizeList.getSelectedValue()));
			}
		});

		colorList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				panel.setFontColor((Color)PropertyComponent.COLOR.getElements().get(colorList.getSelectedValue()));
			}
		});

		backgroundList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				panel.setBackgroundColor((Color)PropertyComponent.BACKGROUND.getElements().get(backgroundList.getSelectedValue()));
			}
		});
	}

	private void initWindow() {
		setSize(400, 700);
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				panel.cancel();
				setVisible(false);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ

			}
		});
	}

	private void initButton() {
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				setVisible(false);
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				panel.cancel();
				setVisible(false);
			}
		});
	}

	private void addButton(JButton ok, JButton cancel, int pos) {
		gbc.gridx = 1;
		gbc.gridy = pos;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(cancel, gbc);
		base.add(cancel);

		gbc.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(ok, gbc);
		base.add(ok);
	}

	private void addProperty(JLabel label, JPanel list, int pos, int padx, int pady) {
		gbc.gridx = 0;
		gbc.gridy = pos;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(label, gbc);
		base.add(label);

		gbc.gridx = 1;
		gbc.gridy = pos;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.ipadx = padx;
		gbc.ipady = pady;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(list, gbc);
		base.add(list);
	}

	private void initListElement() {
		Vector<String> fonts = new Vector<>();
		for (Entry<String, ?> e : PropertyComponent.FONT.getElements().entrySet()) {
			fonts.add(e.getKey());
		}
		fontList = new JList<>(fonts);
		fontPane.getViewport().setView(fontList);
		fontPane.setPreferredSize(new Dimension(150, 150));
		fontPanel.add(fontPane);

		Vector<String> sizes = new Vector<>();
		for (int i = 10; i <= 300; i += 10) {
			sizes.add(String.valueOf(i));
		}
		sizeList = new JList<>(sizes);
		sizeList.setSize(200, 100);
		sizePane.getViewport().setView(sizeList);
		sizePane.setPreferredSize(new Dimension(150, 150));
		sizePanel.add(sizePane);

		PropertyListCellRenderer r = new PropertyListCellRenderer();
		Vector<String> colors = new Vector<>();
		for (Entry<String, ?> e : PropertyComponent.COLOR.getElements().entrySet()) {
			colors.add(e.getKey());
		}
		colorList = new JList<>(colors);
		colorList.setCellRenderer(r);
		colorPane.getViewport().setView(colorList);
		colorPane.setPreferredSize(new Dimension(150, 50));
		colorPanel.add(colorPane);

		Vector<String> backgrounds = new Vector<>();
		for (Entry<String, ?> e : PropertyComponent.BACKGROUND.getElements().entrySet()) {
			backgrounds.add(e.getKey());
		}
		backgroundList = new JList<>(backgrounds);
		backgroundList.setCellRenderer(r);
		backgroundPane.getViewport().setView(backgroundList);
		backgroundPane.setPreferredSize(new Dimension(150, 50));
		backgroundPanel.add(backgroundPane);
	}

	class PropertyListCellRenderer implements ListCellRenderer<String> {

		@Override
		public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// TODO 自動生成されたメソッド・スタブ
			JLabel label = new JLabel(value, new ColorIcon((Color) PropertyComponent.COLOR.getElements().get(value)),
					JLabel.CENTER);
			label.setOpaque(true);
			if (isSelected) {
				label.setBackground(Color.LIGHT_GRAY);
			}
			return label;
		}

	}
}
