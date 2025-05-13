package Doblemente_enlazadas;

public class Nodo {
    int data;
    Nodo siguiente;
    Nodo anterior;

    public Nodo(int data) {
        this.data = data;
        this.siguiente = null;
        this.anterior = null;
    }
}
