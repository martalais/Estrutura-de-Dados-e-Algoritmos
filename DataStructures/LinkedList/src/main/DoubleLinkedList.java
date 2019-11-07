package main;

/**
 * DoubleLinkedList recursiva.
 * 
 * Uma lista duplamente encadeada possui alem da referencia para o proximo, 
 * uma referencia para o anterior.
 * 
 * @author marta
 *
 */
public class DoubleLinkedList {
	
	private Node head;
	
	public DoubleLinkedList() {
		this.head = null;
	}

	public boolean isEmpty() {
		return this.head == null;	
	}
	
	public int sum() {
		return sum(this.head);
	}
	
	private int sum(Node node) {
		
		if(node == null) {
			return 0;
		} else {
			return node.value + sum(node.next);
		}
		
	}

	public void removeLast() {
		
		if (isEmpty()) return;
		
		if (this.head.next == null)
			this.head = null;
		else
			this.removeLast(this.head);
		
	}
	
	private void removeLast(Node node) {
		
		if (node.next == null) {
			node.previous.next = null;
		} else {
			removeLast(node.next);	
		}
		
		
	}

	
	public void addLast(int n) {
		if (isEmpty()) {
			this.head = new Node(n);
		} else {
			this.addLast(this.head, n);
		}
	}
	
	private void addLast(Node node, int n) {
		
		if (node.next == null) {
			Node newNode = new Node(n);
			node.next = newNode;
			newNode.previous = node;
			return;
		} else {
			addLast(node.next, n);
		}

	}
	
	
	public String toString() {
		
		return this.toString(this.head);
	
	}

	private String toString(Node node) {
		if (node == null)
			return "";
		else {
			return node.value + " " + toString(node.next);
		}
	}
	
	
}

/**
 * O node muda na DoubleLinkedList, agora ele tambem guarda a referencia do node anterior.
 * 
 * @author marta
 *
 */
class Node {
	
	int value;
	Node next;
	Node previous;
	
	Node(int value) {
		this.value = value;
		this.next = null;
		this.previous = null;
	}
}

