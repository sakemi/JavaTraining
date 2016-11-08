package gui24.ui.dialog;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui24.ui.ClockPanel;

public class ColorDialog extends PropertyDialogBase{
	private final Map<String, Color> color = new HashMap<>();

	public ColorDialog(ClockPanel panel, String label) {
		super(panel, label);
		initColor();
		list = createList();
		addList();
		addButton();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	private void initColor(){
		color.put("Red", Color.RED);
		color.put("Green", Color.GREEN);
		color.put("Blue", Color.BLUE);
	}

	@Override
	protected void save() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void cancel() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected JList<String> createList() {
		Vector<String> colorName = new Vector<>();
		for(Entry<String, Color> e : color.entrySet()){
			colorName.add(e.getKey());
		}
		JList<String> list = new JList<>(colorName);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				panel.setFontColor(color.get(list.getSelectedValue()));
			}
		});

		return list;
	}

}
