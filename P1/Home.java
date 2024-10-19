import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Home {
    private static Operaciones op = new Operaciones(); // Definición de variable de clase

    public static void main(String[] args) {
        ventanaMain();
        ArrayList<AFN> afns = new ArrayList<AFN>();
    }


    public static void ventanaMain() {
        JFrame ventana = new JFrame("Analizador Léxico");
        ventana.setSize(600, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);

        JButton botonCrearAFNBasico = new JButton("Crear AFN básico");
        botonCrearAFNBasico.setBounds(20, 30, 200, 30);
        botonCrearAFNBasico.addActionListener(e -> ventanaAFNBasico());
        ventana.add(botonCrearAFNBasico);

        JButton botonUnirAFNs = new JButton("Unir AFNs");
        botonUnirAFNs.setBounds(20, 80, 200, 30);
        botonUnirAFNs.addActionListener(e -> ventanaUnirAFN());
        ventana.add(botonUnirAFNs);

        JButton botonConcatenarAFNs = new JButton("Concatenar AFNs");
        botonConcatenarAFNs.setBounds(20, 130, 200, 30);
        botonConcatenarAFNs.addActionListener(e -> ventanaConcatenarAFN());
        ventana.add(botonConcatenarAFNs);

        JButton botonCerraduraPositiva = new JButton("Cerradura Positiva");
        botonCerraduraPositiva.setBounds(20, 180, 200, 30);
        botonCerraduraPositiva.addActionListener(e -> ventanaCerraduraPositiva());
        ventana.add(botonCerraduraPositiva);

        JButton botonCerraduraKleen = new JButton("Cerradura de Kleene");
        botonCerraduraKleen.setBounds(20, 230, 200, 30);
        botonCerraduraKleen.addActionListener(e -> ventanaCerraduraKleen());
        ventana.add(botonCerraduraKleen);

        JButton botonAnalizadorLexico = new JButton("Crear Analizador Léxico");
        botonAnalizadorLexico.setBounds(20, 280, 200, 30);
        botonAnalizadorLexico.addActionListener(e -> ventanaAnalizadorLexico());
        ventana.add(botonAnalizadorLexico);

        JButton botonAFNaAFD = new JButton("Convertir AFN a AFD");
        botonAFNaAFD.setBounds(20, 330, 200, 30);
        botonAFNaAFD.addActionListener(e -> ventanaAFNaAFD());
        ventana.add(botonAFNaAFD);

        JButton botonAnalisisLexico = new JButton("Análisis Léxico");
        botonAnalisisLexico.setBounds(20, 380, 200, 30);
        botonAnalisisLexico.addActionListener(e -> ventanaAnalisisLexico());
        ventana.add(botonAnalisisLexico);

        ventana.setVisible(true);
    }

    public static void ventanaAFNBasico() {
        JFrame ventana = new JFrame("Crear AFN básico");
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        JLabel label = new JLabel("Ingrese símbolo inferior:");
        JTextField entradaSimboloInf = new JTextField(5);
        JLabel label2 = new JLabel("Ingrese símbolo superior:");
        JTextField entradaSimboloSup = new JTextField(5);

        JButton botonCrear = new JButton("Crear AFN");
        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char simboloInf = entradaSimboloInf.getText().charAt(0);
                char simboloSup = entradaSimboloSup.getText().charAt(0);
                AFN afn = op.CrearBasico(simboloInf, simboloSup); // Ajusta esta línea para usar el método correcto
                System.out.println("AFN creado con los símbolos: " + simboloInf + " a " + simboloSup);
            }
        });

        ventana.add(label);
        ventana.add(entradaSimboloInf);
        ventana.add(label2);
        ventana.add(entradaSimboloSup);
        ventana.add(botonCrear);
        ventana.setVisible(true);
    }

    public static void ventanaUnirAFN() {
        JFrame ventana = new JFrame("Unir AFNs");
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        

        JLabel label = new JLabel("Unir dos AFNs.");
        ventana.add(label);

        ventana.setVisible(true);
    }

    public static void ventanaConcatenarAFN() {
        JFrame ventana = new JFrame("Concatenar AFNs");
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        JLabel label = new JLabel("Concatenar dos AFNs.");
        ventana.add(label);

        ventana.setVisible(true);
    }

    public static void ventanaCerraduraPositiva() {
        JFrame ventana = new JFrame("Cerradura Positiva");
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        JLabel label = new JLabel("Aplicar cerradura positiva.");
        ventana.add(label);

        ventana.setVisible(true);
    }

    public static void ventanaCerraduraKleen() {
        JFrame ventana = new JFrame("Cerradura de Kleene");
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        JLabel label = new JLabel("Aplicar cerradura de Kleene.");
        ventana.add(label);

        ventana.setVisible(true);
    }

    public static void ventanaAnalizadorLexico() {
        JFrame ventana = new JFrame("Analizador Léxico");
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        JLabel label = new JLabel("Crear un analizador léxico.");
        ventana.add(label);

        ventana.setVisible(true);
    }

    public static void ventanaAFNaAFD() {
        JFrame ventana = new JFrame("Convertir AFN a AFD");
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        JLabel label = new JLabel("Convertir AFN a AFD.");
        ventana.add(label);

        ventana.setVisible(true);
    }

    public static void ventanaAnalisisLexico() {
        JFrame ventana = new JFrame("Análisis Léxico");
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        JLabel label = new JLabel("Realizar análisis léxico.");
        ventana.add(label);

        ventana.setVisible(true);
    }
}
