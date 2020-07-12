package models;

import java.lang.reflect.Array;

public class MyArray<T> {
    private T[] array;
    private final Class cls;
    private final String name;
    private final int id;
    private static int nextId = 0;

    public MyArray(final Class<T> type, final int size) {
        this.id = nextId++;
        this.cls = type;
        this.name = cls.getName();
        array = (T[]) Array.newInstance(type, size);
    }

    public T[] getArray() {
        return array;
    }

    public T getElement(int index) {
        return array[index];
    }

    public Class getCls() {
        return cls;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setElement(int index, T element) {
        array[index] = element;
    }
}
