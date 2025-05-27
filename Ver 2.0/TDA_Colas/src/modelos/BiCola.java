package modelos;

public class BiCola {
    private Object[] elementos;
    private int frente;
    private int fin;
    private int tamaño;
    private static final int CAPACIDAD_INICIAL = 5; // Capacidad inicial por defecto

    public BiCola() {
        elementos = new Object[CAPACIDAD_INICIAL];
        frente = fin = -1;
        tamaño = 0;
    }

    // Método para redimensionar el arreglo
    private void redimensionar(int nuevaCapacidad) {
        Object[] nuevoArray = new Object[nuevaCapacidad];
        if (tamaño > 0) {
            if (frente <= fin) {
                // Caso: elementos están en un bloque continuo
                System.arraycopy(elementos, frente, nuevoArray, 0, tamaño);
            } else {
                // Caso: elementos están divididos en dos bloques (frente > fin)
                int elementosHastaFinal = elementos.length - frente;
                System.arraycopy(elementos, frente, nuevoArray, 0, elementosHastaFinal);
                System.arraycopy(elementos, 0, nuevoArray, elementosHastaFinal, fin + 1);
            }
        }
        elementos = nuevoArray;
        frente = 0;
        fin = tamaño - 1;
    }

    // Métodos de inserción dinámicos
    public void insertarInicio(Object valor) {
        if (tamaño == elementos.length) {
            redimensionar(elementos.length * 2); // Duplica la capacidad
        }

        if (estaVacia()) {
            frente = fin = 0;
        } else {
            frente = (frente - 1 + elementos.length) % elementos.length;
        }
        elementos[frente] = valor;
        tamaño++;
    }

    public void insertarFinal(Object valor) {
        if (tamaño == elementos.length) {
            redimensionar(elementos.length * 2); // Duplica la capacidad
        }

        if (estaVacia()) {
            frente = fin = 0;
        } else {
            fin = (fin + 1) % elementos.length;
        }
        elementos[fin] = valor;
        tamaño++;
    }

    public String removerInicio() {
        if (tamaño == 0) throw new RuntimeException("Bicola vacía");
        String valor = (String) elementos[frente];
        frente = (frente + 1) % elementos.length;
        tamaño--;
        if (tamaño == 0) frente = fin = -1;
        return valor;
    }

    public String removerFinal() {
        if (tamaño == 0) throw new RuntimeException("Bicola vacía");
        String valor = (String) elementos[fin];
        fin = (fin - 1 + elementos.length) % elementos.length;
        tamaño--;
        if (tamaño == 0) frente = fin = -1;
        return valor;
    }

    public String buscar(Object valor) {
        if (tamaño == 0) return "Cola vacía";
        for (int i = 0, idx = frente; i < tamaño; i++, idx = (idx + 1) % elementos.length) {
            if (elementos[idx].equals(valor)) return "Elemento encontrado en posición " + i + "\n";
        }
        return "Elemento no encontrado\n";
    }

    public int tamaño() {
        return tamaño;
    }

    public String visualizar() {
        if (tamaño == 0) return "Bicola vacía.";
        StringBuilder sb = new StringBuilder("Bicola: \n");
        for (int i = 0, idx = frente; i < tamaño; i++, idx = (idx + 1) % elementos.length) {
            sb.append(elementos[idx]).append(" ->\n");
        }
        return sb.toString();
    }
    
    public boolean estaVacia()
    {
    	return tamaño == 0;
    }
}