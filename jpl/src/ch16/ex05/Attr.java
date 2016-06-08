package ch16.ex05;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Attr {
	private final String name;
	private Object value = null;

	public Attr(String name) {
		this.name = name;
	}

	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	@Dummy
	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public Object setValue(Object newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + "=" + value + "'";
	}
}

@Retention(value = RetentionPolicy.RUNTIME)
@interface Dummy {

}
