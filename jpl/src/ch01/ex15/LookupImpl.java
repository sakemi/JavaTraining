package ch01.ex15;

import java.util.HashMap;
import java.util.Map;

public class LookupImpl implements ImprovedLookup {
	private Map<String,Object> data = new HashMap<String,Object>();

	@Override
	public Object find(String name) {
		for(Map.Entry<String, Object> m: data.entrySet()){
			if(m.getKey().equals(name)){
				return m.getValue();
			}
		}
		return null;
	}

	@Override
	public void add(String name, Object value) {
		data.put(name, value);
	}

	@Override
	public void remove(String name) {
		data.remove(name);
	}

}
