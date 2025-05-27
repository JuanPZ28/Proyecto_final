package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Circularmente_enlazadas.Circular_enlazada;
import Doblemente_enlazadas.Doblemente_enlazada;
import Listas.Simplemente_enlazada;
import colas_ui.VentanaBiCola;
import colas_ui.VentanaColaCircular;
import colas_ui.VentanaColaPrioridad;
import colas_ui.VentanaColaSimple;
import pilas_tda.formulario_pilas;

/**
 * Interfaz de la Clase Main
 */
public class main_ui extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JMenuItem mntmListaSimple, mntmListaDoble, mntmListaCircular;
    private JMenuItem mntmPilaSimple, mntmColaSimple, mntmBiCola;
    private JMenuItem mntmColaCircular, mntmColaPrioridad, mntmSalir;

    /**
     * Constructor de la main_ui
     */
    public main_ui() {
        setTitle("Estructura de Datos Lineal - Tipos de Datos Abstractos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 434, 22);
        contentPane.add(menuBar);

        // Menú Listas
        JMenu mnListas = new JMenu("Listas");
        menuBar.add(mnListas);
        
        mntmListaSimple = new JMenuItem("Simplemente Enlazada");
        mntmListaSimple.addActionListener(this);
        mnListas.add(mntmListaSimple);
        
        mntmListaDoble = new JMenuItem("Doblemente Enlazada");
        mntmListaDoble.addActionListener(this);
        mnListas.add(mntmListaDoble);
        
        mntmListaCircular = new JMenuItem("Circularmente Enlazada");
        mntmListaCircular.addActionListener(this);
        mnListas.add(mntmListaCircular);

        // Menú Pilas
        JMenu mnPilas = new JMenu("Pilas");
        menuBar.add(mnPilas);
        
        mntmPilaSimple = new JMenuItem("Pilas");
        mntmPilaSimple.addActionListener(this);
        mnPilas.add(mntmPilaSimple);

        // Menú Colas
        JMenu mnColas = new JMenu("Colas");
        menuBar.add(mnColas);
        
        mntmColaSimple = new JMenuItem("Simple");
        mntmColaSimple.addActionListener(this);
        mnColas.add(mntmColaSimple);
        
        mntmBiCola = new JMenuItem("Bicola");
        mntmBiCola.addActionListener(this);
        mnColas.add(mntmBiCola);
        
        mntmColaCircular = new JMenuItem("Circular");
        mntmColaCircular.addActionListener(this);
        mnColas.add(mntmColaCircular);
        
        mntmColaPrioridad = new JMenuItem("Prioridad");
        mntmColaPrioridad.addActionListener(this);
        mnColas.add(mntmColaPrioridad);

        // Menú Salir
        JMenu mnSalir = new JMenu("Salir");
        menuBar.add(mnSalir);
        
        mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(this);
        mnSalir.add(mntmSalir);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mntmListaSimple) {
            new Simplemente_enlazada().setVisible(true);
        } 
        else if (e.getSource() == mntmListaDoble) {
            new Doblemente_enlazada().setVisible(true);
        }
        else if (e.getSource() == mntmListaCircular) {
            new Circular_enlazada().setVisible(true);
        }
        else if (e.getSource() == mntmPilaSimple) {
            new formulario_pilas().setVisible(true);
        }
        else if (e.getSource() == mntmColaSimple) {
            new VentanaColaSimple().setVisible(true);
        }
        else if (e.getSource() == mntmBiCola) {
            new VentanaBiCola().setVisible(true);
        }
        else if (e.getSource() == mntmColaCircular) {
            new VentanaColaCircular().setVisible(true);
        }
        else if (e.getSource() == mntmColaPrioridad) {
            new VentanaColaPrioridad().setVisible(true);
        }
        else if (e.getSource() == mntmSalir) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                main_ui frame = new main_ui();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}