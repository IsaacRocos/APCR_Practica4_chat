package Interfaz;

import javax.swing.JOptionPane;

/**
 *
 * @author Isaac
 */
public class Inicio extends javax.swing.JFrame implements Runnable {

    String nombreUsuario;
    String grupo;
    int puerto;
    boolean datosListos;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getGrupo() {
        return grupo;
    }

    public int getPuerto() {
        return puerto;
    }

    public boolean getDatosListos() {
        return datosListos;
    }


    public Inicio() {
        datosListos = false;
        initComponents();
    }

    public void mostrarVentana(Inicio ventanaInicio) {
        java.awt.EventQueue.invokeLater(ventanaInicio);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFNombreUsuario = new javax.swing.JTextField();
        jTFIPGrupo = new javax.swing.JTextField();
        jTFPuerto = new javax.swing.JTextField();
        jButConectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/port.png"))); // NOI18N

        jLabel1.setText("Nombre de Usuario:");

        jLabel2.setText("Conectar a IP de Grupo:");

        jLabel4.setText("Puerto:");

        jTFNombreUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFDatosKeyReleased(evt);
            }
        });

        jTFIPGrupo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFIPGrupo.setText("230.1.1.1");
        jTFIPGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFDatosKeyReleased(evt);
            }
        });

        jTFPuerto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFPuerto.setText("4000");
        jTFPuerto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFDatosKeyReleased(evt);
            }
        });

        jButConectar.setText("Conectar");
        jButConectar.setEnabled(false);
        jButConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButConectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(31, 31, 31)
                                .addComponent(jTFNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jButConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTFPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(31, 31, 31)
                        .addComponent(jTFIPGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTFIPGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(29, 29, 29)
                        .addComponent(jButConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButConectarActionPerformed
        System.out.println("Verificando datos...");
        try {
            puerto = Integer.parseInt(jTFPuerto.getText());
            nombreUsuario = jTFNombreUsuario.getText();
            grupo = jTFIPGrupo.getText();
            System.out.println("Datos consistentes");
            synchronized (this) {
                this.notifyAll();
            }

        } catch (NumberFormatException nfe) {
            System.err.println("Datos NO consistentes");
            JOptionPane.showMessageDialog(null, "Algunos de los datos indroducidos son incorrectos.\nVerifiquelos por favor.");
        }
    }//GEN-LAST:event_jButConectarActionPerformed

    private void jTFDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFDatosKeyReleased
        if (jTFNombreUsuario.getText().equals("") || jTFIPGrupo.getText().equals("") || jTFPuerto.getText().equals("") ) {
            jButConectar.setEnabled(false);
        } else {
            jButConectar.setEnabled(true);
        }
    }//GEN-LAST:event_jTFDatosKeyReleased

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Inicio().setVisible(true);
//            }
//        });
//    }   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButConectar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTFIPGrupo;
    private javax.swing.JTextField jTFNombreUsuario;
    private javax.swing.JTextField jTFPuerto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        this.setVisible(true);
    }
}
