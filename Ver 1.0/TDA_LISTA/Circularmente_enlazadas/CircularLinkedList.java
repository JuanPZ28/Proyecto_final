package Circularmente_enlazadas;

public class CircularLinkedList {
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

    public void traverse() {
        if (isEmpty()) {
            System.out.println("Lista vacía");
            return;
        }
        Nodo current = head;
        do {
            System.out.print(current.data + " -> ");
            current = current.siguiente;
        } while (current != head);
        System.out.println("(regresa al inicio)");
    }

    public void search(int key) {
        if (isEmpty()) {
            System.out.println("Lista vacía");
            return;
        }

        Nodo current = head;
        do {
            if (current.data == key) {
                System.out.println("Elemento encontrado: " + key);
                return;
            }
            current = current.siguiente;
        } while (current != head);

        System.out.println("Elemento " + key + " no encontrado");
    }

    public void remove(int key) {
        if (isEmpty()) {
            System.out.println("Lista vacía");
            return;
        }

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
            return;
        }

        Nodo current = head;
        Nodo previous = null;

        do {
            previous = current;
            current = current.siguiente;

            if (current.data == key) {
                previous.siguiente = current.siguiente;
                return;
            }
        } while (current != head);

        System.out.println("Elemento " + key + " no encontrado");
    }
   public void size() {
	   int contador =0;
	   Nodo current = head;
       do {
           current = current.siguiente;
           contador++;
       } while (current != head);
       System.out.println(contador);
   }
}
