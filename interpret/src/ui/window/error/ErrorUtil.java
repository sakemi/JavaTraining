package ui.window.error;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ErrorUtil {
	public static void showError(Exception e, JDialog parent) {
		JOptionPane.showMessageDialog(parent, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}
}
