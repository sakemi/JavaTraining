package dialog.list;

import java.awt.Font;

import gui4.ClockWindow;

public class FontList extends PropertyList{

	public FontList(ClockWindow window) {
		super(window);
		add(Font.SERIF);
		add(Font.SANS_SERIF);
		add(Font.MONOSPACED);
	}

	@Override
	public void execute(String label) {
		// if (label.equals(Font.SERIF)) {
		// window.setFont(Color.WHITE);
		// } else if (label.equals(Font.SANS_SERIF)) {
		// window.setBackgroundColor(Color.RED);
		// } else if (label.equals(Font.MONOSPACED)) {
		// window.setBackgroundColor(Color.BLUE);
		// }
		window.setFont(label);
	}

}
