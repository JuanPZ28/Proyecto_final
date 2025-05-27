package Doblemente_enlazadas;

/**
 * Esta clase representa una lista doblemente enlazada.
 * Cada nodo contiene una referencia tanto al siguiente como al anterior nodo.
 * Se puede insertar al inicio o al final, eliminar nodos, buscar valores,
 * recorrer hacia adelante y calcular el tamaño.
 */
public class DoublyLinkedList {
    /**
     * Referencia al primer nodo de la lista.
     */
    Nodo head;

    /**
     * Referencia al último nodo de la lista.
     */
    Nodo tail;

    /**
     * Constructor que inicializa una lista vacía.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si no hay nodos, false en caso contrario.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserta un nuevo nodo al inicio de la lista.
     *
     * @param data El valor a insertar.
     */
    public void insertAtBeginning(int data) {
        Nodo nuevo = new Nodo(data);

        if (head == null) {
            // Si la lista está vacía, el nuevo nodo es cabeza y cola
            head = tail = nuevo;
        } else {
            // El nuevo nodo se enlaza al principio
            nuevo.siguiente = head;
            head.anterior = nuevo;
            head = nuevo;
        }
    }

    /**
     * Inserta un nuevo nodo al final de la lista.
     *
     * @param data El valor a insertar.
     */
    public void insertAtEnd(int data) {
        Nodo nuevo = new Nodo(data);

        if (head == null) {
            // Lista vacía: nuevo nodo es cabeza y cola
            head = tail = nuevo;
        } else {
            // Se enlaza el nuevo nodo al final de la lista
            tail.siguiente = nuevo;
            nuevo.anterior = tail;
            tail = nuevo;
        }
    }

    /**
     * Elimina el primer nodo que contenga el valor especificado.
     *
     * @param key El valor del nodo a eliminar.
     * @return Mensaje indicando si fue eliminado o no.
     */
    public String remove(int key) {
        if (head == null) {
            return "Lista vacía\n";
        }

        Nodo actual = head;

        // Buscamos el nodo con el valor especificado
        while (actual != null && actual.data != key) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            return "Elemento " + key + " no encontrado\n";
        }

        if (actual == head) {
            // Si es la cabeza, actualizamos head
            head = head.siguiente;
            if (head != null) {
                head.anterior = null;
            } else {
                // Si la lista queda vacía
                tail = null;
            }
        } else if (actual == tail) {
            // Si es la cola, actualizamos tail
            tail = tail.anterior;
            tail.siguiente = null;
        } else {
            // Caso general: nodo en el medio
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }

        return "Elemento " + key + " eliminado\n";
    }

    /**
     * Busca un valor en la lista.
     *
     * @param key El valor a buscar.
     * @return Mensaje si se encontró o no.
     */
    public String search(int key) {
        if (isEmpty()) {
            return "Lista vacía\n";
        }

        Nodo actual = head;

        // Recorremos de izquierda a derecha
        while (actual != null) {
            if (actual.data == key) {
                return "Elemento encontrado: " + actual.data + "\n";
            }
            actual = actual.siguiente;
        }

        return "Elemento no encontrado\n";
    }

    /**
     * Recorre la lista de principio a fin y devuelve una representación como cadena.
     *
     * @return Cadena con los valores de la lista en orden.
     */
    public String traverseForward() {
        if (isEmpty()) {
            return "Lista vacía\n";
        }

        StringBuilder sb = new StringBuilder();
        Nodo actual = head;

        // Avanzamos desde head hasta tail
        while (actual != null) {
            sb.append(actual.data).append("<->");
            actual = actual.siguiente;
        }

        sb.append("null\n");
        return sb.toString();
    }

    /**
     * Calcula el tamaño de la lista contando los nodos desde la cola hasta la cabeza.
     *
     * @return Cadena con el número total de nodos.
     */
    public String size() {
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        Nodo actual = tail;

        if (isEmpty()) {
            return "Lista vacía\n";
        }

        // Contamos desde tail hacia head usando anterior
        while (actual != null) {
            actual = actual.anterior;
            contador++;
        }

        sb.append("Tamaño actual de la lista: " + contador + "\n");
        return sb.toString();
    }
}
