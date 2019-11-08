package main;

/**
 * Pilha eh uma estrutura de dados que respeita a seguinte norma: 
 * o ultimo a entrar, eh sempre o primeiro a sair, conhecida como 
 * last-in, first-out (LIFO).
 * 
 * Basta imaginar uma pilha de livros e como funciona inserir ou retirar um livro.
 * 
 * Todas as operacoes, nesse caso, sao O(1).
 * 
 * @author marta
 *
 */
public class Pilha {

    private int[] pilha;
    private int capacidade;
    private int head;

    /**
     * Construtor de pilha, tendo como paramentro a capacidade pre-determinada
     * da pilha e atribuindo head (topo) com o valor inicial de -1.
     * 
     * @param capacidade
     */
    public Pilha(int capacidade) {
        this.capacidade = capacidade;
        this.head = -1;
        this.pilha = new int[capacidade];
    }

    /**
     * Metodo que checa se a pilha esta vazia.
     * 
     * Se o valor de head (topo) for igual a -1, significa que a pilha esta vazia.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return this.head == -1;
    }

    /**
     * Metodo que checa se a pilha esta cheia.
     * 
     * Se o valor de head (topo) for igual a capacidade da pilha, significa que 
     * a pilha atingiu seu limite.
     * 
     * @return boolean
     */
    public boolean isFull() {
        return this.head == (capacidade - 1);
    }

    /**
     * Metodo que adiciona um elemento na pilha, sempre no topo (head), depois
     * incrementa (atualizada) o valor de head.
     * 
     * Se a pilha estiver cheia, gera um erro.
     *
     * @param e
     */
    public void push(int e) {
        
        if (this.isFull())
            throw new RuntimeException("FullStackException");
        this.head += 1;
        this.pilha[head] = e;
        
    }
    
    /**
     * Metodo que deleta o elemento do topo (head) da pilha, depois decrementa
     * (atualiza) o valor de head.
     * 
     * Se a pilha estiver vazia, gera um erro.
     * 
     * @return valor atualizado de head.
     */
    public int pop() {
        if (this.isEmpty())
            throw new RuntimeException("EmptyStackException");
        int valor_head = this.pilha[head];
        head -= 1;
        return valor_head; 
    }

    /**
     * Metodo que serve para visualizar (retornar) o valor de head, 
     * do topo da pilha.
     * 
     * Se a pilha estiver vazia, retorna um erro.
     * 
     * @return
     */
    public int peek() {
        if (this.isEmpty())
            throw new RuntimeException("EmptyStackException");
        return this.pilha[head];
    }

    /**
     * Metodo que retorna o tamanho da pilha.
     * 
     * @return
     */
    public int size() {
        return this.head + 1;
    }
}
