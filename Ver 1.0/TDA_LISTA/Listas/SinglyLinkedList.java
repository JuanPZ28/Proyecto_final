package Listas;

public class SinglyLinkedList {
     Nodo head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertAtBeginning(int data) {
        Nodo newNodo = new Nodo(data);
        newNodo.siguiente = head;
        head = newNodo;
    }

    public void insertAtEnd(int data) {
        Nodo newNodo = new Nodo(data);
        if (isEmpty()) {
            head = newNodo;
        } else {
            Nodo current = head;
            while (current.siguiente != null) {
                current = current.siguiente;
            }
            current.siguiente = newNodo;
        }
    }

    public boolean search(int key) {
        Nodo current = head;
        while (current != null) {
            if (current.data == key) {
                return true;
            }
            current = current.siguiente;
        }
        return false;
    }

    public void traverse() {
        Nodo current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.siguiente;
        }
        System.out.println("null");
    }

    public void remove(int key) {
        if (isEmpty()) {
            System.out.println("Lista vacía");
            return;
        }

        if (head.data == key) {
            head = head.siguiente;
            return;
        }

        Nodo current = head;
        Nodo previous = null;

        while (current != null && current.data != key) {
            previous = current;
            current = current.siguiente;
        }

        if (current == null) {
            System.out.println("Elemento " + key + " no encontrado");
            return;
        }

        previous.siguiente = current.siguiente;
    }
    
    public void size(){
    	int contador = 0;
        Nodo current = head;
        while (current != null) {
            current = current.siguiente;
            contador ++;
        }
        System.out.println(contador);
    }
}
