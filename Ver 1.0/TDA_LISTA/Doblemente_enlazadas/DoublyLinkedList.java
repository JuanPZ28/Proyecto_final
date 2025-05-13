package Doblemente_enlazadas;

public class DoublyLinkedList {
     Nodo head;
     Nodo tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertAtBeginning(int data) {
        Nodo nuevo = new Nodo(data);
        if (head == null) {
            head = tail = nuevo;
        } else {
            nuevo.siguiente = head;
            head.anterior = nuevo;
            head = nuevo;
        }
    }

    public void insertAtEnd(int data) {
        Nodo nuevo = new Nodo(data);
        if (head == null) {
            head = tail = nuevo;
        } else {
            tail.siguiente = nuevo;
            nuevo.anterior = tail;
            tail = nuevo;
        }
    }

    public void remove(int key) {
        if (head == null) {
            System.out.println("Lista vacía");
            return;
        }

        Nodo actual = head;
        while (actual != null && actual.data != key) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            System.out.println("Elemento " + key + " no encontrado");
            return;
        }

        if (actual == head) {
            head = head.siguiente;
            if (head != null) {
                head.anterior = null;
            } else {
                tail = null; // La lista quedó vacía
            }
        } else if (actual == tail) {
            tail = tail.anterior;
            tail.siguiente = null;
        } else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }
    }

    public void search(int key) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.data == key) {
                System.out.println("Elemento encontrado: " + actual.data);
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("Elemento no encontrado");
    }

    public void traverseForward() {
        Nodo actual = head;
        while (actual != null) {
            System.out.print(actual.data + " <-> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    public void traverseBackward() {
        Nodo actual = tail;
        while (actual != null) {
            System.out.print(actual.data + " <-> ");
            actual = actual.anterior;
        }
        System.out.println("null");
    }
    
    public void size() {
    	int contador=0;
        Nodo actual = tail;
        while (actual != null) {
            actual = actual.anterior;
            contador ++;
        }
        System.out.println(contador);
    }
}
