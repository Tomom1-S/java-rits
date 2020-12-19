package ch22.ex04;

import ch11.ex02.Attr;
import ch11.ex03.Attributed;

import java.util.Iterator;
import java.util.Observable;

public class AttributedImpl extends Observable implements Attributed {
    @Override
    public void add(Attr newAttr) {

    }

    @Override
    public Attr find(String attrName) {
        return null;
    }

    @Override
    public Attr remove(String attrName) {
        return null;
    }

    @Override
    public Iterator<Attr> attrs() {
        return null;
    }
}
