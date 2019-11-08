import java.util.Arrays;
import java.util.Scanner;

/**
 * Escreva um programa que leia uma sequencia da entrada padrão e transforma essa sequência em um heap.
 * 
 * Importante! Como vimos em sala de aula, um heap pode ser representado em um array que é preenchido ao 
 * caminhar por nível (da esquerda para a direita) em um Heap. Por exemplo, o array [30, 10, 9, 7] 
 * representa um heap com raíz 30, cujos filhos da esquerda e direita são 10 e 9 respectivamente. 
 * 
 * Por último, 7 é o filho à esquerda de 10.
 * 
 * Assuma que ele já é completo ou quase-completo da esquerda para a direita.
 * 
 */


class BuildHeap {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ");
		Integer[] elements = getArray(input);

		HeapBuildHeap<Integer> heap = new HeapBuildHeap<Integer>(10);

		heap.buildHeap(elements);

		System.out.println(Arrays.toString(heap.getHeap()));
		sc.close();
	}

	private static Integer[] getArray(String[] input) {
	
		Integer[] array = new Integer[input.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(input[i]);
		}
		return array;
	}

}

class HeapBuildHeap<T extends Comparable<T>> {

	protected T[] heap;
	protected int index = -1;

	@SuppressWarnings("unchecked")
	public HeapBuildHeap(int size) {
	
		heap = (T[]) new Comparable[size];
	
	}

	public void buildHeap(T[] array) {
	
		this.heap = array;
		this.index = array.length - 1;

		for (int i = index / 2; i >= 0; i--) {
			heapify(i);
		}
	}

	private void heapify(int position) {
	
		if (position >= 0 && position <= this.index) {

			int left = left(position);
			int right = right(position);
			int largest = position;

			if (left <= this.index && heap[left].compareTo(heap[position]) > 0) 
				largest = left;
			if (right <= this.index && heap[right].compareTo(heap[largest]) > 0) 
				largest = right;

			if (position != largest) {
				swap(heap, position, largest);
				heapify(largest);
			}

		}
	}

	public T[] getHeap() { return heap;	}

	private static int left(int i) {
		return (2 * i + 1);
	}

	private static int right(int i) {
		return (2 * i + 1) + 1;
	}

	public void swap(T[] array, int i, int j) {
		
		T aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	
	}

}