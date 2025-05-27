package colas_ui;

import javax.swing.*;

import colas.ColaPrioridad;
import colas.ColaPrioridad.ElementoPrioridad;

import java.awt.*;
import java.util.Random;

/**
 * Interfaz de la Cola de Prioridad
 * 
 * @author Juan José García
 */
public class VentanaColaPrioridad extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField valorCampo, prioridadCampo, buscarCampo;
    private JTextArea salidaArea;
    private JButton insertarBtn, removerBtn, buscarBtn, visualizarBtn, tamanoBtn;
    private ColaPrioridad<String> colaPrioridad;
    private JTextField txtCantidadMultiple = new JTextField(5);
    private JButton btnInsertarMultiple = new JButton("Insertar múltiples");

    /**
     * Constructor de la Ventana de la Cola de Prioridad
     */
    public VentanaColaPrioridad() {
        colaPrioridad = new ColaPrioridad<>();
        setTitle("COLA DE PRIORIDAD");
        setSize(650, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel titulo = new JLabel("COLA DE PRIORIDAD");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(200, 10, 250, 30);
        getContentPane().add(titulo);

        JLabel valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(20, 37, 50, 25);
        getContentPane().add(valorLabel);

        valorCampo = new JTextField();
        valorCampo.setBounds(70, 37, 150, 25);
        getContentPane().add(valorCampo);

        JLabel prioridadLabel = new JLabel("Prioridad:");
        prioridadLabel.setBounds(230, 37, 70, 25);
        getContentPane().add(prioridadLabel);

        prioridadCampo = new JTextField();
        prioridadCampo.setBounds(310, 37, 50, 25);
        getContentPane().add(prioridadCampo);

        insertarBtn = new JButton("Insertar");
        insertarBtn.setBounds(370, 37, 100, 25);
        getContentPane().add(insertarBtn);

        insertarBtn.addActionListener(e -> insertarElemento());

        visualizarBtn = new JButton("Visualizar cola");
        visualizarBtn.setBounds(480, 37, 140, 25);
        getContentPane().add(visualizarBtn);

        visualizarBtn.addActionListener(e -> visualizarCola());

        JLabel buscarLabel = new JLabel("Buscar valor:");
        buscarLabel.setBounds(20, 68, 100, 25);
        getContentPane().add(buscarLabel);

        buscarCampo = new JTextField();
        buscarCampo.setBounds(120, 68, 150, 25);
        getContentPane().add(buscarCampo);

        buscarBtn = new JButton("Buscar");
        buscarBtn.setBounds(280, 68, 90, 25);
        getContentPane().add(buscarBtn);

        buscarBtn.addActionListener(e -> buscarElemento());

        removerBtn = new JButton("Remover");
        removerBtn.setBounds(380, 68, 100, 25);
        getContentPane().add(removerBtn);

        removerBtn.addActionListener(e -> removerElemento());

        tamanoBtn = new JButton("Tamaño actual");
        tamanoBtn.setBounds(490, 68, 130, 25);
        getContentPane().add(tamanoBtn);

        tamanoBtn.addActionListener(e -> mostrarTamano());
        
        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setBounds(20, 104, 70, 25);
        getContentPane().add(cantidadLabel);

        txtCantidadMultiple.setBounds(90, 104, 50, 25);
        getContentPane().add(txtCantidadMultiple);

        btnInsertarMultiple.setBounds(150, 104, 150, 25);
        getContentPane().add(btnInsertarMultiple);
        
        btnInsertarMultiple.addActionListener(e -> {
            try {
                int cantidad = Integer.parseInt(txtCantidadMultiple.getText());
                insertarElementosAleatorios(cantidad);
            } catch (NumberFormatException ex) {
                salidaArea.append("⚠ Ingrese una cantidad válida.\n\n");
            }
        });


        salidaArea = new JTextArea();
        salidaArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(salidaArea);
        scroll.setBounds(20, 140, 600, 300);
        getContentPane().add(scroll);
    }

    /**
     * Insertar elementos en la Cola de Prioridad.
     * 
     * <p> Obtiene el dato del campo y la prioridad de campo.
     * 
     * <p> Procede a encolar el dato con prioridad.
     */
    private void insertarElemento() {
        String valor = valorCampo.getText().trim();
        String prioridadStr = prioridadCampo.getText().trim();

        if (!valor.isEmpty() && !prioridadStr.isEmpty()) {
            try {
                int prioridad = Integer.parseInt(prioridadStr);
                long startTime = System.nanoTime();
                long startMemory = getUsedMemory();

                colaPrioridad.encolar(new ElementoPrioridad<>(valor, prioridad));

                long endMemory = getUsedMemory();
                long endTime = System.nanoTime();

                salidaArea.append("✓ Elemento insertado con prioridad " + prioridad + ".\n");
                salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
                salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
            } catch (NumberFormatException e) {
                salidaArea.append("⚠ Prioridad inválida. Debe ser un número entero.\n\n");
            }
        } else {
            salidaArea.append("⚠ Completa todos los campos.\n\n");
        }
    }

    /**
     * Visualiza la Cola de Prioridad
     */
    private void visualizarCola() {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory();

        salidaArea.append("Contenido de la cola (prioridad menor primero):\n");
        salidaArea.append(colaPrioridad.toString() + "\n");

        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();

        salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
    }

    /**
     * Buscca un elemento en la Cola de Prioridad
     */
    private void buscarElemento() {
        String valor = buscarCampo.getText().trim();
        if (!valor.isEmpty()) {
            long start = System.nanoTime();
            long startMemory = getUsedMemory();

            boolean encontrado = false;
            for (int i = 0; i < colaPrioridad.size(); i++) {
                ElementoPrioridad<String> elem = colaPrioridad.frente(); // siempre es el primero
                if (elem != null && elem.valor.equals(valor)) {
                    encontrado = true;
                    break;
                }
                // para evitar perder elementos, rotamos la cola
                colaPrioridad.encolar(colaPrioridad.desencolar());
            }

            long endMemory = getUsedMemory();
            long end = System.nanoTime();

            salidaArea.append("Elemento " + valor + (encontrado ? " encontrado." : " no encontrado.") + "\n");
            salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (end - start) + "\n\n");
        } else {
            salidaArea.append("⚠ Ingresa un valor para buscar.\n\n");
        }
    }

    /**
     * Elimina un dato de la Cola de Prioridad
     * 
     * <p> Revisa si está vacía. Luego, obtiene el dato desencolado.
     */
    private void removerElemento() {
        if (!colaPrioridad.estaVacia()) {
            long start = System.nanoTime();
            long startMemory = getUsedMemory();

            ElementoPrioridad<String> removido = colaPrioridad.desencolar();

            long endMemory = getUsedMemory();
            long end = System.nanoTime();

            salidaArea.append("Elemento removido: " + removido + "\n");
            salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (end - start) + "\n\n");
        } else {
            salidaArea.append("⚠ Cola vacía, no se puede remover.\n\n");
        }
    }

    /**
     * Muestra el tamaño de la Cola de Prioridad
     */
    private void mostrarTamano() {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory();

        int tamano = colaPrioridad.size();
        salidaArea.append("Tamaño actual de la cola: " + tamano + "\n");

        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();

        salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
    }
    
    /**
     * Insertar elementos aleatorios a la Cola de Prioridad
     * 
     * <p> Genera un valor y prioridad y crea el dato con esos atributos.
     * @param cantidad de datos a generar
     */
    private void insertarElementosAleatorios(int cantidad) {
        try {
            Random rand = new Random();

            long memoriaAntes = getUsedMemory();
            long tiempoInicio = System.nanoTime();

            for (int i = 0; i < cantidad; i++) {
                String valor = "Val" + rand.nextInt(10000);
                int prioridad = rand.nextInt(10) + 1;
                colaPrioridad.encolar(new ElementoPrioridad<>(valor, prioridad));
            }

            long tiempoFin = System.nanoTime();
            long memoriaDespues = getUsedMemory();

            salidaArea.append("✔ " + cantidad + " elementos insertados aleatoriamente.\n");
            salidaArea.append(formatUsedMemory(memoriaAntes, memoriaDespues) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (tiempoFin - tiempoInicio) + "\n\n");

        } catch (Exception e) {
            // Aquí puedes manejar excepciones que consideres necesarias
            salidaArea.append("⚠ Error al insertar elementos.\n\n");
        }
    }

    /**
     * Benchmark para la memoria
     * @return
     */
    private long getUsedMemory() {
    	Runtime runtime = Runtime.getRuntime();
        System.gc(); // Solicita recolección de basura
        try {
            Thread.sleep(100); // Espera un poco para que GC tenga tiempo de actuar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // buena práctica
        }
        return runtime.totalMemory() - runtime.freeMemory();
    }

    /**
     * Permite generar un formato para la memoria utilizada
     * @param before memoria antes de ejecución
     * @param after memoria después de ejecución
     * @return Memoria total utilizada
     */
    private String formatUsedMemory(long before, long after) {
        long diff = after - before;
        System.out.println(diff);
        if (diff > 0) {
            return "Memoria usada (bytes): " + diff;
        } else if (diff < 0) {
            return "Memoria usada (bytes): 0 (liberación de memoria por GC)";
        } else {
            return "Memoria usada (bytes): ≈0 (sin cambios visibles)";
        }
    }
}
