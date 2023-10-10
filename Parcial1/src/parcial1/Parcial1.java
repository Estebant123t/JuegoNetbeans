package parcial1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Parcial1 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton explorarButton;
    private JButton comunicarseButton;
    private JTextField claveField;
    private int añosExpedicion = 2;
    private int tiempoRestante = 180; // 3 minutos en segundos
    private JButton disminuirAñosButton;
    private JFrame VentanaSecreta;
    private JTextField nuevaClaveField;
    private JButton pistaButton1;
    private JButton pistaButton2;
    private JButton verificarClaveButton;
    private JButton Rescate;
    private boolean acertijoResuelto = false;

    public Parcial1() {
        frame = new JFrame("La Expedición Espacial Perdida");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Establecer el tamaño de la ventana para que sea igual al tamaño de la pantalla
        frame.setSize(screenSize);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        label = new JLabel("Bienvenido al cuartel espacial.");
        explorarButton = new JButton("Investigar el estado de las naves");
        comunicarseButton = new JButton("Comunicarse");

        Rescate = new JButton("Enviar misión de rescate.");

        // Configurar el botón para que esté inactivo inicialmente
        Rescate.setEnabled(false);
        Mensajeintro();

        panel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                System.out.println("La respuesta al acertijo esta en el primer primo.");
            }
        });

        explorarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadonaves();
            }
        });

        comunicarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicarse();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(explorarButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(comunicarseButton, gbc);

        // Campo de contraseña
        claveField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(claveField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(Rescate, gbc);

        // Botón para disminuir los años de expedición
        disminuirAñosButton = new JButton("Descifrar código");
        disminuirAñosButton.setEnabled(false); // Deshabilitado al inicio

        disminuirAñosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disminuirAños();

                // Verifica si el acertijo ya se ha resuelto antes de crear la nueva ventana
                if (!acertijoResuelto) {
                disminuirAñosButton.setEnabled(false);
                    crearNuevaVentana();
                }
            }
        });
       Rescate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                mostrarMensaje("¡Has coordinado el rescate con éxito!");
                System.exit(0);
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(disminuirAñosButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Temporizador para limitar el tiempo del juego
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                if (tiempoRestante <= 0) {
                    mostrarMensaje("Tiempo agotado. La expedición sigue perdida.");
                    System.exit(0);
                }
            }
        });
        timer.start();

        // Agregar un WindowListener para mostrar una pista al cerrar la ventana
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mostrarMensaje("No puedes cerrar la ventana, aún hay trabajo por hacer.");
                mostrarMensaje("Sin embargo puedes disminuir la ventana, si eso sirve de algo.");
            }
        });

        // Agregar un KeyListener para verificar la contraseña ingresada
        claveField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verificarContraseña();
                }
            }
        });
    
    }
    
    private void Mensajeintro(){
        mostrarMensaje("Antes de ingresar al cuartel, tenemos que recordar las normas que debes seguir.");
        mostrarMensaje("En el cuertel podrás investigar el estado de las naves.");
        mostrarMensaje("Y, por supuesto, en caso de tener algún inconveniente con alguna nave, será necesario comunicarse con la misma.");
    }

    
    private void crearNuevaVentana() {
        VentanaSecreta = new JFrame("Ventana Secreta");
        VentanaSecreta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        VentanaSecreta.setLayout(new BorderLayout());

        JPanel nuevoPanel = new JPanel();
        nuevoPanel.setLayout(new GridBagLayout());

        JLabel nuevoLabel = new JLabel("Nuevo inconveniente, resuelve el acertijo para avanzar.");

        nuevaClaveField = new JTextField(10);
        pistaButton1 = new JButton("Pista 1");
        pistaButton2 = new JButton("Pista 2");
        verificarClaveButton = new JButton("Verificar Clave");

        pistaButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMensaje("Pista 1: La respuesta tiene tres letras.");
            }
        });

        pistaButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMensaje("Pista 2: Siempre van al final.");
            }
        });

        verificarClaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarNuevaContraseña();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        nuevoPanel.add(nuevoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        nuevoPanel.add(nuevaClaveField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        nuevoPanel.add(pistaButton1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        nuevoPanel.add(pistaButton2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        nuevoPanel.add(verificarClaveButton, gbc);

        VentanaSecreta.add(nuevoPanel, BorderLayout.CENTER);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        VentanaSecreta.setSize(screenSize);
        VentanaSecreta.setVisible(true);
    
    }

    
    private void verificarNuevaContraseña() {
        String contraseña = nuevaClaveField.getText();
        if (contraseña.equals("xyz")) { // Respuesta correcta
            mostrarMensaje("¡Respuesta correcta! La busqueda continua.");
            acertijoResuelto = true;
            VentanaSecreta.dispose(); // Cierra la nueva ventana
            disminuirAñosButton.setEnabled(true); // Habilita el botón para disminuir años
        } else {
            mostrarMensaje("Respuesta incorrecta. Inténtalo de nuevo.");
        }
        nuevaClaveField.setText("");
    }

    private void estadonaves() {
        mostrarMensaje("Una nave está dañada. Necesitan reparaciones.");
        mostrarMensaje("Primero se debe averiguar dónde esta la nave.");
    }

    private void comunicarse() {
        if (añosExpedicion > 1) {
            mostrarMensaje("Se ha recibido una señal de socorro encriptada.");
            mostrarMensaje("Pista: La clave es el número de años que la nave lleva en su viaje.");
        } else {
            mostrarMensaje("Se ha descifrado la señal. La expedición está en la galaxia Andrómeda.");
            Rescate.setEnabled(true);
            
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }

    private void disminuirAños() {
        if (añosExpedicion > 1) {
            añosExpedicion--;
            mostrarMensaje("Felicidades, sin embargo, aún no es posible descifrar el código");
        } else {
            mostrarMensaje("Felicidades, ya puedes descubrir donde esta la expedición.");
        }
    }

    private void verificarContraseña() {
        String contraseña = claveField.getText();
        if (contraseña.equals("2")) { 
            mostrarMensaje("Contraseña correcta.");
            mostrarMensaje("Sin embargo aún no es posible saber de donde proviene la señal de socorro.");
            disminuirAñosButton.setEnabled(true); // Habilita el botón después de ingresar la contraseña
        } else {
            mostrarMensaje("Contraseña incorrecta. Inténtalo de nuevo.");
        }
        claveField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Parcial1();
            }
        });
    }
}