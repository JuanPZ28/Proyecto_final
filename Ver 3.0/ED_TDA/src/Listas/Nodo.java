package Listas;

/**
 * Clase que representa un nodo en una lista simplemente enlazada.
 * Cada nodo contiene un valor entero y una referencia al siguiente nodo.
 */
public class Nodo {

    /**
     * Valor almacenado en el nodo.
     */
    int data;

    /**
     * Referencia al siguiente nodo en la lista.
     * Es null si este es el último nodo.
     */
    Nodo siguiente;

    /**
     * Constructor que inicializa un nodo con un valor entero.
     * El siguiente nodo se establece como null por defecto.
     *
     * @param data El valor a almacenar en el nodo.
     */
    public Nodo(int data) {
        this.data = data;
        this.siguiente = null;
    }
}
