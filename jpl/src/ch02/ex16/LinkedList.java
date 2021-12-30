package ch02.ex16;

public class LinkedList {
	private class Node {
		private Object data;
		private Node next;
	}

	private Node head;
	private Node tail;

	public LinkedList() {
		this.head = new Node();
		this.tail = new Node();
		this.head.next = tail;
	}

	public int getLength() {
		int size = 0;
		for (Node node = head.next; node.next != null; node = node.next) {
			size++;
		}
		return size;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Node node = head.next; node.next != null; node = node.next) {
			str.append("[" + node.data + "]" + System.lineSeparator());
		}
		return str.toString();
	}

	public Node getNode(int index) {
		Node node = head;
		for (int i = 1; i <= index; i++) {
			node = node.next;
		}
		return node;
	}
	public Object getData(int index) {
		return getNode(index).data;
	}
	public Node getNext(int index) {
		return getNode(index).next;
	}
	public Object getNextData(int index) {
		return getNext(index).data;
	}

	public void setNode(Object data) {
		Node node = new Node();
		tail.data = data;
		tail.next = node;
		tail = node;
	}

	public void setData(Object data, int index) {
		Node node = getNode(index);
		node.data = data;
	}
}
