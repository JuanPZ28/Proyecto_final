package Circularmente_enlazadas;

public class CircularLinkedList{
    Nodo head;

    public CircularLinkedList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertAtEnd(int data) {
        Nodo newNode = new Nodo(data);
        if (isEmpty()) {
            head = newNode;
            newNode.siguiente = head;
        } else {
            Nodo current = head;
            while (current.siguiente != head) {
                current = current.siguiente;
            }
            current.siguiente = newNode;
            newNode.siguiente = head;
        }
    }

public String traverse() {
    if (isEmpty()) {
        return "Lista vacía\n";
    }

    StringBuilder sb = new StringBuilder();
    Nodo current = head;

    do {
        sb.append(current.data).append(" -> ");
        current = current.siguiente;
    } while (current != head);

    sb.append("regresa al inicio\n");
    return sb.toString();
}


    public String search(int key) {
        if (isEmpty()) {
            return "Lista vacía\n";
        }

        Nodo current = head;
        do {
            if (current.data == key) {
                return "Elemento encontrado: " + key + "\n";
            }
            current = current.siguiente;
        } while (current != head);

        return "Elemento " + key + " no encontrado"+"\n";
    }

    public String remove(int key) {
        if (isEmpty()) {
            return "Lista vacia\n";
        }
        StringBuilder sb = new StringBuilder();
        if (head.data == key) {
            Nodo current = head;
            while (current.siguiente != head) {
                current = current.siguiente;
            }

            if (head == head.siguiente) {
                head = null; // Solo un nodo
            } else {
                current.siguiente = head.siguiente;
                head = head.siguiente;
            }
          return "Elemento eliminado\n";
        }

        Nodo current = head;
        Nodo previous = null;

        do {
            previous = current;
            current = current.siguiente;

            if (current.data == key) {
                previous.siguiente = current.siguiente;
                return "Elemento eliminado\n";
            } 
        } while (current != head);
        sb.append("Elemento " + key + " no se encuentra en la lista"+"\n");
        return sb.toString();
    }
    
   public String size() {
       StringBuilder sb = new StringBuilder();
	   int contador =0;
	   Nodo current = head;
    if (isEmpty()) {
        return "Lista vacía\n";
    }
       do {
           current = current.siguiente;
           contador++;
       } while (current != head);
       sb.append("Tamaño actual de la lista: " + contador + "\n");
       return sb.toString();
   }
}
