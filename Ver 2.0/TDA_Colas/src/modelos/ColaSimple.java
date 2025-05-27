package modelos;

public class ColaSimple {
	 private Nodo frente, fin;
	    private int tamano;

	    private static class Nodo {
	    	String valor;
	        Nodo siguiente;

	        Nodo(String valor) {
	            this.valor = valor;
	        }
	    }

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

	    public String remover() {
	        if (estaVacia()) return null;
	        String valor = frente.valor;
	        frente = frente.siguiente;
	        if (frente == null) fin = null;
	        tamano--;
	        return valor;
	    }

	    public boolean buscar(String valor) {
	        Nodo actual = frente;
	        while (actual != null) {
	            if (actual.valor.equals(valor)) return true;
	            actual = actual.siguiente;
	        }
	        return false;
	    }

	    public boolean estaVacia() {
	        return frente == null;
	    }

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
