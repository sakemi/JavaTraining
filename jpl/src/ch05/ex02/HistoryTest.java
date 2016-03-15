package ch05.ex02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HistoryTest {
	private BankAccount bankAccount;
	private BankAccount.History history;

	@Before
	public void setup() {
		bankAccount = new BankAccount();
		bankAccount.deposit(200);
		bankAccount.withdraw(100);

		history = bankAccount.history();
	}

	@Test
	public void testNext() {
		// execute
		BankAccount.Action action = history.next();
		String actual1 = action.toString();
		action = history.next();
		String actual2 = action.toString();

		// test
		assertEquals("0: deposit 200", actual1);
		assertEquals("0: withdraw 100", actual2);
	}

	@Test
	public void testNext_Over10History() {
		// setup
		for (int i = 0; i < 9; i++) {
			bankAccount.deposit(10);
		}

		// execute
		BankAccount.Action action = history.next();
		String actual1 = action.toString();
		for (int i = 0; i < 9; i++) {
			action = history.next();
		}
		String actual2 = action.toString();

		// test
		assertEquals("0: withdraw 100", actual1);
		assertEquals("0: deposit 10", actual2);
	}
}
