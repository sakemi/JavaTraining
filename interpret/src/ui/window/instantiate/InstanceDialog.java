package ui.window.instantiate;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import interpreter.Interpreter;
import ui.window.Main;
import ui.window.error.ErrorUtil;

public class InstanceDialog extends JDialog {
	private final String type;
	private final String name;
	private final JTextField input = new JTextField("Input parameters", 20);
	private final JButton ok = new JButton("OK");
	private final Interpreter itp;
	private final Main main;
	private final JComboBox<String> constructorList;
	private DefaultComboBoxModel<String> model;
	private List<String> list;

	public InstanceDialog(TypeInputDialog parent, Interpreter itp, Main main) {
		super(parent, true);
		this.main = main;
		this.itp = itp;
		this.type = parent.getInput();
		this.name = parent.getValiable();
		initModel();
		constructorList = new JComboBox<String>(model);
		setSize(500, 300);
		addListener();
		setLayout(new GridLayout(3, 1));
		add(constructorList);
		add(input);
		add(ok);
	}

	private void initModel() {
		try {
			list = itp.getConstructors(type);
			model = new DefaultComboBoxModel<String>(list.toArray(new String[0]));
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			ErrorUtil.showError(e, this);
		}
	}

	private void addListener() {
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				Object[] args = createArgs(input.getText());
				try {
					Object obj = itp.instantiate(type, name, args);
					main.updateObjectList(type + " " + name);
					main.output("instantiate : " + obj.getClass().toString());
					setVisible(false);
				} catch (InstantiationException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, InstanceDialog.this);
				} catch (IllegalAccessException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, InstanceDialog.this);
				} catch (IllegalArgumentException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, InstanceDialog.this);
				} catch (InvocationTargetException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, InstanceDialog.this);
				} catch (ClassNotFoundException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, InstanceDialog.this);
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
