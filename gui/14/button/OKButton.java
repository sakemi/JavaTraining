package button;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OKButton extends Button {
	public OKButton(Dialog d) {
		super("OK");
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				d.setVisible(false);
			}
		});
	}
}
