package ch02.ex08;

;

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
}
