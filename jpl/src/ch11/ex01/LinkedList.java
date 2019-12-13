package ch11.ex01;

public class LinkedList<E> implements Cloneable {
    private class Node {
        private E data;
        private Node next;

        Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size = 0;
    private Node head = null;

    public int getLength() {
        int length = 1;
        for (Node node = head; node.next != null; node = node.next) {
            length++;
        }
        return length;
    }

    public LinkedList clone() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < getLength(); i++) {
            list.addLast(getData(i));
        }

        return list;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < getLength(); i++) {
            str.append("[" + getData(i).toString() + "]" + System.lineSeparator());
        }
        return str.toString();
    }

    public Node getNode(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException();
        }

        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public E getData(int index) {
        return getNode(index).data;
    }

    public void addFirst(E data) {
        if (head == null) {
            head = new Node(data, null);
        } else {
            head = new Node(data, head);
        }

        size++;
    }

    public void addLast(E data) {
        if (head == null) {
            addFirst(data);
            return;
        }

        addNode(data, getLength());
    }

    public void addNode(E data, int index) {
        if (index == 0) {
            addFirst(data);
            return;
        }

        Node prevNode = getNode(index - 1);
        Node newNode = new Node(data, prevNode.next);
        prevNode.next = newNode;
        size++;
    }

    public void setData(E data, int index) {
        Node node = getNode(index);
        node.data = data;
    }

    private boolean isValidIndex(int index) {
        return 0 <= index && index <= size;
    }
}
