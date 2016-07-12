package ch20.ex07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Attr {
	private final String name;
	private int value;

	public Attr(String name) {
		this.name = name;
	}

	public Attr(String name, int value) {
		this(name);
		this.value = value;
	}

	public Attr(String name, DataInputStream in) throws IOException {
		this(name, in.readInt());
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public int setValue(int newValue) {
		int oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + "=" + value + "'";
	}

	public void writeData(DataOutputStream out) throws IOException {
		out.writeInt(value);
	}
}
