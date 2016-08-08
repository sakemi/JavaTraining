package ch21.ex07;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.EmptyStackException;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StackTest {
	private Stack<String> stack;
	private List<String> data;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@SuppressWarnings("unchecked")
	@Before
	public void setup()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		stack = new Stack<String>();

		Class<Stack> clazz = Stack.class;
		Field f = clazz.getDeclaredField("elementData");
		f.setAccessible(true);
		data = (List<String>) f.get(stack);
	}

	@Test
	public void pushTest() {
		// execute
		stack.push("hoge");

		// test
		assertEquals(1, data.size());
		assertEquals("hoge", data.get(0));
	}

	@Test
	public void popTest() {
		// setup
		stack.push("hoge1");
		stack.push("hoge2");

		// execute
		String actual1 = stack.pop();
		String actual2 = stack.pop();

		// test
		assertEquals("hoge2", actual1);
		assertEquals("hoge1", actual2);
	}

	@Test
	public void emptyPopTest() {
		// setup
		exception.expect(EmptyStackException.class);

		// test
		stack.pop();
	}

	@Test
	public void peekTest() {
		// setup
		stack.push("hoge1");
		stack.push("hoge2");

		// execute
		String actual1 = stack.peek();
		String actual2 = stack.peek();

		// test
		assertEquals("hoge2", actual1);
		assertEquals("hoge2", actual2);
	}

	@Test
	public void emptyPeekTest() {
		// setup
		exception.expect(EmptyStackException.class);

		// test
		stack.peek();
	}

	@Test
	public void searchTest() {
		// setup
		stack.push("hoge1");
		stack.push("hoge2");

		// execute
		int actual1 = stack.search("hoge1");
		int actual2 = stack.search("hoge2");
		int actual3 = stack.search("hoge3");

		// test
		assertEquals(0, actual1);
		assertEquals(1, actual2);
		assertEquals(-1, actual3);
	}
}
