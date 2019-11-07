package main;

/**
 * Fila eh uma estrutura de dados que respeita a seguinte norma: 
 * o primeiro a entrar, eh sempre o primeiro a sair. 
 * First-in, first-out (FIFO).
 * 
 * É importante lembrar que precisamos manter uma referência
 * para o head e para o tail: head eh o inicio da fila, tail eh o final.
 * 
 * @author marta
 *
 */

public class Fila {
	
	private int[] fila;
    private int capacidade;
    private int head;
    private int tail;

    /**
     * Construtor que recebe como entrada a capacidade pre-determinada da fila.
     * Head e Tail sao inicializados com as referencias -1.
     * 
     * @param capacidade
     */
    public Fila(int capacidade) {
        this.capacidade = capacidade;
        this.head = -1;
        this.tail = -1;
        this.fila = new int[capacidade];
    }

    /**
     * Metodo que checa se a fila esta vazia:
     * Caso head e tail ainda estejam referenciados como -1, a fila esta vazia.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return (this.head == -1 && this.tail == -1);
    }

    /**
     * Metodo que checa se a fila esta cheia:
     * Se a referencia de tail (levando em consideracao a capacidade) for
     * igual a referencia de head (pensando de forma circular), pode indicar
     * que a fila esta cheia.
     * 
     * A melhor forma de fazer isso eh com uma variavel "total", para nao confundir.
     *  
     * @return boolean
     */
    public boolean isFull() {
        return ((this.tail + 1) % capacidade) == this.head; 
    }

    /**
     * Adiciona um elemento a fila atraves do seu valor.
     * 
     * Se a fila estiver cheia, gera um erro.
     * Se a fila estiver vazia, o elemento eh adicionado no head.
     * Else, o elemento eh adicionado no proximo index disponivel 
     * e tail eh incrementado.
     * 
     * @param e
     */
    public void add(int e) {

        if (isFull()) throw new RuntimeException("Is Full");
        if (isEmpty()) {
            this.head = 0;
            this.tail = 0;
            this.fila[0] = e;
        } else {
                tail = (tail + 1) % capacidade;
                this.fila[tail] = e;
        }

    }
    
    /**
     * Remove o primeiro elemento da fila (head).
     * 
     * Se a fila esta vazia, gera um erro.
     * Se a head = tail (fila cheia), head e tail voltam a ser -1.
     * Else, head eh atualizado.
     * 
     * @return
     */
    public int remove() {
        
        if (isEmpty())
            throw new RuntimeException("QueuIsEmptyException");

        int value = fila[head];
        
        if (this.head == this.tail) {
            this.head = -1;
            this.tail = -1;
        } else {
            this.head = ((this.head + 1) % capacidade);
        }
        return value; 
        
    }
    
    public int head() {
        if (this.isEmpty())
            throw new RuntimeException("QueuIsEmptyException");
        return this.fila[head];
    }    

}
