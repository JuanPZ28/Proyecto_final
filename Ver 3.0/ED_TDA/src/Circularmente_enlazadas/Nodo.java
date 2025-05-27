package Circularmente_enlazadas;

/**
 * Clase que representa un nodo en una lista circularmente enlazada.
 * Cada nodo contiene un valor entero y una referencia al siguiente nodo.
 */
public class Nodo {
    /**
     * Valor almacenado en el nodo.
     */
    int data;

    /**
     * Referencia al siguiente nodo en la lista.
     * En una lista circular, el último nodo apunta al primero.
     */
    Nodo siguiente;

    /**
     * Constructor que inicializa el nodo con un valor.
     * El enlace al siguiente nodo se establece como null por defecto.
     *
     * @param data El valor a almacenar en el nodo.
     */
    public Nodo(int data) {
        this.data = data;
        this.siguiente = null;
    }
}

