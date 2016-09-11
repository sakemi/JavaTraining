package gui22.ui.property;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui22.ui.ClockPanel;

public abstract class AbstractColorChanger extends JDialog implements ChangeListener, ActionListener {
	private final JColorChooser colorchooser;
	private final JTextField redText = new JTextField("", 2);
	private final JTextField greenText = new JTextField("", 2);
	private final JTextField blueText = new JTextField("", 2);
	protected final ClockPanel clockView;

	public AbstractColorChanger(ClockPanel clockView) {
		this.clockView = clockView;
		colorchooser = new JColorChooser(Color.GREEN);
		colorchooser.getSelectionModel().addChangeListener(this);

		setBounds(10, 10, 500, 300);

		JPanel selectPanel = new JPanel();

		JButton button = new JButton("change");
		button.addActionListener(this);

		selectPanel.add(new JLabel("RED"));
		selectPanel.add(redText);
		selectPanel.add(new JLabel("GREEN"));
		selectPanel.add(greenText);
		selectPanel.add(new JLabel("BLUE"));
		selectPanel.add(blueText);
		selectPanel.add(button);

		getContentPane().add(colorchooser, BorderLayout.CENTER);
		getContentPane().add(selectPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Color color = colorchooser.getColor();
		changeColor(color);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int red = Integer.parseInt(redText.getText());
		int green = Integer.parseInt(greenText.getText());
		int blue = Integer.parseInt(blueText.getText());

		colorchooser.setColor(red, green, blue);
	}

	abstract void changeColor(Color color);
}
