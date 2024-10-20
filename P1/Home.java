import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Home {
    private static Operaciones op = new Operaciones(); 
    private static ArrayList<AFN> afns = new ArrayList<>();
    public static void main(String[] args) {
        ventanaMain();
        
    }


    public static void ventanaMain() {
        JFrame ventana = new JFrame("Analizador Léxico");
        ventana.setSize(600, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);

        JButton botonCrearAFNBasico = new JButton("Crear AFN básico");
        botonCrearAFNBasico.setBounds(200, 30, 200, 30);
        botonCrearAFNBasico.addActionListener(e -> ventanaAFNBasico());
        ventana.add(botonCrearAFNBasico);

        JButton botonUnirAFNs = new JButton("Unir AFNs");
        botonUnirAFNs.setBounds(200, 80, 200, 30);
        botonUnirAFNs.addActionListener(e -> ventanaUnirAFN());
        ventana.add(botonUnirAFNs);

        JButton botonConcatenarAFNs = new JButton("Concatenar AFNs");
        botonConcatenarAFNs.setBounds(200, 130, 200, 30);
        botonConcatenarAFNs.addActionListener(e -> ventanaConcatenarAFN());
        ventana.add(botonConcatenarAFNs);

        JButton botonCerraduraPositiva = new JButton("Cerradura Positiva");
        botonCerraduraPositiva.setBounds(200, 180, 200, 30);
        botonCerraduraPositiva.addActionListener(e -> ventanaCerraduraPositiva());
        ventana.add(botonCerraduraPositiva);

        JButton botonCerraduraKleen = new JButton("Cerradura de Kleene");
        botonCerraduraKleen.setBounds(200, 230, 200, 30);
        botonCerraduraKleen.addActionListener(e -> ventanaCerraduraKleen());
        ventana.add(botonCerraduraKleen);

        JButton botonAnalizadorLexico = new JButton("Crear Analizador Léxico");
        botonAnalizadorLexico.setBounds(200, 280, 200, 30);
        botonAnalizadorLexico.addActionListener(e -> ventanaAnalizadorLexico());
        ventana.add(botonAnalizadorLexico);

        JButton botonAFNaAFD = new JButton("Convertir AFN a AFD");
        botonAFNaAFD.setBounds(200, 330, 200, 30);
        botonAFNaAFD.addActionListener(e -> ventanaAFNaAFD());
        ventana.add(botonAFNaAFD);

        JButton botonAnalisisLexico = new JButton("Análisis Léxico");
        botonAnalisisLexico.setBounds(200, 380, 200, 30);
        botonAnalisisLexico.addActionListener(e -> ventanaAnalisisLexico());
        ventana.add(botonAnalisisLexico);

        ventana.setVisible(true);
    }

    public static void ventanaAFNBasico() {
        JFrame ventana = new JFrame("Crear AFN básico");
        ventana.setSize(450, 450);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(null);  // Diseño absoluto
    
        JLabel label = new JLabel("Ingrese símbolo inferior:");
        label.setBounds(50, 50, 150, 30);
        JTextField entradaSimboloInf = new JTextField(10);
        entradaSimboloInf.setBounds(220, 50, 150, 30);
    
        JLabel label2 = new JLabel("Ingrese símbolo superior:");
        label2.setBounds(50, 100, 150, 30);
        JTextField entradaSimboloSup = new JTextField(10);
        entradaSimboloSup.setBounds(220, 100, 150, 30);
    
        JButton botonCrear = new JButton("Crear AFN");
        botonCrear.setBounds(150, 200, 100, 30);
        botonCrear.addActionListener(e -> {
            String simboloInfText = entradaSimboloInf.getText();
            String simboloSupText = entradaSimboloSup.getText();
            
            // Validar si los campos están vacíos o tienen más de un carácter
            if (simboloInfText.length() != 1 || simboloSupText.length() != 1) {
                JOptionPane.showMessageDialog(ventana, "Por favor ingrese un único carácter en ambos campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;  // Salir del listener si hay un error
            }
        
            char simboloInf = simboloInfText.charAt(0);
            char simboloSup = simboloSupText.charAt(0);
            
            // Crear AFN básico
            AFN afn = op.CrearBasico(simboloInf, simboloSup);
            afns.add(afn);
        
            // Mostrar mensajes de éxito
            JOptionPane.showMessageDialog(ventana, "AFN creado con los símbolos: " + simboloInf + " a " + simboloSup);
        });
        
    
        ventana.add(label);
        ventana.add(entradaSimboloInf);
        ventana.add(label2);
        ventana.add(entradaSimboloSup);
        ventana.add(botonCrear);
    
        ventana.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        ventana.setVisible(true);
    }
    

    public static void ventanaUnirAFN() {
        JFrame ventana = new JFrame("Unir AFNs");
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(null);  // Diseño absoluto
    
        // Etiqueta para instrucciones
        JLabel label = new JLabel("Seleccione dos AFNs para unir:");
        label.setBounds(50, 20, 200, 30);  // Ajusta la posición y el tamaño
        ventana.add(label);
    
        // JComboBox para seleccionar el primer AFN
        JComboBox<String> comboBoxAFN1 = new JComboBox<>();
        comboBoxAFN1.addItem("Seleccione un AFN"); // Opción por defecto
        actualizarComboBoxAFNs(comboBoxAFN1);
        comboBoxAFN1.setBounds(50, 70, 200, 30);  // Ajusta la posición y el tamaño
        ventana.add(comboBoxAFN1);
    
        // JComboBox para seleccionar el segundo AFN
        JComboBox<String> comboBoxAFN2 = new JComboBox<>();
        comboBoxAFN2.addItem("Seleccione un AFN"); // Opción por defecto
        actualizarComboBoxAFNs(comboBoxAFN2);
        comboBoxAFN2.setBounds(50, 120, 200, 30);  // Ajusta la posición y el tamaño
        ventana.add(comboBoxAFN2);
    
        // Botón para unir los AFNs
        JButton botonUnir = new JButton("Unir AFNs");
        botonUnir.setBounds(100, 180, 100, 30);  // Ajusta la posición y el tamaño
        botonUnir.addActionListener(e -> {
            int index1 = comboBoxAFN1.getSelectedIndex() - 1; // Restar 1 para obtener el índice real
            int index2 = comboBoxAFN2.getSelectedIndex() - 1; // Restar 1 para obtener el índice real
    
            // Verificar si ambos índices son válidos
            if (index1 >= 0 && index2 >= 0) {
                AFN afn1 = afns.get(index1);
                AFN afn2 = afns.get(index2);
                AFN afnUnido = op.Unir(afn1, afn2);
                afns.add(afnUnido); 
    
                // Eliminar los AFNs originales
                if (index1 > index2) {
                    afns.remove(index1);
                    afns.remove(index2);
                } else if(index1 < index2) {
                    afns.remove(index2);
                    afns.remove(index1);
                } else if(index1 ==index2){
                    afns.remove(index1);
                }
    
                JOptionPane.showMessageDialog(ventana, "AFNs unidos correctamente.");
                actualizarComboBoxAFNs(comboBoxAFN1);
                actualizarComboBoxAFNs(comboBoxAFN2);
            } else {
                JOptionPane.showMessageDialog(ventana, "Por favor, seleccione ambos AFNs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        ventana.add(botonUnir);
    
        ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        ventana.setVisible(true);
    }
    
    
    // Método para actualizar el JComboBox con los AFNs disponibles
    public static void actualizarComboBoxAFNs(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        comboBox.addItem("Seleccione un AFN"); // Opción por defecto
        for (int i = 0; i < afns.size(); i++) {
            comboBox.addItem("AFN ID: " + i); // Mostrar el ID en el ComboBox
        }
    }
    public static void ventanaConcatenarAFN() {
    JFrame ventana = new JFrame("Concatenar AFNs");
    ventana.setSize(300, 300);
    ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventana.setLayout(null);  // Diseño absoluto

    // Etiqueta para instrucciones
    JLabel label = new JLabel("Seleccione dos AFNs para concatenarlos:");
    label.setBounds(20, 20, 250, 30);  // Ajusta la posición y el tamaño
    ventana.add(label);

    // JComboBox para seleccionar el primer AFN
    JComboBox<String> comboBoxAFN1 = new JComboBox<>();
    comboBoxAFN1.addItem("Seleccione un AFN"); // Opción por defecto
    actualizarComboBoxAFNs(comboBoxAFN1);
    comboBoxAFN1.setBounds(50, 70, 200, 30);  // Ajusta la posición y el tamaño
    ventana.add(comboBoxAFN1);

    // JComboBox para seleccionar el segundo AFN
    JComboBox<String> comboBoxAFN2 = new JComboBox<>();
    comboBoxAFN2.addItem("Seleccione un AFN"); // Opción por defecto
    actualizarComboBoxAFNs(comboBoxAFN2);
    comboBoxAFN2.setBounds(50, 120, 200, 30);  // Ajusta la posición y el tamaño
    ventana.add(comboBoxAFN2);

    // Botón para concatenar los AFNs
    JButton botonConcatenar = new JButton("Concatenar AFNs");
    botonConcatenar.setBounds(80, 180, 140, 30);  // Ajusta la posición y el tamaño
    botonConcatenar.addActionListener(e -> {
        int index1 = comboBoxAFN1.getSelectedIndex() - 1; // Restar 1 para obtener el índice real
        int index2 = comboBoxAFN2.getSelectedIndex() - 1; // Restar 1 para obtener el índice real

        // Verificar si ambos índices son válidos
        if (index1 >= 0 && index2 >= 0) {
            AFN afn1 = afns.get(index1);
            AFN afn2 = afns.get(index2);
            AFN afnConcatenado = op.Concatenar(afn1, afn2);
            afns.add(afnConcatenado); 

            // Eliminar los AFNs originales
            if (index1 > index2) {
                afns.remove(index1);
                afns.remove(index2);
            } else if(index1 < index2) {
                afns.remove(index2);
                afns.remove(index1);
            } else if(index1 == index2){
                afns.remove(index1);
            }

            JOptionPane.showMessageDialog(ventana, "AFNs concatenados correctamente.");
            actualizarComboBoxAFNs(comboBoxAFN1);
            actualizarComboBoxAFNs(comboBoxAFN2);
        } else {
            JOptionPane.showMessageDialog(ventana, "Por favor, seleccione ambos AFNs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });
    ventana.add(botonConcatenar);

    ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    ventana.setVisible(true);
    }


    public static void ventanaCerraduraPositiva() {
        JFrame ventana = new JFrame("Aplicar cerradura positiva a un AFN");
        ventana.setSize(400, 350); // Aumentar el tamaño de la ventana
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(null);  // Diseño absoluto
    
        // Etiqueta para instrucciones
        JLabel label = new JLabel("Seleccione el AFN para aplicarle la cerradura positiva:");
        label.setBounds(20, 20, 360, 30);  // Ajusta la posición y el tamaño
        ventana.add(label);
    
        // JComboBox para seleccionar el AFN
        JComboBox<String> comboBoxAFN1 = new JComboBox<>();
        comboBoxAFN1.addItem("Seleccione un AFN"); // Opción por defecto
        actualizarComboBoxAFNs(comboBoxAFN1);
        comboBoxAFN1.setBounds(50, 70, 300, 30);  // Ajusta la posición y el tamaño
        ventana.add(comboBoxAFN1);
    
        // Botón para aplicar la cerradura positiva
        JButton botonCerraduraPos = new JButton("Cerradura Positiva");
        botonCerraduraPos.setBounds(130, 120, 150, 30);  // Ajusta la posición y el tamaño
        botonCerraduraPos.addActionListener(e -> {
            int index1 = comboBoxAFN1.getSelectedIndex() - 1; // Restar 1 para obtener el índice real
            // Verificar si el índice es válido
            if (index1 >= 0) {
                AFN afn1 = afns.get(index1);
                AFN afnCerraduraPos = op.CerraduraPos(afn1);
                afns.add(afnCerraduraPos);
                afns.remove(index1);
                JOptionPane.showMessageDialog(ventana, "AFN creado con Cerradura Positiva.");
                actualizarComboBoxAFNs(comboBoxAFN1);
            } else {
                JOptionPane.showMessageDialog(ventana, "Por favor, seleccione un AFN.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        ventana.add(botonCerraduraPos);
    
        ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        ventana.setVisible(true);
    }
    

    public static void ventanaCerraduraKleen() {
        JFrame ventana = new JFrame("Aplicar cerradura de Kleene a un AFN");
        ventana.setSize(400, 350); // Aumentar el tamaño de la ventana
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(null);  // Diseño absoluto
    
        // Etiqueta para instrucciones
        JLabel label = new JLabel("Seleccione el AFN para aplicarle la cerradura de Kleene:");
        label.setBounds(20, 20, 360, 30); // Ajusta la posición y el tamaño
        ventana.add(label);
    
        // JComboBox para seleccionar el AFN
        JComboBox<String> comboBoxAFN1 = new JComboBox<>();
        comboBoxAFN1.addItem("Seleccione un AFN"); // Opción por defecto
        actualizarComboBoxAFNs(comboBoxAFN1);
        comboBoxAFN1.setBounds(50, 70, 300, 30); // Ajusta la posición y el tamaño
        ventana.add(comboBoxAFN1);
    
        // Botón para aplicar la cerradura de Kleene
        JButton botonCerraduraKleen = new JButton("Cerradura de Kleene a un AFN");
        botonCerraduraKleen.setBounds(100, 120, 200, 30); // Ajusta la posición y el tamaño
        botonCerraduraKleen.addActionListener(e -> {
            int index1 = comboBoxAFN1.getSelectedIndex() - 1; // Restar 1 para obtener el índice real
            // Verificar si el índice es válido
            if (index1 >= 0) {
                AFN afn1 = afns.get(index1);
                AFN afnCerraduraKleen = op.CerraduraKleene(afn1);
                afns.add(afnCerraduraKleen);
                afns.remove(index1);
                JOptionPane.showMessageDialog(ventana, "AFN creado con Cerradura de Kleene.");
                actualizarComboBoxAFNs(comboBoxAFN1);
            } else {
                JOptionPane.showMessageDialog(ventana, "Por favor, seleccione un AFN.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        ventana.add(botonCerraduraKleen);
    
        ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
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