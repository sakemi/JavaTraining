package ui.window;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

	private JDialog instantiate;
	private JDialog invoke;
	private JDialog field;
	private JDialog array;

	private String selectedObj;

	private final Interpreter interpreter = new Interpreter();
	private DefaultComboBoxModel<String> model;

	private final JButton generateButton = new JButton(LABEL_GENERATE_BUTTON);
	private final JButton invokeButton = new JButton(LABEL_INVOKE_BUTTON);
	private final JButton editFIeldButton = new JButton(LABEL_EDIT_BUTTON);
	private final JButton arrayButton = new JButton(LABEL_ARRAY_BUTTON);
	private final JLabel objSelectLabel = new JLabel(SELECT_OBJECT_LABEL);
	private final JLabel operationLabel = new JLabel(OPERATION_LABEL);
	private final JComboBox<String> objectList;
	private final GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	public Main() {
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(gbl);
		String[] s = { "" };
		model = new DefaultComboBoxModel<String>(s);
		objectList = new JComboBox<String>(model);
		setComponent(operationLabel, 0, 0, 20, 10);
		setComponent(generateButton, 0, 1, 20, 10);
		setComponent(objSelectLabel, 1, 0, 20, 10);
		setComponent(objectList, 1, 1, 20, 10, 2);
		setComponent(invokeButton, 0, 2, 20, 10);
		setComponent(editFIeldButton, 0, 3, 20, 10);
		setComponent(arrayButton, 0, 4, 20, 10);
		initListener();
	}

	public void updateObjectList(String in) {
		// objectList = new JComboBox<String>();
		// Map<String, Object> objects = interpreter.getObjects();
		// for (Map.Entry<String, Object> e : objects.entrySet()) {
		// objectList.addItem(e.getKey());
		// }
		model.addElement(in);
	}

	private void setComponent(Component c, int x, int y, double weightx, double weighty) {
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipadx = 150;
		gbc.ipady = 10;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	private void setComponent(Component c, int x, int y, double weightx, double weighty, int width) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = 1;
		gbc.ipadx = 300;
		gbc.ipady = 10;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	private void initListener() {
		generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				instantiate = new TypeInputDialog(Main.this, interpreter);
				instantiate.setVisible(true);
			}
		});

		invokeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				invoke = new InvokeDialog(interpreter, selectedObj);
				invoke.setVisible(true);
			}
		});

		editFIeldButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				field = new EditFieldDialog(interpreter, selectedObj);
				field.setVisible(true);
			}
		});

		arrayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				array = new ArrayEditDialog(Main.this, interpreter, selectedObj);
				array.setVisible(true);
			}
		});

		objectList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				selectedObj = (String) objectList.getSelectedItem();
			}
		});
	}
}
