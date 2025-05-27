package ui;


import javax.swing.*;

import modelos.BiCola;

import java.awt.*;
import java.util.Random;

public class VentanaBiCola extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BiCola bicola = new BiCola();

    private JTextField campoValor = new JTextField(10);
    private JTextField campoBuscar = new JTextField(10);
    private JTextField campoCantidad = new JTextField("10", 5);
    private JTextArea salidaArea = new JTextArea();

    public VentanaBiCola() {
        setTitle("BICOLA PERSONALIZADA");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel titulo = new JLabel("BICOLA");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelSuperior = new JPanel(new GridLayout(3, 1, 5, 5));

        // Línea 1: insertar valor
        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila1.add(new JLabel("Valor:"));
        fila1.add(campoValor);
        // Línea 2: buscar y eliminar
        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila2.add(new JLabel("Buscar:"));
        fila2.add(campoBuscar);

        // Línea 3: aleatorios y visualizar
        JPanel fila3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila3.add(new JLabel("Cantidad aleatorios:"));
        fila3.add(campoCantidad);

        panelSuperior.add(fila1);
        panelSuperior.add(fila2);
        panelSuperior.add(fila3);

        // Área de resultados
        salidaArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(salidaArea);
        scroll.setPreferredSize(new Dimension(650, 250));
        
     // Botones para fila1: insertar
        JButton btnInsertarFrente = new JButton("Insertar al Frente");
        JButton btnInsertarFinal = new JButton("Insertar al Final");
        fila1.add(btnInsertarFrente);
        fila1.add(btnInsertarFinal);

        // Botones para fila2: buscar y eliminar
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEliminarFrente = new JButton("Eliminar Frente");
        JButton btnEliminarFinal = new JButton("Eliminar Final");
        fila2.add(btnBuscar);
        fila2.add(btnEliminarFrente);
        fila2.add(btnEliminarFinal);

        // Botones para fila3: aleatorios, tamaño, visualizar
        JButton btnAleatoriosFrente = new JButton("Aleatorios Frente");
        JButton btnAleatoriosFinal = new JButton("Aleatorios Final");
        JButton btnTamano = new JButton("Tamaño");
        JButton btnVisualizar = new JButton("Visualizar");
        fila3.add(btnAleatoriosFrente);
        fila3.add(btnAleatoriosFinal);
        fila3.add(btnTamano);
        fila3.add(btnVisualizar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titulo, BorderLayout.NORTH);
        getContentPane().add(panelSuperior, BorderLayout.CENTER);
        getContentPane().add(scroll, BorderLayout.SOUTH);
        
        btnInsertarFrente.addActionListener(e -> insertarElementosFrente());
        btnInsertarFinal.addActionListener(e -> insertarElementosFinal());
        btnBuscar.addActionListener(e -> buscarElemento());
        btnEliminarFrente.addActionListener(e -> eliminarFrente());
        btnEliminarFinal.addActionListener(e -> eliminarFinal());
        btnAleatoriosFrente.addActionListener(e -> insertarAleatorios(true));
        btnAleatoriosFinal.addActionListener(e -> insertarAleatorios(false));
        btnTamano.addActionListener(e -> mostrarTamano());
        btnVisualizar.addActionListener(e -> visualizarBicola());

    }

    private void insertarElementosFrente() {
        insertarElementos(true); // true = frente
    }

    private void insertarElementosFinal() {
        insertarElementos(false); // false = final
    }

    private void insertarElementos(boolean alFrente) {
        String texto = campoValor.getText().trim();
        if (!texto.isEmpty()) {
            String[] elementos = texto.split(",");
            long startTime = System.nanoTime();
            long startMemory = getUsedMemory();

            try {
                for (String el : elementos) {
                    String trimmed = el.trim();
                    if (!trimmed.isEmpty()) {
                        if (alFrente) {
                            bicola.insertarInicio(trimmed);
                        } else {
                            bicola.insertarFinal(trimmed);
                        }
                    }
                }
            } catch (RuntimeException ex) {
                salidaArea.append("⚠ " + ex.getMessage() + "\n");
                return;
            }

            long endMemory = getUsedMemory();
            long endTime = System.nanoTime();

            salidaArea.append("✓ Elementos insertados al " + (alFrente ? "frente" : "final") + ".\n");
            salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
        } else {
            salidaArea.append("⚠ Debes ingresar al menos un elemento.\n\n");
        }
    }
    
    private void insertarAleatorios(boolean alFrente) {
        String texto = campoCantidad.getText().trim();
        int cantidad;
        try {
            cantidad = Integer.parseInt(texto);
            if (cantidad <= 0) {
                salidaArea.append("⚠ Ingresa un número entero positivo para la cantidad.\n\n");
                return;
            }
        } catch (NumberFormatException e) {
            salidaArea.append("⚠ Valor inválido para cantidad.\n\n");
            return;
        }

        Random rnd = new Random();
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory();

        try {
            for (int i = 0; i < cantidad; i++) {
                int num = rnd.nextInt(10000);
                String elem = "Val" + num;
                if (alFrente) {
                    bicola.insertarInicio(elem);
                } else {
                    bicola.insertarFinal(elem);
                }
            }
        } catch (RuntimeException ex) {
            salidaArea.append("⚠ " + ex.getMessage() + "\n");
            return;
        }

        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();

        salidaArea.append("✓ " + cantidad + " elementos aleatorios insertados al " + (alFrente ? "frente" : "final") + ".\n");
        salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
    }

    private void visualizarBicola() {
        if (bicola.estaVacia()) {
            salidaArea.append("La bicola está vacía.\n\n");
            return;
        }
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory();

        salidaArea.append(bicola.visualizar());

        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();
        salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
    }

    private void buscarElemento() {
        String valor = campoBuscar.getText().trim();
        if (valor.isEmpty()) {
            salidaArea.append("⚠ Ingresa un valor para buscar.\n\n");
            return;
        }

        long start = System.nanoTime();
        long memStart = getUsedMemory();

        salidaArea.append(bicola.buscar(valor));
        
        long end = System.nanoTime();
        long memEnd = getUsedMemory();
        
        salidaArea.append(formatUsedMemory(memStart, memEnd) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (end - start) + "\n\n");
    }

    private void eliminarFrente() {
        eliminarElemento(true); // true = frente
    }

    private void eliminarFinal() {
        eliminarElemento(false); // false = final
    }

    private void eliminarElemento(boolean delFrente) {
        if (bicola.estaVacia()) {
            salidaArea.append("⚠ Bicola vacía, no se puede eliminar.\n\n");
            return;
        }

        long start = System.nanoTime();
        long memStart = getUsedMemory();

        try {
            String removido = delFrente ? bicola.removerInicio() : bicola.removerFinal();
            long memEnd = getUsedMemory();
            long end = System.nanoTime();

            salidaArea.append("Elemento eliminado del " + (delFrente ? "frente" : "final") + ": " + removido + "\n");
            salidaArea.append(formatUsedMemory(memStart, memEnd) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (end - start) + "\n\n");
        } catch (RuntimeException ex) {
            salidaArea.append("⚠ " + ex.getMessage() + "\n\n");
        }
    }

    private void mostrarTamano() {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory();

        int tamano = bicola.tamaño();
        salidaArea.append("Tamaño actual de la bicola: " + tamano + "\n");

        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();

        salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
    }

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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaBiCola().setVisible(true));
    }
}
