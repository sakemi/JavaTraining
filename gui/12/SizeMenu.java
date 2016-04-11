package gui2;

import java.awt.Menu;

import gui2.MenuBarContents.ClockFontSize;

public class SizeMenu extends Menu {
	public SizeMenu() {
		super(MenuBarContents.SIZE.getLabel());
	}

	public int getSize(String label) {
		if (label.equals(ClockFontSize.SMALL.getLabel())) {
			return ClockFontSize.SMALL.getSize();
		} else if (label.equals(ClockFontSize.MEDIUM.getLabel())) {
			return ClockFontSize.MEDIUM.getSize();
		} else if (label.equals(ClockFontSize.LARGE.getLabel())) {
			return ClockFontSize.LARGE.getSize();
		} else {
			throw new IllegalStateException("フォントサイズ変更内部エラー");
		}
	}


}
