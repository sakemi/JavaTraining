package ui.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interpreter.Interpreter;
import ui.window.array.ArrayEditDialog;
import ui.window.field.EditFieldDialog;
import ui.window.instantiate.TypeInputDialog;
import ui.window.invoke.InvokeDialog;

public class Main extends JFrame {
	private static final String LABEL_GENERATE_BUTTON = "Instantiate";
	private static final String LABEL_INVOKE_BUTTON = "Invoke";
	private static final String LABEL_EDIT_BUTTON = "Field Edit";
	private static final String LABEL_ARRAY_BUTTON = "Set Array Compnent";
	private static final String SELECT_OBJECT_LABEL = "Select Object";
	private static final String OPERATION_LABEL = "Operation to Selected Object";
	private static final String FIELD_LABEL = "Field";
	private static final String CONSOLE_LABEL = "Console";

	private JDialog instantiate;
	private JDialog invoke;
	private JDialog field;
	private JDialog array;

	private String selectedObj;

	private final Interpreter itp = new Interpreter();
	private DefaultComboBoxModel<String> model;

	private final JButton generateButton = new JButton(LABEL_GENERATE_BUTTON);
	private final JButton invokeButton = new JButton(LABEL_INVOKE_BUTTON);
	private final JButton editFIeldButton = new JButton(LABEL_EDIT_BUTTON);
	private final JButton arrayButton = new JButton(LABEL_ARRAY_BUTTON);
	private final JLabel objSelectLabel = new JLabel(SELECT_OBJECT_LABEL);
	private final JLabel operationLabel = new JLabel(OPERATION_LABEL);
	private final JLabel fieldLabel = new JLabel(FIELD_LABEL);
	private final JLabel consoleLabel = new JLabel(CONSOLE_LABEL);
	private final JComboBox<String> objectList;
	private final DefaultListModel<String> listModel = new DefaultListModel<String>();
	private final JList<String> fieldList = new JList<String>(listModel);
	private final JScrollPane fieldPane = new JScrollPane(fieldList);
	private final JTextArea console = new JTextArea();
	private final JScrollPane consolePane = new JScrollPane(console);
	private final GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	public Main() {
		setSize(1200, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(gbl);
		String[] s = { "" };
		model = new DefaultComboBoxModel<String>(s);
		objectList = new JComboBox<String>(model);
		setComponent(operationLabel, 0, 0, 20, 10);
		setComponent(generateButton, 0, 1, 20, 10);
		setComponent(objSelectLabel, 1, 0, 20, 10, 1, 1);
		setComponent(objectList, 1, 1, 20, 10, 1, 1);
		setComponent(invokeButton, 0, 2, 20, 10);
		setComponent(editFIeldButton, 0, 3, 20, 10);
		setComponent(arrayButton, 0, 4, 20, 10);
		setComponent(fieldLabel, 1, 2, 20, 10, 1, 1);
		setComponent(fieldPane, 1, 3, 20, 10, 1, 2);
		setComponent(consoleLabel, 2, 0, 20, 10, 1, 1);
		setComponent(consolePane, 2, 1, 20, 10, 1, 5);
		console.setEditable(false);
		console.setForeground(new Color(255, 255, 255));
		console.setBackground(new Color(0, 0, 0));
		console.append(">Interpreter version 1.2\n\n>");
		initListener();
	}

	public void updateObjectList(String in) {
		model.addElement(in);
	}

	public void output(String mes) {
		console.append(mes + "\n\n>");
	}

	public void updateField() {
		Map<String, Field> fields = null;
		try {
			fields = itp.getFields(selectedObj);
		} catch (ClassNotFoundException e) {
			// ここには到達しない
			e.printStackTrace();
		}
		if (fields == null) {
			return;
		}

		listModel.clear();
		for (Map.Entry<String, Field> e : fields.entrySet()) {
			Field f = e.getValue();
			f.setAccessible(true);
			try {
				listModel.addElement(f.toGenericString() + " : " + f.get(itp.getObject(selectedObj)));
			} catch (IllegalArgumentException e1) {
				// TODO 自動生成された catch ブロック
				// 起こりえない
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO 自動生成された catch ブロック
				// 起こりえない
				e1.printStackTrace();
			}
		}
	}

	private void setComponent(Component c, int x, int y, double weightx, double weighty) {
		// gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipadx = 150;
		gbc.ipady = 10;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	private void setComponent(Component c, int x, int y, double weightx, double weighty, int width, int height) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.ipadx = 300;
		gbc.ipady = 10;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.fill = GridBagConstraints.BOTH;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	private void initListener() {
		generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				instantiate = new TypeInputDialog(Main.this, itp);
				instantiate.setVisible(true);
			}
		});

		invokeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				invoke = new InvokeDialog(itp, selectedObj, Main.this);
				invoke.setVisible(true);
			}
		});

		editFIeldButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				field = new EditFieldDialog(itp, selectedObj, Main.this);
				field.setVisible(true);
			}
		});

		arrayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				array = new ArrayEditDialog(Main.this, itp, selectedObj);
				array.setVisible(true);
			}
		});

		objectList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				selectedObj = (String) objectList.getSelectedItem();
				updateField();
			}
		});

		// fieldList.addListSelectionListener(new ListSelectionListener() {
		//
		// @Override
		// public void valueChanged(ListSelectionEvent e) {
		// // TODO 自動生成されたメソッド・スタブ
		// try {
		// Field f =
		// itp.getFields(selectedObj).get(fieldList.getSelectedValue());
		// StringBuilder out;
		// out = new
		// StringBuilder().append(fieldList.getSelectedValue()).append(":\n>").append(f.get(itp.getObject(selectedObj)));
		// output(out.toString());
		// } catch (ClassNotFoundException e1) {
		// // TODO 自動生成された catch ブロック
		// e1.printStackTrace();
		// } catch (IllegalArgumentException e1) {
		// // TODO 自動生成された catch ブロック
		// e1.printStackTrace();
		// } catch (IllegalAccessException e1) {
		// // TODO 自動生成された catch ブロック
		// e1.printStackTrace();
		// }
		// }
		//
		// });
	}
}
