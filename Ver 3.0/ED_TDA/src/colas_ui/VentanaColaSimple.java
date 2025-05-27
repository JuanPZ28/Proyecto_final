package colas_ui;

import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import colas.ColaSimple;

/**
 * Interfaz de la Cola Simple
 * 
 * @author Juan José García
 */
public class VentanaColaSimple extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField valorCampo, buscarCampo, cantidadAleatoriaCampo;
    private JTextArea salidaArea;
    private JButton insertarBtn, removerBtn, buscarBtn, visualizarBtn, tamanoBtn, insertarAleatoriosBtn;
    private ColaSimple cola;

    /**
     * Constructor de la Cola Simple.
     */
    public VentanaColaSimple() {
        cola = new ColaSimple();

        setTitle("COLA SIMPLE PERSONALIZADA");
        setSize(650, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel titulo = new JLabel("COLA SIMPLE");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(250, 10, 200, 30);
        getContentPane().add(titulo);

        JLabel valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(20, 50, 50, 25);
        getContentPane().add(valorLabel);

        valorCampo = new JTextField();
        valorCampo.setBounds(70, 50, 200, 25);
        getContentPane().add(valorCampo);

        insertarBtn = new JButton("Insertar");
        insertarBtn.setBounds(280, 50, 100, 25);
        getContentPane().add(insertarBtn);

        insertarBtn.addActionListener(e -> insertarElemento());

        visualizarBtn = new JButton("Visualizar cola");
        visualizarBtn.setBounds(390, 50, 150, 25);
        getContentPane().add(visualizarBtn);

        visualizarBtn.addActionListener(e -> visualizarCola());

        JLabel buscarLabel = new JLabel("Buscar valor:");
        buscarLabel.setBounds(20, 90, 100, 25);
        getContentPane().add(buscarLabel);

        buscarCampo = new JTextField();
        buscarCampo.setBounds(120, 90, 150, 25);
        getContentPane().add(buscarCampo);

        buscarBtn = new JButton("Buscar");
        buscarBtn.setBounds(280, 90, 100, 25);
        getContentPane().add(buscarBtn);

        buscarBtn.addActionListener(e -> buscarElemento());

        removerBtn = new JButton("Remover");
        removerBtn.setBounds(390, 90, 100, 25);
        getContentPane().add(removerBtn);

        removerBtn.addActionListener(e -> removerElemento());

        tamanoBtn = new JButton("Tamaño actual");
        tamanoBtn.setBounds(500, 90, 120, 25);
        getContentPane().add(tamanoBtn);

        tamanoBtn.addActionListener(e -> mostrarTamano());
        
        cantidadAleatoriaCampo = new JTextField("10");
        cantidadAleatoriaCampo.setBounds(20, 126, 100, 25);
        getContentPane().add(cantidadAleatoriaCampo);

        insertarAleatoriosBtn = new JButton("Insertar aleatorios");
        insertarAleatoriosBtn.setBounds(130, 126, 170, 25);
        getContentPane().add(insertarAleatoriosBtn);
        insertarAleatoriosBtn.addActionListener(e -> insertarAleatorios());
        
        salidaArea = new JTextArea();
        salidaArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(salidaArea);
        scroll.setBounds(20, 163, 600, 277);
        getContentPane().add(scroll);
    }

    /**
     * Insertar un elemento en la Cola
     */
    private void insertarElemento() {
        String valor = valorCampo.getText().trim();
        if (!valor.isEmpty()) {
            long startTime = System.nanoTime();
            long startMemory = getUsedMemory();

            cola.insertar(valor);

            long endMemory = getUsedMemory();
            long endTime = System.nanoTime();

            salidaArea.append("✓ Elemento insertado: " + valor + "\n");
            salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
        } else {
            salidaArea.append("⚠ Ingresa un valor para insertar.\n\n");
        }
    }
 
    /**
     * Insertar elementos aleatorios a la Cola
     * 
     * <p> Utiliza un campo que le da la cantidad de datos que deberá producir
     */
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
                cola.insertar(elem);
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

    /**
     * Visualiza la Cola
     */
    private void visualizarCola() {
        if (cola.estaVacia()) {
            salidaArea.append("La cola está vacía.\n\n");
            return;
        }
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory();
        salidaArea.append(cola.toString());
        
        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();
        salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
    }

    /**
     * Busca un dato en la Cola
     */
    private void buscarElemento() {
        String valor = buscarCampo.getText().trim();
        if (!valor.isEmpty()) {
            long start = System.nanoTime();
            long memStart = getUsedMemory();

            boolean encontrado = cola.buscar(valor);

            long memEnd = getUsedMemory();
            long end = System.nanoTime();

            salidaArea.append("Elemento " + valor + (encontrado ? " encontrado." : " no encontrado.") + "\n");
            salidaArea.append(formatUsedMemory(memStart, memEnd) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (end - start) + "\n\n");
        } else {
            salidaArea.append("⚠ Ingresa un valor para buscar.\n\n");
        }
    }

    /**
     * Remueve un elemento de la Cola
     */
    private void removerElemento() {
        if (!cola.estaVacia()) {
            long start = System.nanoTime();
            long memStart = getUsedMemory();

            String removido = cola.remover();

            long memEnd = getUsedMemory();
            long end = System.nanoTime();

            salidaArea.append("Elemento removido: " + removido + "\n");
            salidaArea.append(formatUsedMemory(memStart, memEnd) + "\n");
            salidaArea.append("Tiempo de ejecución (ns): " + (end - start) + "\n\n");
        } else {
            salidaArea.append("⚠ Cola vacía, no se puede remover.\n\n");
        }
    }

    /**
     * Muestra el tamaño de la Cola
     */
    private void mostrarTamano() {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory();

        int tamano = cola.tamano();
        salidaArea.append("Tamaño actual de la cola: " + tamano + "\n");

        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();

        salidaArea.append(formatUsedMemory(startMemory, endMemory) + "\n");
        salidaArea.append("Tiempo de ejecución (ns): " + (endTime - startTime) + "\n\n");
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