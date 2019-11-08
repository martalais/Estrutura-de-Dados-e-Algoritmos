import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/** 
 * Valor Mais Próximo BST
 * 
 * 
 * Escreva um programa que leia os números a serem adicionados em uma BST e 
 * um número N sobre o qual se deseja encontrar o valor mais próximo a ele presente na árvore.
 * 
 * RESTRICOES
 * 
 * - Não é permitido procurar por sucessor ou predecessor. Ainda que fosse, N 
 * não precisa ser um valor presente na árvore.
 * - A implementação deve ser O(h). Isto é, você não pode percorrer
 * toda a árvore para encontrar o elemento.
 * 
 */

class ValorMaisProxBST {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ");
		
		int[] elements = getArray(input);

		BSTValoresProximos bst = new BSTValoresProximos();

		for (int elem : elements) {
			bst.add(elem);
		}
		
		int value = sc.nextInt();
		Object[] array = bst.preOrder().toArray();
		System.out.println(Arrays.toString(array));
		System.out.println(bst.searchNext(value));
		bst.preOrder();
		sc.close();
		sc.close();
	}

	private static int[] getArray(String[] input) {
		
		int[] array = new int[input.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(input[i]);
		}
		return array;
	}
}

///Valores proximos
class BSTValoresProximos {

	private Node root;

	public boolean isEmpty() {
		return this.root == null;
	}

	public void add(int element) {
		if (isEmpty()) {
			this.root = new Node(element);
		} else {
			this.root.add(element);
		}
	}

	public int searchNext(int value) {
		
		if (isEmpty()) {
			return -1;
		}
		int result = this.root.value;
		int prox = Math.abs(this.root.value - value);
		return search(value, this.root, prox, result);
	}

	private int search(int value, Node node, int prox, int result) {
		if (node != null) {
			if (Math.abs(node.value - value) < prox) {
				prox = Math.abs(node.value - value);
				result = node.value;
			}
			if (node.value == value) {
				return result;
			} else if (node.value > value) {
				return search(value, node.left, prox, result);
			} else {
				return search(value, node.right, prox, result);
			}
		}
		return result;
	}

	public ArrayList<Integer> preOrder() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		if (!isEmpty()) {
			return preOrder(this.root, array);
		}
		return array;
	}

	private ArrayList<Integer> preOrder(Node node, ArrayList<Integer> array) {
		array.add(node.value);
		if (node.left != null) {
			preOrder(node.left, array);
		}
		if (node.right != null) {
			preOrder(node.right, array);
		}
		return array;
	}
}

///NODE

class Node {

	int value;
	Node left;
	Node right;
	Node parent;

	public Node(int value) {
		this.value = value;
	}

	public Node(int value, Node parent) {
		this.value = value;
		this.parent = parent;
	}

	public void add(int value) {
		if (value > this.value) {
			if (this.right == null) {
				this.right = new Node(value, this);
			} else {
				this.right.add(value);
			}
		} else if (value < this.value) {
			if (this.left == null) {
				this.left = new Node(value, this);
			} else {
				this.left.add(value);
			}
		}
	}
	
}
