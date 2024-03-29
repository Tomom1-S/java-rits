package ch12.ex01;

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

    public Node find(E data) throws ObjectNotFoundException {
        Node node = head;
        // 柴田さん
        // == を使って比較すると参照一致、equals なら値の一致を見る
        // 一般的に、find では equals が使われる
        while (!data.equals(node.data)) {
            node = node.next;
            if (node.next == null) {
                throw new ObjectNotFoundException();
            }
        }
        return node;
    }

    private boolean isValidIndex(int index) {
        return 0 <= index && index <= size;
    }
}
