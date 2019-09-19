package ch02.ex11;

public class LinkedList {
	public class Node {
		public Object data;
		public Node next;
	}

	public Node head;
	public Node tail;

	public LinkedList() {
		this.head = new Node();
		this.tail = new Node();
		this.head.next = tail;
	}

	public void setNode(Object data) {
		Node node = new Node();
		tail.data = data;
		tail.next = node;
		tail = node;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Node node = head.next; node.next != null; node = node.next) {
			str.append("[" + node.data + "]" + System.lineSeparator());
		}
		return str.toString();
	}
}
