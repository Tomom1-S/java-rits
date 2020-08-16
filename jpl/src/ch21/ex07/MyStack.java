package ch21.ex07;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyStack<E> {
    private final ArrayList<E> list;

    public MyStack() {
        this.list = new ArrayList<>();
    }

    public boolean empty() {
        return list.size() == 0;
    }

    public E peek() {
        if (empty()) {
            throw new EmptyStackException();
        }

        return list.get(list.size() - 1);
    }

    public E pop() {
        if (empty()) {
            throw new EmptyStackException();
        }

        final int end = list.size() - 1;
        final E tail = list.get(end);
        list.remove(end);
        return tail;
    }

    public void push(E item) {
        list.add(item);
    }

    public int search(E item) {
        if (empty()) {
            return -1;
        }

        int index = list.size();
        for (final E e : list) {
            if (!e.equals(item)) {
                index--;
                continue;
            }
            return index;
        }

        return -1;
    }
}
