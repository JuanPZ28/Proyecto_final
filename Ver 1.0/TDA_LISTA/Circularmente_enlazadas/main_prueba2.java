package Circularmente_enlazadas;

public class main_prueba2 {
    public static void main(String[] args) {
        CircularLinkedList lista = new CircularLinkedList();

        lista.insertAtEnd(10);
        lista.insertAtEnd(20);
        lista.insertAtEnd(30);
        lista.insertAtEnd(40);

        //MODULO DE OPERACIONES EN LA LISTA SIMPLEMENTE ENLAZADA-----------------------------------------------------------------------------------
        // Medicion de tiempo y memoria
        Runtime runtime = Runtime.getRuntime(); //Instancia de la clase Runtime
        //Acceso al entorno de ejecuion de la maquina de java, donde se le solicite que obtenga el tiempo de recorrido
        runtime.gc(); // Limpiar memoria

        long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
        long tiempoInicio = System.nanoTime();
//Cambiar metodo
        lista.insertAtEnd(90);
        lista.size();
        long tiempoFin = System.nanoTime();
        long memoriaDespues = runtime.totalMemory() - runtime.freeMemory();
 //MODULO DE OPERACIONES EN LA LISTA SIMPLEMENTE ENLAZADA-----------------------------------------------------------------------------------
       
        System.out.println("Tiempo de ejecución (ns): " + (tiempoFin - tiempoInicio));
        System.out.println("Memoria usada (bytes): " + (memoriaDespues - memoriaAntes));
        
    }
}