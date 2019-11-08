import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Máximo em uma árvore binária de pesquisa
 * 
 * Implemente um programa que identifique o valor máximo em uma BST.
 *
 */
class QntElemMaioresBST {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ");
		
		int[] elements = getArray(input);
		int elem = Integer.parseInt(sc.nextLine());

		ElemMaioresBST<Integer> bst = new ElemMaioresBST<Integer>();

		for (int element : elements) {
			bst.insert(element);
		}

		System.out.println(Arrays.toString(bst.preOrder()));
		System.out.println(bst.qtdMaiores(elem));

		sc.close();
	}

	private static int[] getArray(String[] entrada) {
		
		int[] array = new int[entrada.length];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(entrada[i]);
		}
		return array;
	}
}

//////

class ElemMaioresBST<T extends Comparable<T>> {

	private ElemeMaioresBSTNode<T> root;

	public ElemMaioresBST() {
		root = new ElemeMaioresBSTNode<T>();
	}

	public ElemeMaioresBSTNode<T> getRoot() {
		return this.root;
	}

	public boolean isEmpty() {
		return root.isEmpty();
	}

	public void insert(T element) {
		if (element != null) {
			this.insert(root, element);
		}
	}

	public void insert(ElemeMaioresBSTNode<T> node, T element) {
		if (node.isEmpty()) {

			node.setData(element);
			node.setLeft(new ElemeMaioresBSTNode<T>());
			node.setRight(new ElemeMaioresBSTNode<T>());

			node.getRight().setParent(node);
			node.getLeft().setParent(node);

		} else {
			if (element.compareTo(node.getData()) > 0) {
				insert(node.getRight(), element);
			} else {
				insert(node.getLeft(), element);
			}
		}
	}

	public ElemeMaioresBSTNode<T> search(T element) {
		ElemeMaioresBSTNode<T> node = null;
		if (!isEmpty()) {
			node = this.search(root, element);
		}
		return node;
	}

	private ElemeMaioresBSTNode<T> search(ElemeMaioresBSTNode<T> node, T element) {
		ElemeMaioresBSTNode<T> result = new ElemeMaioresBSTNode<T>();
		if (!node.isEmpty()) {

			if (element.equals(node.getData())) {
				result = node;
			}

			else if (element.compareTo(node.getData()) > 0) {
				result = this.search(node.getRight(), element);
			}

			else {
				result = this.search(node.getLeft(), element);
			}

		}
		return result;
	}

	public int height() {
		return this.height(root);
	}

	private int height(ElemeMaioresBSTNode<T> node) {
		int result = -1;
		if (!node.isEmpty()) {
			result = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
		}
		return result;
	}

	public int qtdMaiores(T element) {
		return qtdMaiores(root, element);
	}

	private int qtdMaiores(ElemeMaioresBSTNode<T> node, T element) {
		int result = 0;
		if (!node.isEmpty()) {
			
			if (node.getData().compareTo(element) > 0) {
				result += 1 + size(node.getRight());
				result += qtdMaiores(node.getLeft(), element);
			} else {
				result += qtdMaiores(node.getRight(), element);
			}
			
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public T[] preOrder() {
		ArrayList<Comparable<T>> lista = new ArrayList<>();
		if (!isEmpty()) {
			preOrder(this.root, lista);
		}
		return (T[]) lista.toArray(new Comparable[size()]);
	}

	private void preOrder(ElemeMaioresBSTNode<T> node, ArrayList<Comparable<T>> lista) {
		if (!node.isEmpty()) {
			lista.add(node.getData());
			preOrder((ElemeMaioresBSTNode<T>) node.getLeft(), lista);
			preOrder((ElemeMaioresBSTNode<T>) node.getRight(), lista);
		}
	}

	private int size() {
		return this.size(root);
	}

	private int size(ElemeMaioresBSTNode<T> node) {
		int size = 0;
		if (!node.isEmpty()) {
			size = 1 + size(node.getLeft()) + size(node.getRight());
		}
		return size;
	}

}

//////

class ElemeMaioresBSTNode<T> {

	private T data;
	private ElemeMaioresBSTNode<T> left;
	private ElemeMaioresBSTNode<T> right;
	private ElemeMaioresBSTNode<T> parent;

	public ElemeMaioresBSTNode() {

	}

	public ElemeMaioresBSTNode(T data) {
		
		super();
		
		this.data = data;
		this.left = new ElemeMaioresBSTNode<T>();
		this.right = new ElemeMaioresBSTNode<T>();
		this.parent = null;
	}

	public ElemeMaioresBSTNode(T data, ElemeMaioresBSTNode<T> left, ElemeMaioresBSTNode<T> right,
			ElemeMaioresBSTNode<T> parent) {
		
		super();
		
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public boolean isEmpty() { return data == null;	}

	public T getData() { return data; }

	public void setData(T data) { this.data = data; }

	public ElemeMaioresBSTNode<T> getLeft() { return left; }

	public void setLeft(ElemeMaioresBSTNode<T> left) { this.left = left; }

	public ElemeMaioresBSTNode<T> getRight() { return right; }

	public void setRight(ElemeMaioresBSTNode<T> right) { this.right = right; }

	public ElemeMaioresBSTNode<T> getParent() { return parent; }

	public void setParent(ElemeMaioresBSTNode<T> parent) { this.parent = parent; }
}
