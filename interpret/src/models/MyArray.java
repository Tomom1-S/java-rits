package models;

import java.lang.reflect.Array;

public class MyArray<T> {
    T[] array;

    public MyArray(final Class<T> type, final int size) {
        array = (T[]) Array.newInstance(type, size);
    }

    public T getElement(int index) {
        return array[index];
    }

    public void setElement(int index, T element) {
        array[index] = element;
    }
}
