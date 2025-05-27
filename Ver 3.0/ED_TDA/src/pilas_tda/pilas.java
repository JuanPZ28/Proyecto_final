package pilas_tda;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Implementación de pilas usando listas enlazadas y arreglos
 * @author David Valero
 */
public class pilas {
    
    /**
     * Clase abstracta base para las implementaciones de pila
     */
    public abstract static class Stack {
        /**
         * Agrega un elemento a la pila
         * @param value Valor a insertar
         */
        public abstract void push(int value);
        
        /**
         * Elimina y devuelve el elemento superior de la pila
         * @return Elemento eliminado o -1 si está vacía
         */
        public abstract int pop();
        
        /**
         * Busca un elemento en la pila
         * @param value Valor a buscar
         * @return true si se encuentra, false si no
         */
        public abstract boolean search(int value);
        
        /**
         * Devuelve el tamaño actual de la pila
         * @return Cantidad de elementos
         */
        public abstract int size();
        
        /**
         * Convierte la pila a una lista para visualización
         * @return Lista con los elementos
         */
        public abstract List<Integer> toList();
    }

    /**
     * Implementación de pila usando LinkedList
     */
    public static class ListStack extends Stack {
        private LinkedList<Integer> stack = new LinkedList<>();

        @Override
        public void push(int value) {
            stack.push(value);
        }

        @Override
        public int pop() {
            return stack.isEmpty() ? -1 : stack.pop();
        }

        @Override
        public boolean search(int value) {
            return stack.contains(value);
        }

        @Override
        public int size() {
            return stack.size();
        }

        @Override
        public List<Integer> toList() {
            return new LinkedList<>(stack);
        }
    }

    /**
     * Implementación de pila usando arreglos
     */
    public static class ArrayStack extends Stack {
        private int[] stack;
        private int top;
        private final int capacity;

        /**
         * Constructor para pila basada en arreglos
         * @param size Tamaño máximo de la pila
         */
        public ArrayStack(int size) {
            stack = new int[size];
            capacity = size;
            top = -1;
        }

        @Override
        public void push(int value) {
            if(top == capacity - 1) {
                JOptionPane.showMessageDialog(null, "Pila llena", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            stack[++top] = value;
        }

        @Override
        public int pop() {
            return top == -1 ? -1 : stack[top--];
        }

        @Override
        public boolean search(int value) {
            for(int i = 0; i <= top; i++) {
                if(stack[i] == value) return true;
            }
            return false;
        }

        @Override
        public int size() {
            return top + 1;
        }

        @Override
        public List<Integer> toList() {
            List<Integer> lista = new LinkedList<>();
            for(int i = top; i >= 0; i--) {
                lista.add(stack[i]);
            }
            return lista;
        }
    }
}