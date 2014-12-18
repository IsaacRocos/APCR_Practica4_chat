package Interfaz;

import DTO.Mensaje;
import DTO.Usuario;
import Servicio.Servicio;
import Utileria.Chat;
import Utileria.Utileria;
import java.awt.Color;
import static java.awt.image.ImageObserver.ERROR;
import javax.swing.JFrame;
import java.io.IOException;
import java.util.ArrayList;
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
    HTMLEditorKit kit = new HTMLEditorKit();
    HTMLDocument doc = new HTMLDocument();
    String nombreUsuario;
    String grupo;
    int puerto;
    Usuario usuario;
    Usuario destinatario;
    ArrayList<Usuario> listaDeUsuarios;

    public VentanaChat() {
        System.out.println("InitCoponents...");
        initComponents();
    }

    private VentanaChat(Inicio ventanaInicio) {
        this();
        nombreUsuario = ventanaInicio.getNombreUsuario();
        grupo = ventanaInicio.getGrupo();
        puerto = ventanaInicio.getPuerto();
        labelNombreDeUsuario.setText(nombreUsuario);
        this.usuario = new Usuario(nombreUsuario, grupo);
        listaDeUsuarios = new ArrayList<Usuario>();
        listaDeUsuarios.add(new Usuario("Todos", grupo));
    }

    private void actualizaListaUsuarios() {
        //Object[] usuarios = {new Usuario("Ibeth", grupo), new Usuario("Erick", grupo), new Usuario("Ivan", grupo), new Usuario("Ferch", grupo), new Usuario("Isaac", grupo)};
        Object[] usuarios = listaDeUsuarios.toArray();
        this.jListUsuarios.setListData(usuarios);
        this.jListUsuarios.setSelectedIndex(0);
    }

    /**
     * Muestra en la ventana de chat, los mensajes ya formateados de los
     * usuarios.
     *
     * @param mensaje
     */
    private void mostrarMensajeVentana(Mensaje mensaje) {

        String mensajeForm = Utileria.formatearMensaje(mensaje,usuario);
        System.out.println(mensajeForm);
        try {
            kit.insertHTML(doc, doc.getLength(), mensajeForm, 0, 0, null);
        } catch (BadLocationException ex) {
            System.err.println("Error: Referencia a una localizacion no existente");
        } catch (IOException ex) {
            System.err.println("Error IO");
        }
        textAreaMensaje.setText("");
    }

    public void prepararMensaje() {
        String mensajeVentana = textAreaMensaje.getText();
        Mensaje mensaje;
        if ((fijarDestinatario()) == 0) { // seleccionado mensaje a todos.
            mensaje = new Mensaje(1, mensajeVentana, usuario, null);
        } else { // seleccionado unusuario (mensaje privado)
            mensaje = new Mensaje(2, mensajeVentana, usuario, destinatario);
        }
        if (enviarMensaje(mensaje)) { // si consigue enviar elmensaje
            mostrarMensajeVentana(mensaje);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo enviar su mensaje,\nintentelo de nuevo por favor.", "Error", ERROR);
        }
    }

    /**
     * Verifica si se presina el boton "botonEnviar".En caso de que así sea,
     * llama a los métodos mostrarMensajeVentana() y ...
     *
     * @param evt Contiene la información del evento ocurrido en el botón
     * "botonEnviar".
     */
    private void botonEnviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {
        prepararMensaje();
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
            prepararMensaje();
        }
    }

    public int fijarDestinatario() {
        int indice = jListUsuarios.getSelectedIndex();
        this.destinatario = listaDeUsuarios.get(indice);
        return indice;
    }

    private boolean iniciaServicio() {
        boolean estadoConexion;
        System.out.println("Intentando conectar al grupo:" + grupo);
        servicio = new Servicio(grupo, puerto, (Chat) this);
        try {
            servicio.unirAlGrupo();
            servicio.start();
            estadoConexion = true;

            labelEstadoConexion.setForeground(Color.GREEN);
            labelEstadoConexion.setText("Conectado a: " + grupo);

            enviarMensaje(new Mensaje(0, "", usuario, null)); // mensaje login
            actualizaListaUsuarios();
        } catch (IOException e) {
            estadoConexion = false;
            System.err.println("Ocurri\u00f3 un error al intentar conectar al servidor");
            JOptionPane.showMessageDialog(null, "Ocurri\u00f3 un error al unirse al grupo.\nPor favor, intentelo de nuevo.", "Error", ERROR);
        }
        return estadoConexion;
    }

    public boolean enviarMensaje(Mensaje mensaje) {
        try {
            servicio.enviarMensaje(mensaje);
            return true;
        } catch (IOException ioe) {
            System.err.println("Error al enviar el mensaje");
            return false;
        }
    }

    @Override
    public void procesarMensaje(Mensaje mensaje) {
        int tipoMensaje = mensaje.getTipoMensaje();
        System.out.println("Procesando mensaje Tipo: " + tipoMensaje);
        switch (tipoMensaje) {
            case 0: // LogIn
                if (!mensaje.getRemitente().equals(this.usuario)) {
                    mostrarMensajeVentana(mensaje);
                    Usuario nuevoUConect = mensaje.getRemitente();
                    enviarMensaje(new Mensaje(5, "", this.usuario, nuevoUConect)); // envia notificación de conexión.
                    if (!listaDeUsuarios.contains(nuevoUConect)) {
                        listaDeUsuarios.add(nuevoUConect);
                    }
                    actualizaListaUsuarios();
                }
                break;
            case 1: // Msj para todos
                if (!mensaje.getRemitente().equals(this.usuario)) {
                    mostrarMensajeVentana(mensaje);
                }
                break;
            case 2: // Msj Privado
                if (mensaje.getDestinatario().equals(this.usuario)) {
                    mostrarMensajeVentana(mensaje);
                }
                break;
            case 3: // Msj LogOut
                mostrarMensajeVentana(mensaje);
                Usuario UDesConect = mensaje.getRemitente();
                listaDeUsuarios.remove(UDesConect);
                actualizaListaUsuarios();
                break;
            case 4: // Error al leer msj
                mostrarMensajeVentana(mensaje);
                break;
            case 5: //Actualización de conexión
                if (!mensaje.getRemitente().equals(this.usuario)) {
                    Usuario UConect = mensaje.getRemitente();
                    if (!listaDeUsuarios.contains(UConect)) {
                        listaDeUsuarios.add(UConect);
                    }
                    actualizaListaUsuarios();
                }
                break;
        }
    }

    @Override
    public void run() {
        System.out.println("Mostrando ventana de Chat...");
        this.setVisible(true);
    }

    /**
     * Método main
     *
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
        if (ventanaChat.iniciaServicio()) {
            java.awt.EventQueue.invokeLater(ventanaChat);
            ventanaInicio.dispose();
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
        jListUsuarios = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        labelEstadoConexion = new javax.swing.JLabel();
        labelNombreDeUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaMensaje = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("<<Chat en Grupo>>");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        botonEnviarMensaje.setText("Enviar");
        botonEnviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarMensajeActionPerformed(evt);
            }
        });

        textPaneMensajes.setEditable(false);
        textPaneMensajes.setContentType("text/html"); // NOI18N
		textPaneMensajes.setEditorKit(kit);
        textPaneMensajes.setDocument(doc);
        
        jScrollPane1.setViewportView(textPaneMensajes);

        jListUsuarios.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jListUsuarios.setToolTipText("Selecciona a los destinatarios");
        jListUsuarios.setSelectionBackground(new java.awt.Color(0, 204, 102));
        jScrollPane2.setViewportView(jListUsuarios);

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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        enviarMensaje(new Mensaje(3, "Fin de sesion", usuario, null));
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        enviarMensaje(new Mensaje(3, "Fin de sesion", usuario, null));
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEnviarMensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jListUsuarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelEstadoConexion;
    private javax.swing.JLabel labelNombreDeUsuario;
    private javax.swing.JTextArea textAreaMensaje;
    private javax.swing.JTextPane textPaneMensajes;
    // End of variables declaration//GEN-END:variables
}
