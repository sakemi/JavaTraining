package interpreter;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {
	private final Map<String, Object> objects = new HashMap<String, Object>();
	private final Map<String, Object> arrays = new HashMap<String, Object>();
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
	public Object instantiate(String type, String name, Object... args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(type);
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> c : constructors) {
			Type[] t = c.getGenericParameterTypes();
			boolean match = true;
			if (t.length != args.length) {
				continue;
			}
			if (args.length != 0) {
				for (int i = 0; i < t.length; i++) {
					if (args[i] == null) {
						throw new IllegalArgumentException();
					}
					Class<?> cls;
					if (Integer.class.isInstance(args[i])) {
						cls = int.class;
					} else if (Double.class.isInstance(args[i])) {
						cls = double.class;
					} else if (Boolean.class.isInstance(args[i])) {
						cls = boolean.class;
					} else {
						cls = args[i].getClass();
					}

					if (t[i] != cls) {
						match = false;
						break;
					}
				}
				if (match) {
					Object obj = c.newInstance(args);
					objects.put(type + " " + name, obj);
					return obj;
				}
			} else {
				Object obj = c.newInstance();
				objects.put(type + " " + name, obj);
				return obj;
			}
		}
		throw new IllegalArgumentException();
	}

	public Object generateArray(String componentType, String name, int length)
			throws NegativeArraySizeException, ClassNotFoundException {
		Class<?> clazz;
		if (componentType.equals("int")) {
			clazz = int.class;
		} else if (componentType.equals("byte")) {
			clazz = byte.class;
		} else if (componentType.equals("short")) {
			clazz = short.class;
		} else if (componentType.equals("long")) {
			clazz = long.class;
		} else if (componentType.equals("float")) {
			clazz = float.class;
		} else if (componentType.equals("double")) {
			clazz = double.class;
		} else if (componentType.equals("boolean")) {
			clazz = boolean.class;
		} else if (componentType.equals("char")) {
			clazz = char.class;
		} else {
			clazz = Class.forName(componentType);
		}
		Object array = Array.newInstance(clazz, length);
		arrays.put(componentType + "[] " + name, array);
		objects.put(componentType + "[] " + name, array);
		return array;
	}

	public String setArrayComponent(String array, int index, Object component) {
		Array.set(arrays.get(array), index, component);
		objects.put(array + "[" + index + "]", component);
		return array + "[" + index + "]";
	}

	public List<String> getConstructors(String type) throws ClassNotFoundException {
		List<String> cList = new ArrayList<String>();
		Class<?> clazz = Class.forName(type);
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> c : constructors) {
			cList.add(c.toString());
		}
		return cList;
	}

	public Object invokeMethod(String object, Method m, Object... args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object retObjClass = (m.invoke(objects.get(object), args));
		if (retObjClass != void.class) {
			objects.put("retVal" + retValID, retObjClass);
		}
		return retObjClass;
	}

	public Map<String, Method> getMethods(String object) throws ClassNotFoundException {
		Map<String, Method> mMap = new HashMap<String, Method>();
		if (objects.get(object) == null) {
			return mMap;
		}
		Class<?> clazz = objects.get(object).getClass();
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			mMap.put(m.toString(), m);
			// System.out.println(m.toString());
		}
		return mMap;
	}

	public void rewriteField(String object, Field f, Object value)
			throws IllegalArgumentException, IllegalAccessException {

		f.setAccessible(true);
		f.set(objects.get(object), value);
	}

	public Object getFieldVal(String object, Field f) throws IllegalArgumentException, IllegalAccessException {
		f.setAccessible(true);
		return f.get(objects.get(object));
	}

	public Map<String, Field> getFields(String object) throws ClassNotFoundException {
		Map<String, Field> fMap = new HashMap<String, Field>();
		if (objects.get(object) == null) {
			return fMap;
		}
		Class<?> clazz = objects.get(object).getClass();
		Field[] privateFields = clazz.getDeclaredFields();
		Field[] publicFields = clazz.getFields();
		Field[] fields = new Field[privateFields.length + publicFields.length];
		System.arraycopy(privateFields, 0, fields, 0, privateFields.length);
		System.arraycopy(publicFields, 0, fields, privateFields.length, publicFields.length);
		for (Field f : fields) {
			fMap.put(f.toString(), f);
			// System.out.println(f.toString());
		}
		return fMap;
	}

	public Object getObject(String object) {
		return objects.get(object);
	}

	public Map<String, Object> getObjects() {
		return objects;
	}

	public Map<String, Object> getArrays() {
		return arrays;
	}

	public static void main(String[] args) {
		try {
			Interpreter i = new Interpreter();
			// i.instantiate("java.awt.Frame", "f", "frame");
			// Map<String, Method> m = i.getMethods("java.awt.Frame f");
			// i.invokeMethod("java.awt.Frame f", m.get("public void
			// java.awt.Window.setSize(int,int)"), 100, 100);
			// i.invokeMethod("java.awt.Frame f", m.get("public void
			// java.awt.Window.setVisible(boolean)"), true);

			i.instantiate("dummy.Dummy", "d");
			Map<String, Field> m = i.getFields("dummy.Dummy d");
			System.out.println(i.getFieldVal("dummy.Dummy d", m.get("private final int dummy.Dummy.i")));
			i.rewriteField("dummy.Dummy d", m.get("private final int dummy.Dummy.i"), 100);
			System.out.println(i.getFieldVal("dummy.Dummy d", m.get("private final int dummy.Dummy.i")));
			// i.rewriteField("dummy.Dummy d", "int", "i", 10);
			// Dummy d2 = (Dummy) i.getObject("dummy.Dummy d");
			// System.out.println(d2.getI());
			// System.out.println(5 + d2.getI());
			// System.out.println(i.getFieldVal("dummy.Dummy d", "int", "i"));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
