package com.C2ManuKevin.juego.vista;

import com.C2ManuKevin.juego.controlador.EsperarJugadoresController;
import com.C2ManuKevin.juego.controlador.Juego;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author KevinRg & Manu
 */

public class EsperarJugadores extends javax.swing.JFrame {
    
    private final EsperarJugadoresController controller;

    /**
     * Creates new form EsperarJugadores
     *
     * @param controller
     */
    public EsperarJugadores(EsperarJugadoresController controller) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.controller = controller;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ipLabel = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        copiarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 204));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Juega con amigos compartiendo la siguiente dirección IP");

        ipLabel.setEditable(false);
        ipLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ipLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipLabelActionPerformed(evt);
            }
        });

        startButton.setText("Start Game");
        startButton.setEnabled(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        copiarButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        copiarButton.setText("Copy");
        copiarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarButtonActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(0, 2));
        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(startButton)
                            .addComponent(copiarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(copiarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(ipLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        controller.iniciarJuego();
    }//GEN-LAST:event_startButtonActionPerformed

    private void copiarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarButtonActionPerformed
        controller.copiarAlPortapapeles(ipLabel.getText());
        ipLabel.setText("Copied");
    }//GEN-LAST:event_copiarButtonActionPerformed

    private void ipLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipLabelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton copiarButton;
    private javax.swing.JTextField ipLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

    public void reload() {
        ipLabel.setText(controller.getIP());
        ArrayList<JLabel> jlabels = new ArrayList<>();
        jPanel1.removeAll();
        for (int i = 0; i < controller.getCantidadJugadores(); i++) {
            int j = i + 1;    
            JLabel label1 = new JLabel("   - Player " + j + " : " );
            jPanel1.add(label1);
            JLabel label2 = new JLabel(controller.getJugador(i).getNombre());
            jPanel1.add(label2);
            jlabels.add(label2);
            jPanel1.updateUI();
        }
        
        if (controller.getCantidadJugadores() >= 2) {
            startButton.setEnabled(true);
        }
        if (Juego.getInstance().servidor != null) {
            startButton.setVisible(true);
        } else {
            startButton.setVisible(false);
        }
        repaint();
    }
    
}
