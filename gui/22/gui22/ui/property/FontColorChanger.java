package gui22.ui.property;

import java.awt.Color;

import gui22.ui.ClockPanel;

public class FontColorChanger extends AbstractColorChanger {

	public FontColorChanger(ClockPanel clockView) {
		super(clockView);
	}

	@Override
	public void changeColor(Color color) {
		// TODO 自動生成されたメソッド・スタブ
		clockView.setFontColor(color);
	}

}
