package ch13.ex04;

import java.util.ArrayList;
import java.util.List;

public class LoadTypeValue {
	public static List<Object> loadTypeValue(String typeValues) {
		List<Object> result = new ArrayList<Object>();
		String[] lines = typeValues.split("\n");
		for (String s : lines) {
			String type = s.split(" ")[0];
			String value = s.split(" ")[1];

			if (type.equals("Integer")) {
				result.add(Integer.parseInt(value));
			} else if (type.equals("Byte")) {
				result.add(Byte.parseByte(value));
			} else if (type.equals("Short")) {
				result.add(Short.parseShort(value));
			} else if (type.equals("Long")) {
				result.add(Long.parseLong(value));
			} else if (type.equals("Float")) {
				result.add(Float.parseFloat(value));
			} else if (type.equals("Double")) {
				result.add(Double.parseDouble(value));
			} else if (type.equals("Character")) {
				result.add(value.toCharArray()[0]);
			} else if (type.equals("Boolean")) {
				result.add(Boolean.parseBoolean(value));
			} else {
				throw new IllegalArgumentException();
			}
		}

		return result;
	}
}
