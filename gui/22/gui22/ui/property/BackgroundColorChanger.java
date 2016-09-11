package gui22.ui.property;

import java.awt.Color;

import gui22.ui.ClockPanel;

public class BackgroundColorChanger extends AbstractColorChanger {

	public BackgroundColorChanger(ClockPanel clockView) {
		super(clockView);
	}

	@Override
	public void changeColor(Color color) {
		clockView.setBackgroundColor(color);
	}

}
