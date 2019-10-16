package ch04.ex03;

public class LinkedListImpl implements LinkedList {
    private Node head = null;

    public LinkedListImpl() {
        super();
    }

    @Override
    public int getLength() {
        int length = 1;
        for (Node node = this.head; node.next != null; node = node.next) {
            length++;
        }
        return length;
    }

    @Override
    public LinkedList clone() {
        LinkedList list = new LinkedListImpl();
        for (int i = 0; i < getLength(); i++) {
            list.addLast(getData(i));
        }

        return list;
    }

    @Override
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

    @Override
    public Object getData(int index) {
        return getNode(index).data;
    }

    @Override
    public void addFirst(Object data) {
        if (head == null) {
            head = new Node(data, null);
        } else {
            head = new Node(data, head);
        }
    }

    @Override
    public void addLast(Object data) {
        if (head == null) {
            addFirst(data);
            return;
        }

        addNode(data, getLength());
    }

    @Override
    public void addNode(Object data, int index) {
        if (index == 0) {
            addFirst(data);
            return;
        }

        Node prevNode = getNode(index - 1);
        Node newNode = new Node(data, prevNode.next);
        prevNode.next = newNode;
    }

    private boolean isValidIndex(int index) {
        return 0 <= index && index <= getLength();
    }
}
