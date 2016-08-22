package ch22.ex04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class User extends Observable implements Attributed {
	private Map<String, Attr> attrs = new HashMap<>();

	@Override
	public void add(Attr newAttr) {
		attrs.put(newAttr.getName(), newAttr);
		setChanged();
		notifyObservers();
	}

	@Override
	public Attr find(String attrName) {
		return attrs.get(attrName);
	}

	@Override
	public Attr remove(String attrName) {
		setChanged();
		notifyObservers();
		return attrs.remove(attrName);
	}

	@Override
	public Iterator<Attr> attrs() {
		return attrs.values().iterator();
	}

}
