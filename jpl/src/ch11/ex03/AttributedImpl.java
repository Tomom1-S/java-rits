package ch11.ex03;

import ch11.ex02.Attr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributedImpl implements Attributed, Iterable<Attr> {
    protected Map<String, Attr> attrTable = new HashMap<String, Attr>();

    @Override
    public void add(Attr newAttr) {
        attrTable.put(newAttr.getName(), newAttr);
    }

    @Override
    public Attr find(String name) {
        return attrTable.get(name);
    }

    @Override
    public Attr remove(String name) {
        return attrTable.remove(name);
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
