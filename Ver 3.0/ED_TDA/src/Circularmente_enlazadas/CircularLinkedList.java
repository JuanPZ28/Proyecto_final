package Circularmente_enlazadas;

/**
 * Esta clase representa una lista circularmente enlazada de nodos.
 * Incluye las operaciones de inserción, eliminación, búsqueda, recorrido
 * y obtención del tamaño de la lista.
 */
public class CircularLinkedList {

    /**
     * Referencia al nodo cabeza (inicio) de la lista.
     */
    Nodo head;

    /**
     * Constructor que inicializa una lista vacía.
     */
    public CircularLinkedList() {
        this.head = null;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si no hay nodos en la lista, false en caso contrario.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserta un nuevo nodo con un valor entero al final de la lista.
     * Si la lista está vacía, el nodo se convierte en la cabeza
     * y apunta a sí mismo (estructura circular).
     *
     * @param data El valor que se insertará en el nuevo nodo.
     */
    public void insertAtEnd(int data) {
        Nodo newNode = new Nodo(data);  // Se crea un nuevo nodo con el dato

        if (isEmpty()) {
            // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
            // y apunta a sí mismo para mantener la circularidad
            head = newNode;
            newNode.siguiente = head;
        } else {
            // Si la lista no está vacía, se recorre hasta el último nodo
            Nodo current = head;
            while (current.siguiente != head) {
                current = current.siguiente;
            }

            // Se enlaza el nuevo nodo al final y se apunta al inicio
            current.siguiente = newNode;
            newNode.siguiente = head;
        }
    }

    /**
     * Recorre la lista y devuelve una cadena con los valores de los nodos,
     * terminando con un mensaje indicando que retorna al inicio.
     *
     * @return Cadena con los elementos de la lista.
     */
    public String traverse() {
        if (isEmpty()) {
            return "Lista vacía\n";
        }

        StringBuilder sb = new StringBuilder();
        Nodo current = head;

        // Recorremos desde la cabeza hasta volver a ella
        do {
            sb.append(current.data).append(" -> ");
            current = current.siguiente;
        } while (current != head);

        sb.append("regresa al inicio\n");
        return sb.toString();
    }

    /**
     * Busca un valor en la lista circular.
     *
     * @param key Valor a buscar en los nodos.
     * @return Mensaje indicando si se encontró o no.
     */
    public String search(int key) {
        if (isEmpty()) {
            return "Lista vacía\n";
        }

        Nodo current = head;

        // Recorremos la lista desde la cabeza hasta volver a ella
        do {
            if (current.data == key) {
                return "Elemento encontrado: " + key + "\n";
            }
            current = current.siguiente;
        } while (current != head);

        return "Elemento " + key + " no encontrado\n";
    }

    /**
     * Elimina el primer nodo que contiene el valor dado.
     *
     * @param key Valor del nodo a eliminar.
     * @return Mensaje indicando el resultado de la operación.
     */
    public String remove(int key) {
        if (isEmpty()) {
            return "Lista vacia\n";
        }

        StringBuilder sb = new StringBuilder();

        // Caso especial: el nodo a eliminar es la cabeza
        if (head.data == key) {
            Nodo current = head;

            // Recorremos hasta encontrar el último nodo
            while (current.siguiente != head) {
                current = current.siguiente;
            }

            if (head == head.siguiente) {
                // Si hay un solo nodo, eliminamos la cabeza
                head = null;
            } else {
                // Si hay más nodos, se elimina la cabeza
                current.siguiente = head.siguiente;
                head = head.siguiente;
            }
            return "Elemento eliminado\n";
        }

        Nodo current = head;
        Nodo previous = null;

        // Recorremos buscando el nodo a eliminar
        do {
            previous = current;
            current = current.siguiente;

            if (current.data == key) {
                // Se enlaza el nodo anterior con el siguiente del nodo a eliminar
                previous.siguiente = current.siguiente;
                return "Elemento eliminado\n";
            }
        } while (current != head);

        // Si no se encuentra el valor
        sb.append("Elemento " + key + " no se encuentra en la lista\n");
        return sb.toString();
    }

    /**
     * Calcula la cantidad de nodos en la lista.
     *
     * @return Cadena indicando el tamaño de la lista.
     */
    public String size() {
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        Nodo current = head;

        if (isEmpty()) {
            return "Lista vacía\n";
        }

        // Recorremos la lista y contamos los nodos
        do {
            current = current.siguiente;
            contador++;
        } while (current != head);

        sb.append("Tamaño actual de la lista: " + contador + "\n");
        return sb.toString();
    }
}

