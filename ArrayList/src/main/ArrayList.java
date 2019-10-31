package main;

import java.util.Arrays;

/**
 * Resumo sobre ArrayList
 * 
 * O ArrayList consiste em, basicamente, um array que "cresce dinamicamente". As aspas estao ai justamente pq o 
 * que realmente acontece eh uma funcao de resize: um metodo que cria um novo array e transfere os elementos do
 * array original para o novo array.
 * 
 * A proposta do ArrayList eh fornecer uma API com operações de uma lista, mas esconder detalhes como remanejamento 
 * de elementos na remoção, aumento da capacidade da estrutura na adição de elementos, entre outras tarefas.
 * 
 * Vamos ver no codigo exemplo abaixo como funciona.
 * 
 * @author marta
 *
 */

public class ArrayList {
	 
	private int[] list;
	public static final int CAPACIDADE_DEFAULT = 10;	// Capacidade incial do ArrayList eh determinada aqui.
	private int size;	// Quantidade de elementos presentes na lista atualmente.
	
	/**
	 * Construtor com capacidade pre-determinada.
	 */
	public ArrayList() {
		this(CAPACIDADE_DEFAULT);		
	}
	
	/**
	 * Construtor que recebe a capacidade desejada.
	 * 
	 * @param capacidade
	 */
	public ArrayList(int capacidade) {		
		this.list = new int[capacidade];
		this.size = 0;
	}
		
	/**
	 * Metodo que checa se o ArrayList esta vazio a partir do size.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() { 		
		return this.size == 0;
	}

	/**
	 * Metodo que checa se o ArrayList esta cheio, comparando o size ao tamanho da lista.
	 * 
	 * @return boolean
	 */
	public boolean isFull() { 		
		return this.size == this.list.length;
	}
	
	/**
	 * Metodo que verifica se a nova capacidade pretendida eh atendida pelo tamanho atual da lista e, 
	 * caso nao seja, invoca resize para criar uma nova lista cujo tamanho o máximo entre o dobro da lista 
	 * original ou a capacidade nova pretendida.
	 * 
	 * @param capacidadePretendida
	 */
	private void assegureCapacidade(int capacidadePretendida) {		
		if (capacidadePretendida > this.list.length)
				resize(Math.max(this.list.length * 2, capacidadePretendida));
	}

	
	
	
	// METODOS DE ADICAO
	
	/*
	 * Adiciona um novo elemento na lista recebendo um valor.
	 * não requer um índice específico e, por isso, assume que a inserção do novo elemento
	 * deve ser feita no fim da lista, isto é, na próxima posição livre do array.
	 */
	public boolean addValor(int e) {
		assegureCapacidade(this.size + 1);
		this.list[size++] = e;
		return true;
	}

	/**
	 * Adiciona um novo elemento na lista recebendo o index e o elemento.
	 * Inclui o novo elemento na posição index e desloca os elementos à frente uma posição
	 * para a direita (shiftParaDireita).
	 *  
	 * @param index
	 * @param e
	 */
	public void addIndexValor(int index, int e) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		
		assegureCapacidade(this.size + 1);
		
		shiftParaDireita(index);
		
		this.list[index] = e;
		this.size += 1;
		
	}
	 /**
	  * Esse metodo serve para deslocar os elementos a frente da posição index 
	  * para a direita, para então incluir o elemento na posição index.
	  * 
	  * @param index
	  */
	private void shiftParaDireita(int index) {
		if (index == this.list.length - 1) 
			throw new IndexOutOfBoundsException("Não há espaço para "
					+ "efetuar o shift à direita.");
		
		for (int i = this.size; i > index; i--) {
			this.list[i] = this.list[i-1];
		}
	}
	
	/**
	 * Adiciona um novo elemento na lista recebendo o index e o elemento.
	 * Altera o valor da posicao index indicada.
	 * 
	 * @param index
	 * @param e
	 */
	public void addSet(int index, int e) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		this.list[index] = e;
	}
	
	
	
	
	
	
	// METODOS DE REMOCAO
	
	/**
	 * Remove o elemento da lista recebendo seu index.
	 * 
	 * @param index
	 * @return elemento
	 */
	public Object removeIndex(int index) {
		if (index < 0 || index >= this.size)
			return null;
		
		int e = this.get(index);
		
		shiftParaEsquerda(index);
		this.size -= 1;
		return e;
	}
	
	/**
	 * Remove o elemento da lista recebendo o valor.
	 * Nesse caso, precisa procurar o elemento antes de realizar o shift.
	 * 
	 * @param e
	 * @return boolean
	 */
	public boolean removeValue(int e) {
				
		for (int i = 0; i < size; i++) {
			if (this.list[i] == e) {
				this.removeValue(i);
				return true;
			}
		}		
		return false;
	}
	
	/**
	 * Esse metodo serve para deslocar os elementos para evitar buracos na lista.
	 *  
	 * @param index
	 */
	private void shiftParaEsquerda(int index) {
		for (int i = index; i < this.size - 1; i++) {
			this.list[i] = this.list[i+1];
		}
	}
	
	
	
	
	// METODOS DE BUSCA
	
	/**
	 * Metodo para busca quando queremos encontrar o indice em que um elemento esta.
	 * Eh executado com busca linear (O(n)), pois devem iterar sobre a lista procurando 
	 * pelo objeto passado como parametro.
	 * 
	 * @param e
	 * @return
	 */
	public int indexOf(int e) {
		
		for (int i = 0; i < size; i++)
			if (this.list[i] == e) 
				return i;
		return -1;		
	}
	
	/**
	 * Metodo para busca quando queremos verificar a presenca de um elemento na lista.
	 * Eh executado com busca linear (O(n)), pois devem iterar sobre a lista procurando 
	 * pelo objeto passado como parametro.
	 * 
	 * @param e
	 * @return
	 */
	public boolean contains(int e) {
		return this.indexOf(e) != 1;
	}
	
	/**
	 * Metodo para busca quando queremos acessar o elemento em um determinado indice.
	 * Eh executado em tempo constante O(1), pois o índice eh fornecido como parametro.
	 * 
	 * @param index
	 * @return
	 */
	public int get(int index) {
		if (index >= size || index <0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			return this.list[index];
		}
	}
	
	
	/**
	 * Metodo que cria um novo array e transfere os elementos do array original para ele.
	 * O(n)
	 * 
	 * @param novaCapacidade
	 */
	private void resize(int novaCapacidade) {
		int[] newList = new int[novaCapacidade];
		for (int i = 0; i < this.list.length; i++)
			newList[i] = this.list[i];
		this.list = newList;
	}
	
	public int size() { 
		return size;
	}
	
	public String toString() {
		return Arrays.toString(this.list);
	}
	
}
