package ch11.ex03;

import ch11.ex02.Attr;

public interface Attributed {
    void add(Attr newAttr);
    Attr find(String attrName);
    Attr remove(String attrName);
    java.util.Iterator<Attr> attrs();

}
