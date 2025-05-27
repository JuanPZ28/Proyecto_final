package Listas;

/**
 * Clase que representa una lista simplemente enlazada.
 * Permite insertar, eliminar, buscar, recorrer y calcular el tamaño de la lista.
 */
public class SinglyLinkedList {

    /**
     * Referencia al primer nodo de la lista.
     */
    Nodo head;

    /**
     * Constructor que inicializa una lista vacía.
     */
    public SinglyLinkedList() {
        this.head = null;
    }

    /**
     * Verifica si la lista está vacía.
     * @return true si la lista no tiene elementos, false en caso contrario.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserta un nuevo nodo al inicio de la lista.
     * @param data Valor a insertar.
     */
    public void insertAtBeginning(int data) {
        Nodo newNodo = new Nodo(data);
        // Apunta el nuevo nodo al actual head
        newNodo.siguiente = head;
        // Mueve head al nuevo nodo
        head = newNodo;
    }

    /**
     * Inserta un nuevo nodo al final de la lista.
     * @param data Valor a insertar.
     */
    public void insertAtEnd(int data) {
        Nodo newNodo = new Nodo(data);
        if (isEmpty()) {
            head = newNodo;
        } else {
            Nodo current = head;
            // Recorre hasta el último nodo
            while (current.siguiente != null) {
                current = current.siguiente;
            }
            // Conecta el último nodo al nuevo nodo
            current.siguiente = newNodo;
        }
    }

    /**
     * Busca un valor específico en la lista.
     * @param key Valor a buscar.
     * @return Mensaje indicando si se encontró el elemento o no.
     */
    public String search(int key) {
        if (isEmpty()) {
            return "Lista vacía\n";
        }

        Nodo current = head;
        while (current != null) {
            if (current.data == key) {
                return "Elemento encontrado: " + key + "\n";
            }
            current = current.siguiente;
        }
        return "Elemento " + key + " no encontrado\n";
    }

    /**
     * Recorre la lista y devuelve los elementos en orden.
     * @return Representación en cadena de la lista.
     */
    public String traverse() {
        if (isEmpty()) {
            return "Lista vacía\n";
        }

        StringBuilder sb = new StringBuilder();
        Nodo current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.siguiente;
        }
        sb.append("null\n");
        return sb.toString();
    }

    /**
     * Elimina el primer nodo que contiene el valor especificado.
     * @param key Valor a eliminar.
     * @return Mensaje indicando si se eliminó el nodo o no se encontró.
     */
    public String remove(int key) {
        if (isEmpty()) {
            return "Lista vacía\n";
        }

        // Caso especial: el nodo a eliminar es el head
        if (head.data == key) {
            head = head.siguiente;
            return "Elemento eliminado\n";
        }

        Nodo current = head;
        Nodo previous = null;

        // Buscar el nodo con el valor
        while (current != null && current.data != key) {
            previous = current;
            current = current.siguiente;
        }

        // Si no se encontró el nodo
        if (current == null) {
            return "Elemento " + key + " no encontrado\n";
        }

        // Elimina el nodo saltándolo en la cadena
        previous.siguiente = current.siguiente;
        return "Elemento eliminado\n";
    }

    /**
     * Calcula el tamaño de la lista.
     * @return Mensaje con la cantidad de nodos en la lista.
     */
    public String size() {
        if (isEmpty()) {
            return "Lista vacía\n";
        }

        StringBuilder sb = new StringBuilder();
        int contador = 0;
        Nodo current = head;

        while (current != null) {
            current = current.siguiente;
            contador++;
        }

        sb.append("Tamaño actual de la lista: " + contador + "\n");
        return sb.toString();
    }
}
