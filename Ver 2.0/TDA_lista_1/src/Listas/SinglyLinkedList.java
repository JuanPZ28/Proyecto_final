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
        return "Elemento " + key + " no encontrado"+"\n";
    }

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

    public String remove(int key) {
        if (isEmpty()) {
            return "Lista vacia\n";
        }
        
        if (head.data == key) {
            head = head.siguiente;
            return "Elemento eliminado\n";
        }

        Nodo current = head;
        Nodo previous = null;

        while (current != null && current.data != key) {
            previous = current;
            current = current.siguiente;
        }

        if (current == null) {
            return "Elemento " + key + " no encontrado"+"\n";
        }

        previous.siguiente = current.siguiente;
        return "Elemento eliminado\n";
    }
    
    public String size(){
       StringBuilder sb = new StringBuilder();
    	int contador = 0;
        Nodo current = head;
        
            if (isEmpty()) {
                return "Lista vacía\n";
            }        
        
        while (current != null) {
            current = current.siguiente;
            contador ++;
        }
       sb.append("Tamaño actual de la lista: " + contador + "\n");
       return sb.toString();
    }
}
