package ch19.ex02;

/**
 * LinkedList class for exercise 2.16
 *
 * @author Tomomi Suzuki
 * @version 1.0
 * @since 1.0
 */
public class LinkedList {
    /**
     * The each node of this list
     */
    private class Node {
        /**
         * The each element
         */
        private Object data;
        /**
         * The next node
         */
        private Node next;
    }

    /**
     * The first node of this list
     */
    private Node head;
    /**
     * The last node of this list
     */
    private Node tail;

    /**
     * Creates a new linked list with length 1
     */
    public LinkedList() {
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
    }

    /**
     * Returns the length of this list
     */
    public int getLength() {
        int size = 0;
        for (Node node = head.next; node.next != null; node = node.next) {
            size++;
        }
        return size;
    }

    /**
     * Returns a string of the form
     * <pre>
     *     {@code
     *     [Node1.data]
     *     [Node2.data]
     *     }
     * </pre>
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Node node = head.next; node.next != null; node = node.next) {
            str.append("[" + node.data + "]" + System.lineSeparator());
        }
        return str.toString();
    }

    /**
     * Returns the node specified by the index
     *
     * @param index The index to specify the node
     */
    public Node getNode(int index) {
        Node node = head;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * Returns the node's data specified by the index
     *
     * @param index The index to specify the node
     */
    public Object getData(int index) {
        return getNode(index).data;
    }

    /**
     * Returns the next node of the node specified by the index
     *
     * @param index The index to specify the node
     */
    public Node getNext(int index) {
        return getNode(index).next;
    }

    /**
     * Returns the next node's data of the node specified by the index
     *
     * @param index The index to specify the node
     */
    public Object getNextData(int index) {
        return getNext(index).data;
    }

    /**
     * Create the new node to the end of this list
     *
     * @param data The data for new node
     */
    public void setNode(Object data) {
        Node node = new Node();
        tail.data = data;
        tail.next = node;
        tail = node;
    }

    /**
     * Create the new node to the end of this list
     *
     * @param data  The data for the specified node
     * @param index The index to specify the node
     */
    public void setData(Object data, int index) {
        Node node = getNode(index);
        node.data = data;
    }
}
