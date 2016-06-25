package ui.window.error;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ErrorUtil {
	public static void showError(Exception e, JDialog parent) {
		if (e.getCause() != null) {
			JOptionPane.showMessageDialog(parent, e.getCause().toString(), "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(parent, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
