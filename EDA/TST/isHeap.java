import java.util.Scanner;

/**
 * Escreva um programa que leia uma sequencia da entrada padrão e verifica se a sequencia forma um max heap.
 * 
 * Assuma que ele já é completo ou quase-completo da esquerda para a direita.
 * 
 */

class isHeap {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] elem = getArrayInteiros(sc);
		System.out.println(isHeap(elem));
		sc.close();
	}

	public static boolean isHeap (int[] elem) {
		boolean result = true;
		
		int i = 0;
		
		while (result && left(i) < elem.length && right(i) < elem.length) {
			if (elem[left(i)] > elem[parent(i)] || elem[right(i)] > elem[parent(i)]) {
				result = false;
			}
			i++;
		}

		return result;
	}

	private static int parent(int i) {
		return (i - 1) / 2;
	}

	private static int left(int i) {
		return (2 * i + 1);
	}

	private static int right(int i) {
		return (2 * i + 1) + 1;
	}

	private static int[] getArrayInteiros(Scanner sc) {
		String[] input = sc.nextLine().split(" ");
		int[] array = new int[input.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(input[i]);
		}
		return array;
	}

}
