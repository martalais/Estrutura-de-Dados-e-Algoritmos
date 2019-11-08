import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

/**
 * Rotações em Árvores Binárias de Pesquisa
 * 
 * Escreva um programa que leia três números a serem adicionados em uma 
 * BST e aplique a(s) rotação(ões) necessária(s) para balancear a árvore.
 * 
 */

class Rotacoes {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Integer[] elements = getArray(sc);

		BST<Integer> bst = new BST<>();
		for (int elem : elements) {
			bst.insert(elem);
		}

		bst.balance();

		sc.close();
	}

	private static Integer[] getArray(Scanner sc) {
		
		String[] input = sc.nextLine().split(" ");
		Integer[] array = new Integer[input.length];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(input[i]);
		}
		
		return array;
	}
}

///////////////////////////	
	
class BSTNode<T> {
	
	private T data;
	private BSTNode<T> left;
	private BSTNode<T> right;
	private BSTNode<T> parent;
	
	public BSTNode() {
		
	}
	
	public BSTNode(T data) {	
		super();
		this.data = data;
		this.left = new BSTNode<T>();
		this.right = new BSTNode<T>();
		this.parent = null;
	}
	
	public BSTNode(T data, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
		
	}
	
	public boolean isNIL() { return data == null; }
	
	public boolean isLeaf( ) {
		return this.data != null && this.left.isNIL() && this.right.isNIL();
	}
	
	public T getData() { return this.data; }
	
	public void setData(T data) { this.data = data; }
	
	public BSTNode<T> getLeft() { return this.left;	}
	
	public BSTNode<T> getRight() { return this.right; }
	
	public BSTNode<T> getParent() {	return this.parent;	}
	
	public void setLeft(BSTNode<T> left) { this.left = left; }
	
	public void setRight(BSTNode<T> right) { this.right = right; }
	
	public void setParent(BSTNode<T> parent) { this.parent = parent; }

}

//////////////////////////

class BST<T extends Comparable<T>> {
	
	protected BSTNode<T> root;
	
	public BST() {
		root = new BSTNode<T>();		
	}
	
	public BSTNode<T> getRoot() { return this.root;	}
	
	public boolean isEmpty() { return root.isNIL(); }
	
	public void insert(T element) {
		if (element != null)
			this.insert(root, element);
	}
	
	private void insert(BSTNode<T> node, T element) {
		
		if (node.isNIL()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
		} else {
			
			if(element.compareTo(node.getData()) > 0) {
				this.insert(node.getRight(), element);
			} else {
				this.insert(node.getLeft(), element);
			}	
		}
	}
	
	@SuppressWarnings("unchecked")
	public T[] preOrder() {
		
		List<Comparable<T>> array = new ArrayList<>();
		
		if(!this.isEmpty()) {
			this.preOrder(root, array);
		}
		return (T[]) array.toArray(new Comparable[array.size()]);
	}
	
	private void preOrder(BSTNode<T> node, List<Comparable<T>> array) {
		
		if (!node.isNIL()) {
			array.add(node.getData());
			preOrder(node.getLeft(), array);
			preOrder(node.getRight(), array);
		}
		
	}
	
	public void balance() {
	
		if(isBalanced(root)) 
			System.out.println("balanceada");
		else
			rebalance(root);
	
	}
	
	public boolean isBalanced(BSTNode<T> node) {
	
		boolean balance = false;
		
		if (node.isLeaf() || node.isNIL())
			balance = true;
		else {
			if (nodeBalance(node)) 
				balance = true;
			else
				balance = false;
			
			if (balance)
				balance = isBalanced(node.getLeft()) && isBalanced(node.getRight());
			
		}
		return balance;
	}
	
	private boolean nodeBalance(BSTNode<T> node) {
		return Math.abs(balanceOfNode(node)) <= 1;
	}
	
	private int balanceOfNode(BSTNode<T> node) {
		
		int balance = 0;
		
		if(node != null && !node.isNIL()) 
			balance = height(node.getLeft()) - height(node.getRight());
		return balance;
	}
	
	private int height(BSTNode<T> node) {
		
		int result = -1;
		
		if(!node.isNIL()) {
			result = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
		}
		return result;
	}
	
	protected void rebalance(BSTNode<T> node) {
		
		int balance = this.balanceOfNode(node);
		
		if (balance > 1) {
			if (balanceOfNode((BSTNode<T>) node.getLeft()) >= 0) {
				node = rightRotation(node);
			} else {
				node.setLeft(leftRotation((BSTNode<T>) node.getLeft()));
				System.out.println(Arrays.toString(this.preOrder()));
				node = rightRotation(node);
			}
			System.out.println(Arrays.toString(this.preOrder()));
		}

		else if (balance < -1) {
		
			if (balanceOfNode((BSTNode<T>) node.getRight()) <= 0) {
				node = leftRotation(node);
			} else {
				node.setRight(rightRotation((BSTNode<T>) node.getRight()));
				System.out.println(Arrays.toString(this.preOrder()));
				node = leftRotation(node);
			}
			System.out.println(Arrays.toString(this.preOrder()));
		}
	}
	
	private BSTNode<T> leftRotation(BSTNode<T> node) {
		
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();

		if (root == node) {
			this.root = pivot;
		}

		pivot.setParent(node.getParent());

		node.setRight(pivot.getLeft());
		pivot.getLeft().setParent(node);

		pivot.setLeft(node);
		node.setParent(pivot);

		System.out.println("rot_esq(" + node.getData() + ")");
		return pivot;

	}
	
	private BSTNode<T> rightRotation(BSTNode<T> node) {
		
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();

		if (root == node) {
			this.root = pivot;
		}

		pivot.setParent(node.getParent());

		node.setLeft(pivot.getRight());
		pivot.getRight().setParent(node);

		pivot.setRight(node);
		node.setParent(pivot);

		System.out.println("rot_dir(" + node.getData() + ")");
		return pivot;
	}
	
}