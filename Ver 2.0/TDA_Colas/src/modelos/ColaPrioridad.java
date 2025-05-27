package modelos;


public class ColaPrioridad<T> {
    private ElementoPrioridad<T>[] elementos;
    private int size;
    private static final int CAPACIDAD_INICIAL = 10;

    @SuppressWarnings("unchecked")
    public ColaPrioridad() {
        // No se puede crear arrays genéricos directamente, se crea Object[] y se castea
        elementos = (ElementoPrioridad<T>[]) new ElementoPrioridad[CAPACIDAD_INICIAL];
        size = 0;
    }

    public static class ElementoPrioridad<T> {
        public final T valor;
        public final int prioridad; // 1 (alta) - 10 (baja)

        public ElementoPrioridad(T valor, int prioridad) {
            this.valor = valor;
            this.prioridad = prioridad;
        }

        @Override
        public String toString() {
            return valor + " (prioridad " + prioridad + ")";
        }
    }

    public void encolar(ElementoPrioridad<T> nuevo) {
        if (size == elementos.length) {
            // ampliar capacidad
            aumentarCapacidad();
        }
        // encontrar la posición donde insertar para mantener orden por prioridad
        int i = 0;
        while (i < size && elementos[i].prioridad <= nuevo.prioridad) {
            i++;
        }
        // mover elementos a la derecha para hacer espacio
        for (int j = size; j > i; j--) {
            elementos[j] = elementos[j - 1];
        }
        elementos[i] = nuevo;
        size++;
    }

    public ElementoPrioridad<T> desencolar() {
        if (estaVacia()) return null;
        ElementoPrioridad<T> primero = elementos[0];
        // mover todo un lugar a la izquierda
        for (int i = 1; i < size; i++) {
            elementos[i - 1] = elementos[i];
        }
        elementos[size - 1] = null; // liberar referencia
        size--;
        return primero;
    }

    public ElementoPrioridad<T> frente() {
        return estaVacia() ? null : elementos[0];
    }

    public boolean estaVacia() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void aumentarCapacidad() {
        ElementoPrioridad<T>[] nuevoArray = (ElementoPrioridad<T>[]) new ElementoPrioridad[elementos.length * 2];
        System.arraycopy(elementos, 0, nuevoArray, 0, size);
        elementos = nuevoArray;
    }
    
    @Override
    public String toString() {
        if (estaVacia()) return "Cola vacía.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(elementos[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
