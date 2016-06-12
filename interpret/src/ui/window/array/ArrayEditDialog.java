package ui.window.array;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import interpreter.Interpreter;
import ui.window.Main;
import ui.window.error.ErrorUtil;

public class ArrayEditDialog extends JDialog {
	private String selectedObj;
	private final Interpreter itp;
	private final JComboBox<String> arrayList;
	private DefaultComboBoxModel<String> model;
	private final JTextField index = new JTextField("Select index", 3);
	private final JButton ok = new JButton("OK");
	private Map<String, Object> map;
	private final Main main;

	public ArrayEditDialog(Main main, Interpreter itp, String selectedObj) {
		this.selectedObj = selectedObj;
		this.itp = itp;
		this.main = main;
		setSize(500, 300);
		initModel();
		arrayList = new JComboBox<String>(model);
		addListener();
		setLayout(new GridLayout(3, 1));
		add(arrayList);
		add(index);
		add(ok);
	}

	private void initModel() {
		map = itp.getArrays();
		String[] arrays = new String[map.size()];
		int i = 0;
		for (Map.Entry<String, Object> m : map.entrySet()) {
			arrays[i] = m.getKey();
			i++;
		}
		model = new DefaultComboBoxModel<String>(arrays);
	}

	private void addListener() {
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				try {
					String s = itp.setArrayComponent((String) arrayList.getSelectedItem(),
							Integer.parseInt(index.getText()), itp.getObjects().get(selectedObj));
					main.updateObjectList(s);
				} catch (IllegalArgumentException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1,ArrayEditDialog.this);
				}
			}
		});
	}
}
