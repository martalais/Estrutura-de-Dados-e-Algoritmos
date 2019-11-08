package main;

/**
 * 
 * LinkedList (single) eh uma estrutura formada por nos, onde cada no guarda 
 * um dado e uma referencia para o proximo no.
 * 
 * Sao estruturas dinamicas, ao contrario das estruturas baseadas em arrays, 
 * na linked list objetos sao criados e removidos conforme preciso.
 * A ordem eh determinada por um ponteiro e nao pelo indice como no array.
 * 
 * LinkedList eh muito util quando nao sabemos o tamanho da nossa estrutura, 
 * quando nao pretendemos redimensionar (resize) o vetor, alem do fato de que 
 * podemos alocar memória sob demanda.
 * 
 * Nao podemos acessar diretamente os elementos numa LinkedList, 
 * temos sempre que percorrer-la completamente: O(n).
 * 
 * @author marta
 *
 */
public class LinkedList {

    private Node head;
    private int size; //tamanho ocupado da lista, nao tem capacidade pre-determinada, pois dinamica.

    /**
     * Construtor da LinkedList.
     * Define o comeco da lista (head) como null e o tamanho ocupado como 0.
     * 
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Metodo que checa se a lista esta vazia.
     * 
     * Se o valor de head (começo da lista) for igual a null, indica que a lista esta vazia.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Adiciona um novo node (elemento) no final da lista.
     * 
     * @param v
     */
    public void addLast(int v) {
        Node newNode = new Node(v);		//cria um novo node
        
        if (isEmpty())					//checa se esta vazio (head seria o final, entao)
            this.head = newNode;		//se estiver vazio, adiciona o novo node no inicio (head).
        else {
            Node aux = head;			//else, adiciona o node no prox index disponivel, ou seja,
            							//proximo valor de index que seja null (vazio).
            while (aux.next != null)
                aux = aux.next;

            aux.next = newNode;
        }
        
        this.size += 1;					//incrementa o tamanho ocupadado da lista.
    }

    /**
     * Metodo de busca do node na lista, recebendo como parametro o valor do node.
     * 
     * @param value
     * @return
     */
    private Node search(int value) {
        Node aux = head;
        while (aux != null && aux.value != value) {		//enquanto o next do elemento nao for null e o
            aux = aux.next;								//valor no elemento nao for igual ao procurado
        }												//siga buscando.
        return aux; 
    }

    /**
     * Remover o node da lista, recebendo como parametro o valor do node.
     * 
     * 
     * @param value
     * @return
     */
    public boolean remove(int value) {
    
        if (!isEmpty()) {						//se a lista estiver vazia, nao ha o q remover

            if (this.head.value == value) {		//se o valor do node for encontrado, a referencia muda
                this.head = this.head.next;		//para o next e o size eh decrementado.
                this.size--;
                return true;
            }

            Node prev = null;
            Node aux = head;
            
            while (aux != null && aux.value != value) {
                prev = aux;
                aux = aux.next;
            }

            if (aux == null) return false;
            else {
                prev.next = aux.next;
                this.size--;
                return true;
            }

        }
        
        return false;
    
    
    }

    /**
     * Remove o ultimo node da lista.
     */
    public void removeLast() {
    
        if (!isEmpty()) {
        
            if (this.head.next == null)
                this.head = null;
            else {
                Node prev = null;
                Node aux = head;

                while(aux.next != null) {
                    prev = aux;
                    aux = aux.next;
                }

                prev.next = null;
            }
            this.size--;
        }
    
    }
    
    /**
     * Retorna o valor de size, o tamanho atualizado da lista ocupado.
     * 
     * @return tamanho da lista ocupada.
     */
    public int size() {
        return this.size;
    }

    public String toString() {
        
        String out = "";
        Node aux = head;

        while (aux != null) {
            out += aux.value + " ";
            aux = aux.next;
        }

        return out.trim();
    }
}

/**
 * Cada node contem o seu dado armazenado e o valor do proximo ponteiro (prox elemento).
 * 
 * @author marta
 *
 */
class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }
}
