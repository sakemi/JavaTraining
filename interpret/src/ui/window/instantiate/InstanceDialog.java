package ui.window.instantiate;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
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

	public InstanceDialog(TypeInputDialog parent, Interpreter itp, Main main) {
		super(parent, true);
		this.main = main;
		this.itp = itp;
		setSize(300, 300);
		this.type = parent.getInput();
		this.name = parent.getValiable();
		addListener();
		setLayout(new GridLayout(2, 1));
		add(input);
		add(ok);
	}

	private void addListener() {
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				Object[] args = createArgs(input.getText());
				try {
					itp.instantiate(type, name, args);
					main.updateObjectList(type + " " + name);
				} catch (InstantiationException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1,InstanceDialog.this);
				} catch (IllegalAccessException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1,InstanceDialog.this);
				} catch (IllegalArgumentException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1,InstanceDialog.this);
				} catch (InvocationTargetException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1,InstanceDialog.this);
				} catch (ClassNotFoundException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1,InstanceDialog.this);
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
