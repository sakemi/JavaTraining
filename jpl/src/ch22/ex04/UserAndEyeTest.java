package ch22.ex04;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserAndEyeTest {
	private User user;
	private Eye eye;

	@Before
	public void setUp() {
		user = new User();
		eye = new Eye(user);
	}

	@Test
	public void testCountUp() {
		user.add(new Attr("attr1", "1"));
		user.add(new Attr("attr2", "2"));
		user.remove("attr1");
		// remove対象がなくても1回の操作としてカウント
		user.remove("attr3");

		int actual = eye.getChage();

		assertEquals(4, actual);
	}
}
