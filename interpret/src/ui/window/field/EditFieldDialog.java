package ui.window.field;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import interpreter.Interpreter;
import ui.window.Main;
import ui.window.error.ErrorUtil;

public class EditFieldDialog extends JDialog {
	private String selectedObj;
	private final Interpreter itp;
	private final JComboBox<String> fieldList;
	private DefaultComboBoxModel<String> model;
	private final JTextField value = new JTextField("Input value", 20);
	private final JButton apply = new JButton("Apply");
	private Map<String, Field> map;
	private final Main main;

	public EditFieldDialog(Interpreter itp, String selectedObj, Main main) {
		this.selectedObj = selectedObj;
		this.itp = itp;
		this.main = main;
		setSize(500, 300);
		initModel();
		fieldList = new JComboBox<String>(model);
		addListener();
		setLayout(new GridLayout(3, 1));
		add(fieldList);
		add(value);
		add(apply);
	}

	private void initModel() {
		try {
			map = itp.getFields(selectedObj);
			String[] fields = new String[map.size()];
			int i = 0;
			for (Map.Entry<String, Field> m : map.entrySet()) {
				fields[i] = m.getKey();
				i++;
			}
			model = new DefaultComboBoxModel<String>(fields);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			ErrorUtil.showError(e, this);
		}
	}

	private void addListener() {
		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				try {
					Object arg = createArg(value.getText());
					itp.rewriteField(selectedObj, map.get((String) fieldList.getSelectedItem()), arg);
					main.updateField();
				} catch (IllegalAccessException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, EditFieldDialog.this);
				} catch (IllegalArgumentException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, EditFieldDialog.this);
				}
			}
		});
	}

	private Object createArg(String in) throws IllegalArgumentException{
		if (in.equals("")) {
			return null;
		}
		Object obj = convertLiteral(in);
		if (obj == null) {
			obj = itp.getObjects().get(in);
		}

		return obj;
	}

	private Object convertLiteral(String value) throws IllegalArgumentException {
		String type = value.split(" ")[0];
		Object obj;
		try {
			if (type.equals("int")) {
				obj = new Integer(value.split(" ")[1]);
			} else if (type.equals("double")) {
				obj = new Double(value.split(" ")[1]);
			} else if (type.equals("boolean")) {
				obj = new Boolean(value.split(" ")[1]);
			} else if (type.equals("String")) {
				obj = value.split(" ")[1];
			} else {
				return null;
			}
			return obj;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}
}
