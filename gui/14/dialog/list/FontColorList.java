package dialog.list;

import java.awt.Color;

import gui4.ClockWindow;
import property.ClockFontColor;

public class FontColorList extends PropertyList{

	public FontColorList(ClockWindow window) {
		super(window);
		add(ClockFontColor.BLACK.getLabel());
		add(ClockFontColor.RED.getLabel());
		add(ClockFontColor.BLUE.getLabel());
		add(ClockFontColor.YELLOW.getLabel());
	}

	@Override
	public void execute(String label) {
		if (label.equals(ClockFontColor.BLACK.getLabel())) {
			window.setColor(Color.BLACK);
		} else if (label.equals(ClockFontColor.RED.getLabel())) {
			window.setColor(Color.RED);
		} else if (label.equals(ClockFontColor.BLUE.getLabel())) {
			window.setColor(Color.BLUE);
		} else if (label.equals(ClockFontColor.YELLOW.getLabel())) {
			window.setColor(Color.YELLOW);
		}
	}

}
