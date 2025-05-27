package colas;

/**
 * Clase que representa una bicola (cola doble), donde se pueden insertar y eliminar elementos
 * tanto por el frente como por el final. Soporta redimensionamiento dinámico.
 * 
 * @author Juan Garcia
 */
public class BiCola {
    private Object[] elementos;
    private int frente;
    private int fin;
    private int tamaño;
    private static final int CAPACIDAD_INICIAL = 5; // Capacidad inicial por defecto

    /**
     * Constructor de la clase BiCola. Inicializa una bicola vacía.
     */
    public BiCola() {
        elementos = new Object[CAPACIDAD_INICIAL];
        frente = fin = -1;
        tamaño = 0;
    }

    /**
     * Redimensiona el arreglo de elementos a una nueva capacidad.
     * 
     * @param nuevaCapacidad nueva capacidad del arreglo interno
     */
    private void redimensionar(int nuevaCapacidad) {
        Object[] nuevoArray = new Object[nuevaCapacidad];
        if (tamaño > 0) {
            if (frente <= fin) {
                System.arraycopy(elementos, frente, nuevoArray, 0, tamaño);
            } else {
                int elementosHastaFinal = elementos.length - frente;
                System.arraycopy(elementos, frente, nuevoArray, 0, elementosHastaFinal);
                System.arraycopy(elementos, 0, nuevoArray, elementosHastaFinal, fin + 1);
            }
        }
        elementos = nuevoArray;
        frente = 0;
        fin = tamaño - 1;
    }

    /**
     * Inserta un valor al inicio de la bicola.
     * 
     * @param valor el valor a insertar
     */
    public void insertarInicio(Object valor) {
        if (tamaño == elementos.length) {
            redimensionar(elementos.length * 2);
        }

        if (estaVacia()) {
            frente = fin = 0;
        } else {
            frente = (frente - 1 + elementos.length) % elementos.length;
        }
        elementos[frente] = valor;
        tamaño++;
    }

    /**
     * Inserta un valor al final de la bicola.
     * 
     * @param valor el valor a insertar
     */
    public void insertarFinal(Object valor) {
        if (tamaño == elementos.length) {
            redimensionar(elementos.length * 2);
        }

        if (estaVacia()) {
            frente = fin = 0;
        } else {
            fin = (fin + 1) % elementos.length;
        }
        elementos[fin] = valor;
        tamaño++;
    }

    /**
     * Elimina y retorna el elemento al inicio de la bicola.
     * 
     * @return el valor eliminado del frente
     * @throws RuntimeException si la bicola está vacía
     */
    public String removerInicio() {
        if (tamaño == 0) throw new RuntimeException("Bicola vacía");
        String valor = (String) elementos[frente];
        frente = (frente + 1) % elementos.length;
        tamaño--;
        if (tamaño == 0) frente = fin = -1;
        return valor;
    }

    /**
     * Elimina y retorna el elemento al final de la bicola.
     * 
     * @return el valor eliminado del final
     * @throws RuntimeException si la bicola está vacía
     */
    public String removerFinal() {
        if (tamaño == 0) throw new RuntimeException("Bicola vacía");
        String valor = (String) elementos[fin];
        fin = (fin - 1 + elementos.length) % elementos.length;
        tamaño--;
        if (tamaño == 0) frente = fin = -1;
        return valor;
    }

    /**
     * Busca un valor dentro de la bicola.
     * 
     * @param valor el valor a buscar
     * @return mensaje indicando si el valor fue encontrado o no
     */
    public String buscar(Object valor) {
        if (tamaño == 0) return "Cola vacía";
        for (int i = 0, idx = frente; i < tamaño; i++, idx = (idx + 1) % elementos.length) {
            if (elementos[idx].equals(valor)) return "Elemento encontrado en posición " + i + "\n";
        }
        return "Elemento no encontrado\n";
    }

    /**
     * Devuelve el número de elementos en la bicola.
     * 
     * @return tamaño actual de la bicola
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Retorna una representación en cadena de la bicola.
     * 
     * @return representación textual de los elementos de la bicola
     */
    public String visualizar() {
        if (tamaño == 0) return "Bicola vacía.";
        StringBuilder sb = new StringBuilder("Bicola: \n");
        for (int i = 0, idx = frente; i < tamaño; i++, idx = (idx + 1) % elementos.length) {
            sb.append(elementos[idx]).append(" ->\n");
        }
        return sb.toString();
    }

    /**
     * Indica si la bicola está vacía.
     * 
     * @return true si la bicola no contiene elementos, false en caso contrario
     */
    public boolean estaVacia() {
        return tamaño == 0;
    }
}

