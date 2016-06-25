package ui.window.invoke;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import interpreter.Interpreter;
import ui.window.Main;
import ui.window.error.ErrorUtil;

public class InvokeDialog extends JDialog {
	private String selectedObj;
	private final Interpreter itp;
	private final JLabel methodLabel = new JLabel("Method list");
	private JComboBox<String> methodList;
	private DefaultComboBoxModel<String> model;
	private final JTextField param = new JTextField("Input parameter", 20);
	private final JButton invoke = new JButton("Invoke");
	private Map<String, Method> map;
	private final JLabel filterLabel = new JLabel("Filter");
	private final JTextField filter = new JTextField();
	private final JButton apply = new JButton("Apply");
	private final Main main;
	private DefaultComboBoxModel<String> filteredModel;

	public InvokeDialog(Interpreter itp, String selectedObj, Main main) {
		this.selectedObj = selectedObj;
		this.itp = itp;
		this.main = main;
		setSize(500, 300);
		initModel();
		methodList = new JComboBox<String>(model);
		addListener();
		setLayout(new GridLayout(7, 1));
		add(filterLabel);
		add(filter);
		add(apply);
		add(methodLabel);
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
			ErrorUtil.showError(e, this);
		}
	}

	private void filter(String exp) {
		List<String> fm = new ArrayList<String>();
		for (Map.Entry<String, Method> m : map.entrySet()) {
			if (m.getKey().contains(filter.getText())) {
				fm.add(m.getKey());
			}
		}
		filteredModel = new DefaultComboBoxModel<String>((String[]) fm.toArray(new String[0]));
	}

	private void addListener() {
		invoke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				Object[] args = createArgs(param.getText());
				try {
					String selectedMethod = (String) methodList.getSelectedItem();
					Object ret = itp.invokeMethod(selectedObj, map.get(selectedMethod), args);
					StringBuilder mes = new StringBuilder().append("invoke:").append(selectedMethod);
					if (ret != null) {
						mes.append("\n>").append(ret.toString());
					}
					main.output(mes.toString());
				} catch (IllegalAccessException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, InvokeDialog.this);
				} catch (IllegalArgumentException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, InvokeDialog.this);
				} catch (InvocationTargetException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, InvokeDialog.this);
				}
			}

		});

		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				filter(filter.getText());
				if (filter.getText().equals("")) {
					methodList.setModel(model);
				} else {
					methodList.setModel(filteredModel);
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
		String type = params.split(" ", 2)[0];
		Object obj;
		if (type.equals("int")) {
			obj = new Integer(params.split(" ", 2)[1]);
		} else if (type.equals("double")) {
			obj = new Double(params.split(" ", 2)[1]);
		} else if (type.equals("boolean")) {
			obj = new Boolean(params.split(" ", 2)[1]);
		} else if (type.equals("String")) {
			obj = params.split(" ", 2)[1];
		} else {
			return null;
		}
		return obj;
	}
}
