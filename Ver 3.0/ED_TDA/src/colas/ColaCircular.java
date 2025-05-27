package colas;

/**
 * La clase que maneja Colas Circulares.
 * 
 * <p> La clase contiene los siguientes atributos.
 * <ul>
 * 		<li> T[] datos: Los datos del arreglo.
 * 		<li> frente: El frnete de la Cola.
 * 		<li> fin: El fin de la Cola.
 * 		<li> size: Tamaño de la Cola.
 * 		<li> Capacidad: Sirve para modificar el tamaño del arreglo.
 * 		<li> CAPACIDAD_INICIAL: El valor inicial del arreglo, el cuál se modificará con el
 * 			transcurso de las operaciones con la Cola.
 * @param <T> Obtiene Los datos del arreglo
 * 
 * @author Juan José García Cardona
 * @see ColaSimple
 * @see BiCola
 * @see ColaPrioridad
 */
@SuppressWarnings("unchecked")
public class ColaCircular<T> {
    private T[] datos;
    private int frente;
    private int fin;
    private int size;
    private int capacidad;

    private static final int CAPACIDAD_INICIAL = 10;

    /**
     * Constructor alternativo de la Cola Circular en dónde le asigna la CAPACIDAD_INICIAL
     */
    public ColaCircular() {
        this(CAPACIDAD_INICIAL);
    }

    /**
     * Constructor de ña Cola Circular. Utiliza una capacidad Inicial, que revisa para ver si usa
     * CAPACIDAD_INICIAL o el parametro pasado.
     * 
     * <p> datos, frente, fin y tamaño se programan normalmente.
     * @param capacidadInicial para determinar el tamaño del arreglo
     */
    public ColaCircular(int capacidadInicial) {
        capacidad = capacidadInicial > 0 ? capacidadInicial : CAPACIDAD_INICIAL;
        datos = (T[]) new Object[capacidad];
        frente = 0;
        fin = -1;
        size = 0;
    }

    /**
     * Encola un nuevo dato en la Cola.
     * 
     * <p> Revisa si debe aumentar la capacidad del arreglo.
     * 
     * <p> Aumenta el fin, agrega el valor al final.
     * 
     * <p> Agrega uno al tamaño.
     * 
     * @param valor para el nodo.
     */
    public void encolar(T valor) {
        if (size == capacidad) {
            aumentarCapacidad();
        }
        fin = (fin + 1) % capacidad;
        datos[fin] = valor;
        size++;
    }

    /**
     * Desencola el dato del frente. Si esta vacio, retorna nulo.
     * 
     * <p> Obtiene el valor del frente, libera la referencia y reasigna el frente.
     * 
     * <p> Elimina uno al tamaño.
     * 
     * @return valor del nodo.
     */
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

    /**
     * Revisa si esta vacía.
     * @return true si el tamaño es 0 / false si el tasmaño no es 0.
     */
    public boolean estaVacia() {
        return size == 0;
    }

    /**
     * Obtiene el tamaño de la Cola.
     * 
     * @return tamaño de la Cola.
     */
    public int tamaño() {
        return size;
    }

    /**
     * Obtiene la capacidad del arreglo
     * @return capacidad del arreglo
     */
    public int capacidad() {
        return capacidad;
    }

    /**
     * Aumenta la Capacidad del arreglo para guardar más datos.
     * 
     * <p> Duplica la capacidad. Luego crea un nuevo arreglo, copia los elementos al nuevo arreglo.
     * 
     * <p> Reasigna los datos, la capacidad, el frente y el fin correspondiente.
     */
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

    /**
     * Obtiene el contenido del arreglo, los cuales son los datos de la lista.
     * 
     * <p> Crea un arreglo, en dónde guarda los datos, uno por uno.
     * 
     * @return resultado de guardar los datos en el arreglo.
     */
    public String[] obtenerContenido() {
        String[] resultado = new String[size];
        for (int i = 0; i < size; i++) {
            resultado[i] = (String) datos[(frente + i) % capacidad];
        }
        return resultado;
    }

}