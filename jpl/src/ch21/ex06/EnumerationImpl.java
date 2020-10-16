package ch21.ex06;

import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;

public class EnumerationImpl<E> implements Enumeration {
    private final List<E> inputs;
    private int index;

    public EnumerationImpl(List<E> inputs) {
        this.inputs = inputs;
        index = 0;
    }

    @Override
    public boolean hasMoreElements() {
        return index < inputs.size();
    }

    @Override
    public Object nextElement() {
        if (!hasMoreElements()) {
            throw new NoSuchElementException();
        }
        return inputs.get(index++);
    }
}
