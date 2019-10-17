package ch04.ex03;

interface LinkedList {
    class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    int getLength();
    LinkedList clone();
    Node getNode(int index);
    Object getData(int index);
    void addFirst(Object data);
    void addLast(Object data);
    void addNode(Object data, int index);
}
