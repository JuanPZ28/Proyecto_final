package colas;

/**
 * La clase que maneja Colas Simples
 * 
 * <p> La clase contiene los siguientes atributos
 * <ul>
 * 		<li> frente: El objeto de tipo {@link Nodo}. El frente de la Cola.
 * 		<li> fin: El objeto de tipo {@link Nodo}. El final de la Cola.
 * 		<li> tamaño: El tamaño de la Cola.
 * 
 * @author Juan José García Cardona
 * @see BiCola
 * @see ColaCircular
 * @see ColaPrioridad
 */
public class ColaSimple {
	 private Nodo frente, fin;
	    private int tamano;

	    /**
	     * La clase que maneja los Nodos
	     * <p> Estas solo contiene un objeto {@link String} y Otro objeto {@link Nodo} para enlazar el valor siguiente.
	     * 
	     * @author Juan José García Cardona
	     * @see ColaSimple
	     */
	    private static class Nodo {
	    	String valor;
	        Nodo siguiente;
	        
	        /**
	         * Constructor del Nodo. Le asigna un valor, dejando la referencia al nodo nula
	         * @param valor de tipo {@link String} que le asigna al Nodo su valor
	         * 
	         */
	        Nodo(String valor) {
	            this.valor = valor;
	        }
	    }
	    
	    /**
	     * Inserta un nuevo dato en la Cola.
	     * 
	     * <p> Crea un nuevo nodo. Luego, revisa si está vacía. Si es así, asigna su frente y fin al nuevo.
	     * 
	     * <p> Si no, entonces el siguiente valor del fin será el nuevo, y el fin se los asigna a si mismo.
	     * 
	     * <p> Agrega uno al tamaño.
	     * 
	     * @param valor para el nodo.
	     */
	    public void insertar(String valor) {
	        Nodo nuevo = new Nodo(valor);
	        if (estaVacia()) {
	            frente = fin = nuevo;
	        } else {
	            fin.siguiente = nuevo;
	            fin = nuevo;
	        }
	        tamano++;
	    }

	    /**
	     * Remueve el dato del frente. Si esta vacio, retorna nulo.
	     * 
	     * <p> Obtiene el valor del frente, lo reasigna y lo "elimina".
	     * 
	     * <p> Si la cola queda vacía, el frente y el fin también los asignará vacío.
	     * 
	     * <p> Elimina uno al tamaño.
	     * 
	     * @return valor del nodo.
	     */
	    public String remover() {
	        if (estaVacia()) return null;
	        String valor = frente.valor;
	        frente = frente.siguiente;
	        if (frente == null) fin = null;
	        tamano--;
	        return valor;
	    }

	    /**
	     * Busca en la cola un dato.
	     * 
	     * <p> Avanza a traves de la cola hasta encontrar coincidencia.
	     * 
	     * <p> Si no la encuentra, retorna falso.
	     * @param valor del nodo que se esta buscando.
	     * @return true si encuentra el nodo / false si no encuentra el nodo
	     */
	    public boolean buscar(String valor) {
	        Nodo actual = frente;
	        while (actual != null) {
	            if (actual.valor.equals(valor)) return true;
	            actual = actual.siguiente;
	        }
	        return false;
	    }

	    /**
	     * Revisa si esta vacía.
	     * @return true si el frente está vacío (Cola vacía) / false si el frente no está vacío.
	     */
	    public boolean estaVacia() {
	        return frente == null;
	    }

	    /**
	     * Obtiene el tamaño de la Cola.
	     * 
	     * @return tamaño de la Cola.
	     */
	    public int tamano() {
	        return tamano;
	    }

	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        Nodo actual = frente;
	        while (actual != null) {
	            sb.append(actual.valor).append(" -> \n");
	            actual = actual.siguiente;
	        }
	        return sb.length() > 0 ? sb.substring(0, sb.length() - 4) : "(vacía)";
	    }
}
