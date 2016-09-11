package gui22.ui.property;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui22.ui.ClockPanel;

public class FontChooser extends JDialog {

	public FontChooser(ClockPanel clockView) {
		setBounds(10, 10, 250, 300);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		Font fonts[] = ge.getAllFonts();
		Vector<String> fontNames = new Vector<String>();

		for (int i = 0; i < fonts.length; i++) {
			fontNames.addElement(fonts[i].getName());
		}
		JList<String> fontList = new JList<String>(fontNames);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.getViewport().setView(fontList);
		scrollPane1.setPreferredSize(new Dimension(200, 250));

		JPanel p1 = new JPanel();
		p1.add(scrollPane1);

		getContentPane().add(p1, BorderLayout.LINE_START);

		fontList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				clockView.setFont(fontList.getSelectedValue());
			}
		});
	}
}
