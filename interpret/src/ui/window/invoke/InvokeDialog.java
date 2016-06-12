package ui.window.invoke;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import interpreter.Interpreter;
import ui.window.error.ErrorUtil;

public class InvokeDialog extends JDialog {
	private String selectedObj;
	private final Interpreter itp;
	private final JComboBox<String> methodList;
	private DefaultComboBoxModel<String> model;
	private final JTextField param = new JTextField("Input parameter", 20);
	private final JButton invoke = new JButton("Invoke");
	private Map<String, Method> map;

	public InvokeDialog(Interpreter itp, String selectedObj) {
		this.selectedObj = selectedObj;
		this.itp = itp;
		setSize(500, 300);
		initModel();
		methodList = new JComboBox<String>(model);
		addListener();
		setLayout(new GridLayout(3, 1));
		add(methodList);
		add(param);
		add(invoke);
	}

	private void initModel() {
		try {
			map = itp.getMethods(selectedObj);
			String[] methods = new String[map.size()];
			int i = 0;
			for (Map.Entry<String, Method> m : map.entrySet()) {
				methods[i] = m.getKey();
				i++;
			}
			model = new DefaultComboBoxModel<String>(methods);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			ErrorUtil.showError(e,this);
		}
	}

	private void addListener() {
		invoke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				Object[] args = createArgs(param.getText());
				try {
					itp.invokeMethod(selectedObj, map.get((String) methodList.getSelectedItem()), args);
				} catch (IllegalAccessException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1,InvokeDialog.this);
				} catch (IllegalArgumentException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1,InvokeDialog.this);
				} catch (InvocationTargetException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1,InvokeDialog.this);
				}
			}

		});
	}

	private Object[] createArgs(String in) {
		if (in.equals("")) {
			return new Object[0];
		}
		String[] params = in.split(",");
		Object[] obj = new Object[params.length];
		for (int i = 0; i < params.length; i++) {
			obj[i] = convertLiteral(params[i]);
			if (obj[i] == null) {
				obj[i] = itp.getObjects().get(params[i]);
			}
		}
		return obj;
	}

	private Object convertLiteral(String params) {
		String type = params.split(" ")[0];
		Object obj;
		if (type.equals("int")) {
			obj = new Integer(params.split(" ")[1]);
		} else if (type.equals("double")) {
			obj = new Double(params.split(" ")[1]);
		} else if (type.equals("boolean")) {
			obj = new Boolean(params.split(" ")[1]);
		} else if (type.equals("String")) {
			obj = params.split(" ")[1];
		} else {
			return null;
		}
		return obj;
	}
}
