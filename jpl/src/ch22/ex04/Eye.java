package ch22.ex04;

import java.util.Observable;
import java.util.Observer;

public class Eye implements Observer {
	private User watching;
	// 更新回数を記録する
	private int change = 0;

	public Eye(User user) {
		this.watching = user;
		watching.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		change++;
	}

	public int getChage() {
		return change;
	}

}
