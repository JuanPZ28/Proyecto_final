package pilas_tda;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Interfaz gráfica para manipular pilas
 * @author David Valero
 */
public class formulario_pilas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_insertar;
    private JTextField txt_remover;
    private JTextField txt_buscar;
    private JTextArea txta_visualizacion;
    private JTextField txt_cantidad;
    private JButton btn_switch;
    private JButton btn_agregarMultiples;
    
    private boolean usarLista = true;
    private pilas.Stack pila;
    private final int MAX_ARRAY = 1000;


    /**
     * Constructor de la interfaz gráfica
     */
    public formulario_pilas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        inicializarPila();
        crearComponentes();
    }

    /**
     * Inicializa la pila según el tipo seleccionado
     */
    private void inicializarPila() {
        if(usarLista) {
            pila = new pilas.ListStack();
        } else {
            pila = new pilas.ArrayStack(MAX_ARRAY);
        }
    }

    /**
     * Crea y configura los componentes de la interfaz
     */
    private void crearComponentes() {
        // Configuración de título y botón de cambio
        JLabel lblTitulo = new JLabel("PILAS TDA - Modo: " + (usarLista ? "Lista Enlazada" : "Arreglo"));
        lblTitulo.setBounds(180, 10, 300, 20);
        contentPane.add(lblTitulo);
        
        btn_switch = new JButton("Cambiar a " + (usarLista ? "Arreglo" : "Lista"));
        btn_switch.setBounds(450, 10, 150, 20);
        btn_switch.addActionListener(e -> cambiarImplementacion());
        contentPane.add(btn_switch);

        // Área de visualización
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(180, 40, 400, 150);
        contentPane.add(scrollPane);
        
        txta_visualizacion = new JTextArea();
        txta_visualizacion.setEditable(false);
        scrollPane.setViewportView(txta_visualizacion);

        // Campos de texto
        txt_insertar = new JTextField();
        txt_insertar.setBounds(50, 210, 100, 20);
        contentPane.add(txt_insertar);
        
        txt_remover = new JTextField();
        txt_remover.setBounds(50, 250, 100, 20);
        contentPane.add(txt_remover);
        
        txt_buscar = new JTextField();
        txt_buscar.setBounds(50, 290, 100, 20);
        contentPane.add(txt_buscar);
        
        txt_cantidad = new JTextField();
        txt_cantidad.setBounds(50, 330, 100, 20);
        contentPane.add(txt_cantidad);

        // Botón para agregar múltiples datos
        btn_agregarMultiples = new JButton("Agregar N datos");
        btn_agregarMultiples.setBounds(50, 370, 150, 25);
        btn_agregarMultiples.addActionListener(e -> agregarMultiplesDatos());
        contentPane.add(btn_agregarMultiples);

        // Botones de operaciones
        crearBoton("Insertar", 180, 210, e -> insertar());
        crearBoton("Remover", 180, 250, e -> remover());
        crearBoton("Buscar", 180, 290, e -> buscar());
        crearBoton("Tamaño", 180, 330, e -> mostrarTamanio());
        crearBoton("Rellenar", 300, 330, e -> rellenarAleatorios());
        crearBoton("Visualizar", 300, 210, e -> actualizarVisualizacion());
    }

    /**
     * Agrega múltiples datos aleatorios a la pila
     */
    private void agregarMultiplesDatos() {
        try {
            String input = JOptionPane.showInputDialog(this, 
                "¿Cuántos datos aleatorios desea agregar?", 
                "Agregar múltiples datos", 
                JOptionPane.QUESTION_MESSAGE);
            
            if(input != null && !input.isEmpty()) {
                int cantidad = Integer.parseInt(input);
                if(cantidad <= 0) {
                    mostrarError("Debe ingresar un número positivo");
                    return;
                }
                
                Random rand = new Random();
                long inicio = System.nanoTime();
                
                for(int i = 0; i < cantidad; i++) {
                    pila.push(rand.nextInt(1000));
                }
                
                long tiempo = System.nanoTime() - inicio;
                actualizarVisualizacion();
                
                JOptionPane.showMessageDialog(this, 
                    String.format("Se agregaron %,d elementos\nTiempo: %,d ns", cantidad, tiempo));
            }
        } catch (NumberFormatException ex) {
            mostrarError("Ingrese un número válido");
        }
    }

    /**
     * Crea un botón con configuración básica
     * @param texto Texto del botón
     * @param x Posición x
     * @param y Posición y
     * @param action Acción a ejecutar
     */
    private void crearBoton(String texto, int x, int y, ActionListener action) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, 100, 20);
        btn.addActionListener(action);
        contentPane.add(btn);
    }

    /**
     * Cambia entre implementaciones de pila
     */
    private void cambiarImplementacion() {
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Cambiar a implementación con " + (usarLista ? "arreglo" : "lista") + "?\nSe perderán los datos actuales.",
            "Confirmar cambio",
            JOptionPane.YES_NO_OPTION);
        
        if(respuesta == JOptionPane.YES_OPTION) {
            usarLista = !usarLista;
            inicializarPila();
            actualizarTitulo();
            actualizarVisualizacion();
        }
    }

    /**
     * Actualiza el título de la ventana
     */
    private void actualizarTitulo() {
        ((JLabel)contentPane.getComponent(0)).setText("PILAS TDA - Modo: " + (usarLista ? "Lista Enlazada" : "Arreglo"));
        btn_switch.setText("Cambiar a " + (usarLista ? "Arreglo" : "Lista"));
    }

    /**
     * Inserta un elemento en la pila
     */
    private void insertar() {
        try {
            int valor = Integer.parseInt(txt_insertar.getText());
            pila.push(valor);
            txt_insertar.setText("");
            actualizarVisualizacion();
        } catch (Exception ex) {
            mostrarError("Ingrese un número válido");
        }
    }

    /**
     * Remueve un elemento de la pila
     */
    private void remover() {
        int elemento = pila.pop();
        txt_remover.setText(elemento != -1 ? String.valueOf(elemento) : "");
        if(elemento == -1) {
            mostrarError("La pila está vacía");
        }
        actualizarVisualizacion();
    }

    /**
     * Busca un elemento en la pila
     */
    private void buscar() {
        try {
            int valor = Integer.parseInt(txt_buscar.getText());
            JOptionPane.showMessageDialog(this, pila.search(valor) ? "Elemento encontrado" : "Elemento no encontrado");
        } catch (Exception ex) {
            mostrarError("Ingrese un número válido");
        }
    }

    /**
     * Muestra el tamaño actual de la pila
     */
    private void mostrarTamanio() {
        JOptionPane.showMessageDialog(this, "Tamaño actual: " + pila.size());
    }

    /**
     * Rellena la pila con datos aleatorios
     */
    private void rellenarAleatorios() {
        try {
            int cantidad = Integer.parseInt(txt_cantidad.getText());
            if(cantidad <= 0) throw new NumberFormatException();
            
            Random rand = new Random();
            long inicio = System.nanoTime();
            
            for(int i = 0; i < cantidad; i++) {
                pila.push(rand.nextInt(1000));
            }
            
            long tiempo = System.nanoTime() - inicio;
            actualizarVisualizacion();
            
            JOptionPane.showMessageDialog(this, 
                String.format("Operación completada:\nTiempo: %,d ns\nElementos añadidos: %,d", tiempo, cantidad));
        } catch (Exception ex) {
            mostrarError("Ingrese una cantidad válida (número positivo)");
        }
    }

    /**
     * Actualiza la visualización de la pila
     */
    private void actualizarVisualizacion() {
        StringBuilder sb = new StringBuilder();
        for(Integer num : pila.toList()) {
            sb.insert(0, num + "\n");
        }
        txta_visualizacion.setText(sb.toString());
    }

    /**
     * Muestra un mensaje de error
     * @param mensaje Texto del error a mostrar
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}