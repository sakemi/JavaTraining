package gui22.ui.property;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui22.ui.ClockPanel;

public class PropertyDialog extends JDialog {
	private static final String LABEL_FONT_SIZE = "Font Size";
	private static final String LABEL_FONT = "Font";
	private static final String LABEL_FONT_COLOR = "Font Color";
	private static final String LABEL_BACKGROUND_COLOR = "Background Color";
	private static final int SPAN = 10;
	private static final int MIN = 10;
	private static final int MAX = 100;

	private final JButton fontButton = new JButton(LABEL_FONT);
	private final JButton fColorButton = new JButton(LABEL_FONT_COLOR);
	private final JButton bColorButton = new JButton(LABEL_BACKGROUND_COLOR);
	private final JLabel sizeLabel = new JLabel(LABEL_FONT_SIZE);
	private final JSlider sizeSlider = new JSlider();
	private final JPanel sliderPanel = new JPanel();
	private final JPanel buttonPanel = new JPanel();
	private final AbstractColorChanger fontColorChanger;
	private final AbstractColorChanger backgroundColorChanger;
	private final FontChooser fontChooser;

	private final ClockPanel clockView;

	public PropertyDialog(ClockPanel clockView) {
		this.clockView = clockView;
		fontColorChanger = new FontColorChanger(clockView);
		backgroundColorChanger = new BackgroundColorChanger(clockView);
		fontChooser = new FontChooser(clockView);

		initListener();
		setBounds(10, 10, 400, 150);
		setLayout(new FlowLayout());
		sliderPanel.setLayout(new FlowLayout());
		sliderPanel.add(sizeLabel);
		sliderPanel.add(sizeSlider);
		getContentPane().add(sliderPanel);
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(fontButton);
		buttonPanel.add(fColorButton);
		buttonPanel.add(bColorButton);
		getContentPane().add(buttonPanel);
	}

	private void initListener() {
		sizeSlider.setMinimum(MIN);
		sizeSlider.setMaximum(MAX);
		sizeSlider.setMajorTickSpacing(SPAN);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		sizeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				clockView.setFontSize(sizeSlider.getValue());
			}
		});

		fontButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fontChooser.setVisible(true);
			}
		});

		fColorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fontColorChanger.setVisible(true);
			}
		});

		bColorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundColorChanger.setVisible(true);
			}
		});
	}
}
