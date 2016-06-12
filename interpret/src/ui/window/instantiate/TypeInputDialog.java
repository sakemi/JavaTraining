package ui.window.instantiate;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import interpreter.Interpreter;
import ui.window.Main;

public class TypeInputDialog extends JDialog {
	private final Main mainFrame;
	private final TypeInputDialog inputDialog = this;
	private final JTextField typeName = new JTextField("Input binary name", 20);
	private final JTextField valiableName = new JTextField("Input parameter name", 20);
	private final JCheckBox isArray = new JCheckBox("Array");
	private final JButton ok = new JButton("OK");
	private String type = "";
	private String valiable = "";
	private final Interpreter itp;

	public TypeInputDialog(Main mainFrame, Interpreter itp) {
		super(mainFrame, true);
		this.itp = itp;
		setSize(500, 300);
		this.mainFrame = mainFrame;
		setLayout(new GridLayout(4,1));
		add(typeName);
		add(valiableName);
		add(isArray);
		add(ok);
		addOkAction();
	}

	public String getInput() {
		return type;
	}

	public String getValiable() {
		return valiable;
	}

	private void addOkAction() {
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				type = typeName.getText();
				// TODO:入力値バリデーションが必要
				valiable = valiableName.getText();
				if (isArray.isSelected()) {
					ArrayDialog array = new ArrayDialog(inputDialog, itp, mainFrame);
					array.setVisible(true);
				} else {
					InstanceDialog instance = new InstanceDialog(inputDialog, itp, mainFrame);
					instance.setVisible(true);
				}
			}

		});
	}
}
