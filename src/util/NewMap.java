package util;

import java.util.HashMap;
import java.util.Set;

public class NewMap<A, B> extends HashMap<A, B> implements Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2637969392288514493L;

	public NewMap() {
		super();
	}

	public NewMap<A, B> clone() {
		NewMap<A, B> clone = new NewMap<>();
		Set<Entry<A, B>> es = entrySet();
		A key = null;
		B value = null;
		for (Entry<A, B> e : es) {
			key = e.getKey();
			value = e.getValue();
			clone.put(key, value);
		}
		return clone;
	}
}
