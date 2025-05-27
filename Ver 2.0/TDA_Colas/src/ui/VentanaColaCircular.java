package ui;

import modelos.ColaCircular;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class VentanaColaCircular extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField entradaCampo, buscarCampo, cantidadAleatoriaCampo;
    private JTextArea salidaArea;
    private JButton insertarBtn, removerBtn, buscarBtn, visualizarBtn, tamanoBtn, insertarAleatoriosBtn;
    private ColaCircular<String> colaCircular;

    public VentanaColaCircular() {
        colaCircular = new ColaCircular<>(20); // capacidad inicial 20, crecerá si es necesario
        setTitle("COLA CIRCULAR");
        setSize(650, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("COLA CIRCULAR");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(250, 10, 200, 30);
        add(titulo);

        JLabel entradaLabel = new JLabel("Ingresa elementos separados por ',' :");
        entradaLabel.setBounds(20, 50, 250, 25);
        add(entradaLabel);

        entradaCampo = new JTextField();
        entradaCampo.setBounds(270, 50, 250, 25);
        add(entradaCampo);

        insertarBtn = new JButton("Insertar");
        insertarBtn.setBounds(530, 50, 90, 25);
        add(insertarBtn);
        insertarBtn.addActionListener(e -> insertarElementos());

        visualizarBtn = new JButton("Visualizar cola");
        visualizarBtn.setBounds(20, 90, 150, 25);
        add(visualizarBtn);
        visualizarBtn.addActionListener(e -> visualizarCola());

        JLabel opLabel = new JLabel("Operaciones unitarias:");
        opLabel.setBounds(20, 130, 200, 25);
        add(opLabel);

        buscarCampo = new JTextField();
        buscarCampo.setBounds(20, 160, 150, 25);
        add(buscarCampo);

        buscarBtn = new JButton("Buscar");
        buscarBtn.setBounds(180, 160, 100, 25);
        add(buscarBtn);
        buscarBtn.addActionListener(e -> buscarElemento());

        removerBtn = new JButton("Remover");
        removerBtn.setBounds(290, 160, 100, 25);
        add(removerBtn);
        removerBtn.addActionListener(e -> removerElemento());

        tamanoBtn = new JButton("Tamaño actual");
        tamanoBtn.setBounds(400, 160, 150, 25);
        add(tamanoBtn);
        tamanoBtn.addActionListener(e -> mostrarTamano());

        // Entrada para cantidad de aleatorios
        JLabel cantidadAleatoriaLabel = new JLabel("Cantidad de aleatorios:");
        cantidadAleatoriaLabel.setBounds(20, 200, 150, 25);
        add(cantidadAleatoriaLabel);

        cantidadAleatoriaCampo = new JTextField("10");
        cantidadAleatoriaCampo.setBounds(170, 200, 100, 25);
        add(cantidadAleatoriaCampo);

        insertarAleatoriosBtn = new JButton("Insertar aleatorios");
        insertarAleatoriosBtn.setBounds(280, 200, 170, 25);
        add(insertarAleatoriosBtn);
        insertarAleatoriosBtn.addActionListener(e -> insertarAleatorios());

        salidaArea = new JTextArea();
        salidaArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(salidaArea);
        scroll.setBounds(20, 240, 600, 260);
        add(scroll);
    }

    private void insertarElementos() {
        String texto = entradaCampo.getText().trim();
        if (!texto.isEmpty()) {
            String[] elementos = texto.split(",");
            long startTime = System.nanoTime();
            long startMemory = getUsedMemory();

            try {
                for (String el : elementos) {
                    String trimmed = el.trim();
                    if (!trimmed.isEmpty()) {
                        colaCircular.encolar(trimmed);
                    }
                }
            } catch (RuntimeException ex) {
                salidaArea.append("⚠ " + ex.getMessage() + "\n");
                return;
            }

            long endMemory = getUsedMemory();
            long endTime = System.nanoTime();

            salidaArea.append("✓ Elementos insertados.\n");
            salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
        } else {
            salidaArea.append("⚠ Debes ingresar al menos un elemento.\n\n");
        }
    }

    private void insertarAleatorios() {
        String texto = cantidadAleatoriaCampo.getText().trim();
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
                colaCircular.encolar(elem);
            }
        } catch (RuntimeException ex) {
            salidaArea.append("⚠ " + ex.getMessage() + "\n");
            return;
        }

        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();

        salidaArea.append("✓ " + cantidad + " elementos aleatorios insertados.\n");
        salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
    }

    private void visualizarCola() {
        if (colaCircular.estaVacia()) {
            salidaArea.append("La cola está vacía.\n\n");
            return;
        }
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory();
        StringBuilder sb = new StringBuilder();
        for (String elem : colaCircular.obtenerContenido()) {
            sb.append(elem).append(", \n");
        }
        String resultado = sb.toString();
        if (resultado.endsWith(", ")) resultado = resultado.substring(0, resultado.length() - 2);
        
        salidaArea.append("Contenido de la cola: [" + resultado + "]\n\n");
        
        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();
        salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
    }

    private void buscarElemento() {
        String valor = buscarCampo.getText().trim();
        if (valor.isEmpty()) {
            salidaArea.append("⚠ Ingresa un valor para buscar.\n\n");
            return;
        }
        long start = System.nanoTime();
        long memStart = getUsedMemory();

        boolean encontrado = false;
        for (Object elem : colaCircular.obtenerContenido()) {
            if (valor.equals(elem.toString())) {
                encontrado = true;
                break;
            }
        }

        long memEnd = getUsedMemory();
        long end = System.nanoTime();

        salidaArea.append("Elemento '" + valor + "'" + (encontrado ? " encontrado." : " no encontrado.") + "\n");
        salidaArea.append(formatUsedMemory(memStart, memEnd) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (end - start) + "\n\n");
    }

    private void removerElemento() {
        if (colaCircular.estaVacia()) {
            salidaArea.append("⚠ Cola vacía, no se puede remover.\n\n");
            return;
        }
        long start = System.nanoTime();
        long memStart = getUsedMemory();

        try {
            String removido = colaCircular.desencolar();
            long memEnd = getUsedMemory();
            long end = System.nanoTime();

            salidaArea.append("Elemento removido: " + removido + "\n");
            salidaArea.append(formatUsedMemory(memStart, memEnd) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (end - start) + "\n\n");
        } catch (RuntimeException ex) {
            salidaArea.append("⚠ " + ex.getMessage() + "\n\n");
        }
    }

    private void mostrarTamano() {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory();

        int tamano = colaCircular.tamaño();
        salidaArea.append("Tamaño actual de la cola: " + tamano + "\n");

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
        SwingUtilities.invokeLater(() -> new VentanaColaCircular().setVisible(true));
    }
}