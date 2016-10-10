package gui23.ui.property;

import java.awt.Color;

import gui23.ui.ClockPanel;

public class BackgroundColorChanger extends AbstractColorChanger {

	public BackgroundColorChanger(ClockPanel clockView) {
		super(clockView);
	}

	@Override
	public void changeColor(Color color) {
		clockView.setBackgroundColor(color);
	}

}
