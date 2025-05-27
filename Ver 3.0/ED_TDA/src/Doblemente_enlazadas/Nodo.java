package Doblemente_enlazadas;

/**
 * Clase que representa un nodo en una lista doblemente enlazada.
 * Cada nodo contiene un valor entero, una referencia al siguiente nodo
 * y una referencia al nodo anterior.
 */
public class Nodo {

    /**
     * Valor almacenado en el nodo.
     */
    int data;

    /**
     * Referencia al siguiente nodo en la lista.
     */
    Nodo siguiente;

    /**
     * Referencia al nodo anterior en la lista.
     */
    Nodo anterior;

    /**
     * Constructor que crea un nodo con el valor dado.
     * Inicializa las referencias anterior y siguiente como null.
     *
     * @param data El valor a almacenar en el nodo.
     */
    public Nodo(int data) {
        this.data = data;
        this.siguiente = null;
        this.anterior = null;
    }
}
