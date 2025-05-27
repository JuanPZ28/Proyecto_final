package modelos;

@SuppressWarnings("unchecked")
public class ColaCircular<T> {
    private T[] datos;
    private int frente;
    private int fin;
    private int size;
    private int capacidad;

    private static final int CAPACIDAD_INICIAL = 10;

    public ColaCircular() {
        this(CAPACIDAD_INICIAL);
    }

    public ColaCircular(int capacidadInicial) {
        capacidad = capacidadInicial > 0 ? capacidadInicial : CAPACIDAD_INICIAL;
        datos = (T[]) new Object[capacidad];
        frente = 0;
        fin = -1;
        size = 0;
    }

    public void encolar(T valor) {
        if (size == capacidad) {
            aumentarCapacidad();
        }
        fin = (fin + 1) % capacidad;
        datos[fin] = valor;
        size++;
    }

    public T desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("Cola vacía, no se puede desencolar");
        }
        T valor = datos[frente];
        datos[frente] = null; // liberar referencia
        frente = (frente + 1) % capacidad;
        size--;
        return valor;
    }

    public boolean estaVacia() {
        return size == 0;
    }

    public int tamaño() {
        return size;
    }

    public int capacidad() {
        return capacidad;
    }

    private void aumentarCapacidad() {
        int nuevaCapacidad = capacidad * 2;
        T[] nuevoArray = (T[]) new Object[nuevaCapacidad];

        // Copiar elementos en orden correcto desde frente
        for (int i = 0; i < size; i++) {
            nuevoArray[i] = datos[(frente + i) % capacidad];
        }

        datos = nuevoArray;
        capacidad = nuevaCapacidad;
        frente = 0;
        fin = size - 1;
    }

    public String[] obtenerContenido() {
        String[] resultado = new String[size];
        for (int i = 0; i < size; i++) {
            resultado[i] = (String) datos[(frente + i) % capacidad];
        }
        return resultado;
    }

}