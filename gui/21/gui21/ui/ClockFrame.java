package gui21.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ClockFrame extends JFrame {
	private static final String TITLE = "Digital Clock NX";
	private int x = 0;
	private int y = 0;
	private int width = 300;
	private int height = 200;
	private JPanel clockPane = new ClockPanel();

	public ClockFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(x, y, width, height);
		setTitle(TITLE);
		getContentPane().add(clockPane);
		setVisible(true);
	}
}
