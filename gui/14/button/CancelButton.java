package button;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui4.ClockWindow;

public class CancelButton extends Button {
	public CancelButton(Dialog d, ClockWindow w) {
		super("Cancel");
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				w.cancel();
				d.setVisible(false);
			}
		});
	}
}
