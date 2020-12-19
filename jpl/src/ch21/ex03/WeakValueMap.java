package ch21.ex03;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakValueMap<K,V> extends HashMap<K,V> {
    public WeakValueMap() {
        super();
    }

    public WeakValueMap(final int initialCapacity) {
        super(initialCapacity);
    }



}
