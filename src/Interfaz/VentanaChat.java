package Interfaz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;


/**
 *
 * @author Isaac
 */
public class VentanaChat extends javax.swing.JFrame {
    HTMLEditorKit kit;
    HTMLDocument doc;
    
    
    public VentanaChat() {
        initComponents();
        
        pruebaLista();
        //pruebaAreaMensajes();
        
    }
    
    public void pruebaLista(){
        String[] usuarios = {"Todos","Erick","Ivan","Ferch","Isaac"};
        this.listaDeUsuarios.setListData(usuarios);
    }
    
    public void pruebaAreaMensajes(){
        textPaneMensajes.setText("<html><strong>¡Wasap!</strong></html>");
    }
   
    // initComponents()
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
		kit = new HTMLEditorKit();
        doc = new HTMLDocument();
		
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
		textPaneMensajes.setEditorKit(kit);
        textPaneMensajes.setDocument(doc);
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonEnviarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelEstadoConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelNombreDeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(labelNombreDeUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonEnviarMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelEstadoConexion))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void botonEnviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarMensajeActionPerformed
        try {
            kit.insertHTML(doc, doc.getLength(), "<font color='red'><u>"+textAreaMensaje.getText()+"</u></font>", 0, 0, null);
        } catch (BadLocationException ex) {
            System.out.println("Error: Referencia a una localizacion no existente");
        } catch (IOException ex) {
            System.out.println("Error IO");
        }
        textAreaMensaje.setText("");
    }//GEN-LAST:event_botonEnviarMensajeActionPerformed

       //Al presionar Enter
    private void textAreaMensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textAreaMensajeKeyPressed
        
    }//GEN-LAST:event_textAreaMensajeKeyPressed

  
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaChat().setVisible(true);
            }
        });
    }

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
