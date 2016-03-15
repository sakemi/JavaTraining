package ch05.ex02;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
	private long number;
	private long balance;
	private Action lastAct;
	private History history = new History();
	private static final int HISTORY_SIZE = 10;

	public class Action {
		private String act;
		private long amount;

		Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
			history.add(this);
		}

		public String toString() {
			return number + ": " + act + " " + amount;
		}

	}

	public class History {
		private List<Action> hist = new ArrayList<Action>();
		private int next = 0;

		public void add(Action act) {
			// 履歴が追加されるとnextの値を初期値に戻す仕様
			next = 0;
			if (hist.size() == HISTORY_SIZE) {
				hist.remove(0);
			}
			hist.add(act);
		}

		public Action next() {
			if (next == 10) {
				return null;
			}
			return hist.get(next++);
		}
	}

	public void deposit(long amount) {
		balance += amount;
		lastAct = new Action("deposit", amount);
	}

	public void withdraw(long amount) {
		balance -= amount;
		lastAct = new Action("withdraw", amount);
	}

	public History history() {
		return history;
	}
}
