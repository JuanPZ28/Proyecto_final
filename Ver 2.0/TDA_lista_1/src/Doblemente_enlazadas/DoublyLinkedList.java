package Doblemente_enlazadas;

public class DoublyLinkedList {
     Nodo head;
     Nodo tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
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

    public String remove(int key) {
        if (head == null) {
            return "Lista vacía\n";
        }

        Nodo actual = head;
        while (actual != null && actual.data != key) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            return "Elemento "+key+" no encontrado"+"\n";
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
       return "Elemento "+key+" eliminado"+"\n"; 
    }

    public String search(int key) {
        if (isEmpty()) {
            return "Lista vacía\n";
        }        
        Nodo actual = head;
        while (actual != null) {
            if (actual.data == key) {
                return "Elemento encontrado: " + actual.data+ "\n";
            }
            actual = actual.siguiente;
        }
        return "Elemento no encontrado\n";
    }

    public String traverseForward() {
        if(isEmpty()){
          return "Lista vacía\n";
        }
        StringBuilder sb = new StringBuilder();        
        Nodo actual = head;
        
        while (actual != null) {
            sb.append(actual.data).append("<->");
            actual = actual.siguiente;
        }
        sb.append("null\n");
        return sb.toString();
    }

    
    public String size() {
       StringBuilder sb = new StringBuilder();
    	int contador=0;
        Nodo actual = tail;
    if (isEmpty()) {
        return "Lista vacía\n";
    }
    
        while (actual != null) {
            actual = actual.anterior;
            contador ++;
        }
       sb.append("Tamaño actual de la lista: " + contador + "\n");
        return sb.toString();
    }
}
