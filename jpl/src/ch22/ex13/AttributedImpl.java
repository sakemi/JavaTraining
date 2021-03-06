package ch22.ex13;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ch22.ex04.Attr;
import ch22.ex04.Attributed;

public class AttributedImpl implements Attributed, Iterable<Attr> {
	protected Map<String, Attr> attrTable = new HashMap<String, Attr>();

	@Override
	public void add(Attr newAttr) {
		attrTable.put(newAttr.getName(), newAttr);
	}

	@Override
	public Attr find(String attrName) {
		return attrTable.get(attrName);
	}

	@Override
	public Attr remove(String attrName) {
		return attrTable.remove(attrName);
	}

	@Override
	public Iterator<Attr> attrs() {
		return attrTable.values().iterator();
	}

	@Override
	public Iterator<Attr> iterator() {
		return attrs();
	}

}
