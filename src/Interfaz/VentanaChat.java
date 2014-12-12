package Interfaz;

import DTO.Mensaje;
import Servicio.Servicio;
import Utileria.Chat;
import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author Isaac
 */
public class VentanaChat extends JFrame implements Runnable, Chat {

    Servicio servicio;
    HTMLEditorKit kit;
    HTMLDocument doc;
    String nombreUsuario;
    String grupo;
    int puerto;

    public VentanaChat() {
        System.out.println("InitCoponents...");
        initComponents();
        pruebaLista(); // prueba el despliegue de nombres de usuarios conectados.
        // En este caso, se debería hacer la llamada a el método 
        // que seencarga de verificar qué usuarios están conectados.
    }

    private VentanaChat(Inicio ventanaInicio) {
        this();
        nombreUsuario = ventanaInicio.getNombreUsuario();
        grupo = ventanaInicio.getGrupo();
        puerto = ventanaInicio.getPuerto();

        labelNombreDeUsuario.setText(nombreUsuario);
        
    }

    private void pruebaLista() {
        String[] usuarios = {"Todos", "Erick", "Ivan", "Ferch", "Isaac"};
        this.listaDeUsuarios.setListData(usuarios);
    }

    /**
     * Muestra en la ventana de chat, los mensajes ya formateados de los
     * usuarios.
     *
     * @param mensaje
     */
    private void mostrarMensajeVentana(String mensaje) {
        //Probando mensajes con formato en JTextPane
        try {
            //kit.insertHTML(doc, doc.getLength(), "<font color='red'><u>" + textAreaMensaje.getText() + "</u></font>", 0, 0, null);
            kit.insertHTML(doc, doc.getLength(), mensaje, 0, 0, null);
        } catch (BadLocationException ex) {
            System.out.println("Error: Referencia a una localizacion no existente");
        } catch (IOException ex) {
            System.out.println("Error IO");
        }
        textAreaMensaje.setText("");
    }

    /**
     * Verifica si se presina el boton "botonEnviar".En caso de que así sea,
     * llama a los métodos mostrarMensajeVentana() y ...
     *
     * @param evt Contiene la información del evento ocurrido en el botón
     * "botonEnviar".
     */
    private void botonEnviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {
        String mensaje = textAreaMensaje.getText();
        // mensaje = metodo formato mensaje
        mostrarMensajeVentana(mensaje);
    }

    /**
     * Verifica si se presina la tecla [Enter]. En caso de que así sea, llama a
     * los métodos mostrarMensajeVentana() y ...
     *
     * @param evt Es el evento que tiene la información de la tecla que presiona
     * el usuario en textAreaMensaje.
     */
    private void textAreaMensajeKeyPressed(java.awt.event.KeyEvent evt) {
        int codigoTecla = evt.getKeyCode();
        if (codigoTecla == 10) {
            String mensaje = textAreaMensaje.getText();
            // mensaje = metodo formato mensaje
            mostrarMensajeVentana(mensaje);

        }
    }

    private boolean conectaServicio() {
        boolean estadoConexion;
        System.out.println("Intentando conectar al grupo:" + grupo);
        servicio = new Servicio(grupo, puerto, (Chat)this);
        try {
            servicio.unirAlGrupo();
            estadoConexion = true;
            labelEstadoConexion.setForeground(Color.GREEN);
            labelEstadoConexion.setText("Conectado a: " + grupo);
        } catch (IOException e) {
            estadoConexion = false;
            System.err.println("Ocurri\u00f3 un error al intencat conectar al servidor");
            JOptionPane.showMessageDialog(null, "Ocurri\u00f3 un error al unirse al grupo.\nPor favor, intentelo de nuevo.", "Error", ERROR);
        }
        return estadoConexion;
    }

    @Override
    public void procesarMensaje(Mensaje mensaje) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        System.out.println("Mostrando ventana de Chat...");
        this.setVisible(true);
    }

    /**
     * Método main
     * @param args
     */
    public static void main(String args[]) {
        VentanaChat ventanaChat;
        Inicio ventanaInicio = new Inicio();
        ventanaInicio.mostrarVentana(ventanaInicio);
        synchronized (ventanaInicio) {
            try {
                ventanaInicio.wait();
            } catch (InterruptedException ex) {
                System.out.println("InterrumptedException");
            }
        }
        
        ventanaChat = new VentanaChat(ventanaInicio);

        if (ventanaChat.conectaServicio()) {
            java.awt.EventQueue.invokeLater(ventanaChat);
        }
    }

    /**
     * initComponents Inicializa todos los componentes de la ventana de chat.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonEnviarMensaje = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPaneMensajes = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaDeUsuarios = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        labelEstadoConexion = new javax.swing.JLabel();
        labelNombreDeUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaMensaje = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        botonEnviarMensaje.setText("Enviar");
        botonEnviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarMensajeActionPerformed(evt);
            }
        });

        textPaneMensajes.setEditable(false);
        textPaneMensajes.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(textPaneMensajes);

        listaDeUsuarios.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        listaDeUsuarios.setToolTipText("Selecciona a los destinatarios");
        listaDeUsuarios.setSelectionBackground(new java.awt.Color(0, 204, 102));
        jScrollPane2.setViewportView(listaDeUsuarios);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel1.setText("Estado de conexión:");

        labelEstadoConexion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        labelEstadoConexion.setText("Desconectado");

        labelNombreDeUsuario.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        labelNombreDeUsuario.setText("Usuario...");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel3.setText("Usuarios activos:");

        textAreaMensaje.setColumns(20);
        textAreaMensaje.setRows(5);
        textAreaMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textAreaMensajeKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(textAreaMensaje);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(botonEnviarMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombreDeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelEstadoConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelNombreDeUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonEnviarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelEstadoConexion))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEnviarMensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelEstadoConexion;
    private javax.swing.JLabel labelNombreDeUsuario;
    private javax.swing.JList listaDeUsuarios;
    private javax.swing.JTextArea textAreaMensaje;
    private javax.swing.JTextPane textPaneMensajes;
    // End of variables declaration//GEN-END:variables
}
