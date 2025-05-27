package colas;

/**
 * La clase que maneja Colas de Prioridad
 * 
 * <p> La clase contiene los siguientes atributos.
 * <ul>
 * 		<li> elementos: Cola de elementos de tipo {@link ElementoPrioridad}
 * 		<li> size: tamaño de la Cola
 * 		<li> CAPACIDAD_INICIAL: capacidad del arreglo inicial
 * @param <T> los datos del arreglo
 * 
 * @author Juan José García
 * @see ColaSimple
 * @see BiCola
 * @see ColaCircular
 */
public class ColaPrioridad<T> {
    private ElementoPrioridad<T>[] elementos;
    private int size;
    private static final int CAPACIDAD_INICIAL = 10;

    /**
     * Constructor de la Cola, castea de un arreglo {@link ElemntoPrioridad} a génerico.
     * 
     * <p> Asigna el tamaño de la Cola a 0.
     */
    @SuppressWarnings("unchecked")
    public ColaPrioridad() {
        // No se puede crear arrays genéricos directamente, se crea Object[] y se castea
        elementos = (ElementoPrioridad<T>[]) new ElementoPrioridad[CAPACIDAD_INICIAL];
        size = 0;
    }

    /**
     * La clase que maneja los elementos de la Cola.
     * 
     * <p> La clase contiene los siguientes atributos
     * <ul>
     * 		<li> valor: Son los valores de los elementos
     * 		<li> prioridad: Es la prioridad del valor
     * @param <T> Son los datos de las Colas
     * 
     * @author Juan José García
     * @see ColaPrioridad
     */
    public static class ElementoPrioridad<T> {
        public final T valor;
        public final int prioridad; // 1 (alta) - 10 (baja)

        /**
         * Constructor de ElementoPrioridad.
         * 
         * <p> Asigna el valor y prioridad al elemento del arreglo.
         * 
         * @param valor del elemento.
         * @param prioridad del elemento.
         */
        public ElementoPrioridad(T valor, int prioridad) {
            this.valor = valor;
            this.prioridad = prioridad;
        }

        @Override
        public String toString() {
            return valor + " (prioridad " + prioridad + ")";
        }
    }

    /**
     * Encola un dato a la Cola.
     * 
     * <p> Revisa si debe aumentar la capacidad del arreglo si esta es excedida.
     * 
     * <p> Encuentra la posición donde debe insertar el elemento para mantener el orden de la prioridad.
     * 
     * <p> Luego, mueve los elementos para hacer espacio al dato.
     * 
     * <p> Por último, asigna el dato y aumenta uno el tamaño.
     * 
     * @param nuevo elemento a encolar.
     */
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

    /**
     * Desnecola un dato de la Cola, Si esta vacio, retorna nulo.
     * 
     * <p> Obtiene el valor inicial del arreglo de elementos. Luego, traslada los datos del arreglo a la
     * izquierda, reacomodando el arreglo.
     * 
     * <p> Libera la referencia del último dato.
     * 
     * <p> Elimina uno al tamaño.
     * 
     * @return El valor del dato desencolado.
     */
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

    /**
     * Obtiene el valor del frente de la Cola.
     * @return Nulo si está vacío / Dato inicial si no está vacío
     */
    public ElementoPrioridad<T> frente() {
        return estaVacia() ? null : elementos[0];
    }

    /**
     * Revis si esta vacía.
     * @return true si el tamaño es 0 / false si el tamaño no es 0.
     */
    public boolean estaVacia() {
        return size == 0;
    }

    /**
     * Obtiene el tamaño de la lista.
     * 
     * @return tamaño de la lista.
     */
    public int size() {
        return size;
    }

    /**
     * Revisa para aumentar la capacidad del arreglo.
     * 
     * <p> Primero, crea un nuevo arreglo duplicando la capacidad.
     * <p> Luego, utiliza <code> System.arraycopy </code> para duplicar los datos del arreglo.
     * <p> Se guarda en elementos el nuevo arreglo con el nuevo tamaño.
     */
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
