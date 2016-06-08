package interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {
	private final Map<String, Object> objects = new HashMap<String, Object>();
	private int retValID = 0;

	/**
	 *
	 * @param name:バイナリ名
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public boolean instantiate(String type, String name, Object... args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(type);
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> c : constructors) {
			Type[] t = c.getGenericParameterTypes();
			boolean match = true;
			if (t.length != args.length) {
				continue;
			}
			for (int i = 0; i < t.length; i++) {
				Class<?> cls = args[i].getClass();
				if (Integer.class.isInstance(args[i])) {
					cls = int.class;
				} else if (Double.class.isInstance(args[i])) {
					cls = double.class;
				} else if (Boolean.class.isInstance(args[i])) {
					cls = boolean.class;
				}

				if (t[i] != cls) {
					match = false;
					break;
				}
			}
			if (match) {
				objects.put(type + " " + name, c.newInstance(args));
				return true;
			}
		}
		return false;
	}

	public boolean invokeMethod(String object, String method, Object... args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = objects.get(object).getClass();
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (!(method.equals(m.getName()))) {
				continue;
			}
			Type[] t = m.getGenericParameterTypes();
			if (t.length != args.length) {
				continue;
			}

			boolean match = true;
			for (int i = 0; i < t.length; i++) {
				Class<?> cls = args[i].getClass();
				if (Integer.class.isInstance(args[i])) {
					cls = int.class;
				} else if (Double.class.isInstance(args[i])) {
					cls = double.class;
				} else if (Boolean.class.isInstance(args[i])) {
					cls = boolean.class;
				}

				if (t[i] != cls) {
					match = false;
					break;
				}
			}
			if (match) {
				// objects.put("retVal" + retValID, m.invoke(clazz, args));
				m.invoke(objects.get(object), args);
				return true;
			}
		}
		return false;
	}

	public Object getObject(String name) {
		return objects.get(name);
	}

	public static void main(String[] args) {
		try {
			Interpreter i = new Interpreter();
			i.instantiate("java.awt.Frame", "f", "frame");
			i.invokeMethod("java.awt.Frame f", "setSize", 100, 100);
			i.invokeMethod("java.awt.Frame f", "setVisible", true);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
