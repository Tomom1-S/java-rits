package ch01.ex15;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class ImplementedLookup implements ExtendedLookup {
	private String[] names;
	private Object[] values;

	@Override
	public Object find(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name))
				return values[i];
		}
		return null;
	}

	@Override
	public void add(String name, Object value) {
		names = ArrayUtils.addAll(names, name);
		values = ArrayUtils.addAll(values, value);
	}

	@Override
	public void remove(String name) {
		int index = Arrays.asList(names).indexOf(name);
		names = ArrayUtils.remove(names, index);
		values = ArrayUtils.remove(values, index);
	}

}
