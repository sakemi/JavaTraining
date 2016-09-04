package ui.window.instantiate;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import interpreter.Interpreter;
import ui.window.Main;
import ui.window.error.ErrorUtil;

public class ArrayDialog extends JDialog {
	private final String type;
	private final String name;
	private final JTextField input = new JTextField("Input array size", 3);
	private final JButton ok = new JButton("OK");
	private final Interpreter itp;
	private final Main main;

	public ArrayDialog(TypeInputDialog parent, Interpreter itp, Main main) {
		super(parent, true);
		this.itp = itp;
		this.main = main;
		setSize(300, 150);
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
				try {
					int index = Integer.parseInt(input.getText());
					String array = type + "[] " + name;
					Object obj = itp.generateArray(type, name, index);
					main.updateObjectList(array);
					for(int i = 0; i < index; i++){
						main.updateObjectList(array + "[" + i + "] null" );
					}
					main.output("created : " + obj.toString());
				} catch (NumberFormatException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, ArrayDialog.this);
				} catch (NegativeArraySizeException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, ArrayDialog.this);
				} catch (ClassNotFoundException e1) {
					// TODO 自動生成された catch ブロック
					ErrorUtil.showError(e1, ArrayDialog.this);
				}
			}

		});
	}
}
