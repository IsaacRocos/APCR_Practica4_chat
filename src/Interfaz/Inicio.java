package Interfaz;

import javax.swing.JOptionPane;

/**
 *
 * @author Isaac
 */
public class Inicio extends javax.swing.JFrame implements Runnable {

    String nombreUsuario;
    String grupo;
    int puertoEnvio;
    int puertoEscucha;
    boolean datosListos;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getGrupo() {
        return grupo;
    }

    public int getPuertoEnvio() {
        return puertoEnvio;
    }

    public int getPuertoEscucha() {
        return puertoEscucha;
    }

    public boolean getDatosListos() {
        return datosListos;
    }

    public void setDatosListos(boolean estado) {
        datosListos = estado;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButConectar = new javax.swing.JButton();
        jTFNombreUsuario = new javax.swing.JTextField();
        jTFIPGrupo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFPtoEnvio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFPtoEscucha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conectar al Chat");

        jLabel1.setText("Nombre de Usuario:");

        jLabel2.setText("Conectar a IP de Grupo:");

        jButConectar.setText("Conectar");
        jButConectar.setEnabled(false);
        jButConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButConectarActionPerformed(evt);
            }
        });
//jTFNombreUsuario
        jTFNombreUsuario.setForeground(new java.awt.Color(51, 51, 51));
        jTFNombreUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFDatosKeyPressed(evt);
            }
			public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFDatosKeyPressed(evt);
            }
        });
//jTFIPGrupo
        jTFIPGrupo.setForeground(new java.awt.Color(51, 51, 51));
        jTFIPGrupo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFIPGrupo.setText("230.1.1.1");
        jTFIPGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFDatosKeyPressed(evt);
            }
			public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFDatosKeyPressed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/port.png"))); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Puerto Envio:");
//jTFPtoENVIO
        jTFPtoEnvio.setForeground(new java.awt.Color(51, 51, 51));
        jTFPtoEnvio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFPtoEnvio.setText("4000");
        
        jTFPtoEnvio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFDatosKeyPressed(evt);
            }
			public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFDatosKeyPressed(evt);
            }
        });
		
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Puerto Escucha:");
// jTFPtoEscucha
        jTFPtoEscucha.setForeground(new java.awt.Color(51, 51, 51));
        jTFPtoEscucha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFPtoEscucha.setText("4001");
        jTFPtoEscucha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFDatosKeyPressed(evt);
            }
			public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFDatosKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButConectar, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(jTFNombreUsuario)
                    .addComponent(jTFIPGrupo)
                    .addComponent(jTFPtoEnvio)
                    .addComponent(jTFPtoEscucha))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTFIPGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTFPtoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFPtoEscucha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButConectarActionPerformed
        System.out.println("Verificando datos...");
        try {
            puertoEnvio = Integer.parseInt(jTFPtoEnvio.getText());
            puertoEscucha = Integer.parseInt(jTFPtoEscucha.getText());
            nombreUsuario = jTFNombreUsuario.getText();
            grupo = jTFIPGrupo.getText();
//            setDatosListos(true);
            System.out.println("Datos consistentes");
            synchronized (this) {
                this.notifyAll();
            }

        } catch (NumberFormatException nfe) {
            System.err.println("Datos NO consistentes");
            JOptionPane.showMessageDialog(null, "Algunos de los datos indroducidos son incorrectos.\nVerifiquelos por favor.");
//            setDatosListos(false);
        }
    }//GEN-LAST:event_jButConectarActionPerformed

    /**
     * Verifica que todos los campos de texo no estén vacíos.
     */
    private void jTFDatosKeyPressed(java.awt.event.KeyEvent evt) {
        if (jTFNombreUsuario.getText().equals("") || jTFIPGrupo.getText().equals("") || jTFPtoEnvio.getText().equals("") || jTFPtoEscucha.getText().equals("")) {
            jButConectar.setEnabled(false);
        } else {
            jButConectar.setEnabled(true);
        }
    }
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTFIPGrupo;
    private javax.swing.JTextField jTFNombreUsuario;
    private javax.swing.JTextField jTFPtoEnvio;
    private javax.swing.JTextField jTFPtoEscucha;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        this.setVisible(true);
    }
}
