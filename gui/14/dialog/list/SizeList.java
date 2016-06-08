package dialog.list;

import gui4.ClockWindow;
import property.SizeProperty;

public class SizeList extends PropertyList {

	public SizeList(ClockWindow window) {
		super(window);
		add(SizeProperty.SMALL.getLabel());
		add(SizeProperty.MEDIUM.getLabel());
		add(SizeProperty.LARGE.getLabel());
		add(SizeProperty.ULTIMATE.getLabel());

	}

	@Override
	public void execute(String label) {
		// TODO 自動生成されたメソッド・スタブ
		if(label.equals(SizeProperty.SMALL.getLabel())){
			window.setFontSize(SizeProperty.SMALL.getSize());
		}else if(label.equals(SizeProperty.MEDIUM.getLabel())){
			window.setFontSize(SizeProperty.MEDIUM.getSize());
		}else if(label.equals(SizeProperty.LARGE.getLabel())){
			window.setFontSize(SizeProperty.LARGE.getSize());
		}else if(label.equals(SizeProperty.ULTIMATE.getLabel())){
			window.setFontSize(SizeProperty.ULTIMATE.getSize());
		}
	}

}
