package ch02.ex06;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ch02.ex01.Vehicle;;

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

	public void addNode(Object data) {
		Node node = new Node();
		tail.data = data;
		tail.next = node;
		tail = node;
	}

	public void print() {
		Node node = head.next;
		while (node.next != null) {
			System.out.println(ToStringBuilder.reflectionToString(node.data, ToStringStyle.SHORT_PREFIX_STYLE));
			node = node.next;
		}
	}

	public static void main(String[] args) {
		Vehicle toyota = new Vehicle();
		toyota.speed = 100;
		toyota.direction = 30;
		toyota.owner = "Tom";

		Vehicle benz = new Vehicle();
		benz.speed = 165.5;
		benz.direction = 200.5;
		benz.owner = "Bob";

		Vehicle ferrari = new Vehicle();
		ferrari.speed = 180.4;
		ferrari.direction = 60;
		ferrari.owner = "Fred";

		LinkedList list = new LinkedList();
		list.addNode(toyota);
		list.addNode(benz);
		list.addNode(ferrari);
		list.print();
	}
}
