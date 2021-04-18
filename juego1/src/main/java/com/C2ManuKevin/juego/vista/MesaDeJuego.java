/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.C2ManuKevin.juego.vista;

import com.C2ManuKevin.juego.controlador.MesaDeJuegoController;
import com.C2ManuKevin.juego.modelo.Jugador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author JUAN-PC
 */
public class MesaDeJuego extends javax.swing.JFrame {

//    private Highlighter.HighlightPainter blue = new DefaultHighlighter.DefaultHighlightPainter(Color.blue);
//    private Highlighter.HighlightPainter red = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
//    private Highlighter.HighlightPainter yellow = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
//    private Highlighter.HighlightPainter green = new DefaultHighlighter.DefaultHighlightPainter(Color.green);
    MesaDeJuegoController controller;

    /**
     * Creates new form MesaDeJuego
     *
     * @param controller
     */
    public MesaDeJuego(MesaDeJuegoController controller) {
        initComponents();
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

        Jug1TablLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        manoPanel = new javax.swing.JPanel();
        cartaMesaPanel = new javax.swing.JPanel();
        nuevaCarta = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();
        sayUNO = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 255));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setResizable(false);

        Jug1TablLabel.setText("________");

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane1.setViewportView(jPanel1);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("JUGADORES");

        javax.swing.GroupLayout manoPanelLayout = new javax.swing.GroupLayout(manoPanel);
        manoPanel.setLayout(manoPanelLayout);
        manoPanelLayout.setHorizontalGroup(
            manoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );
        manoPanelLayout.setVerticalGroup(
            manoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(manoPanel);

        nuevaCarta.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        nuevaCarta.setText("Toma una carta");
        nuevaCarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaCartaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cartaMesaPanelLayout = new javax.swing.GroupLayout(cartaMesaPanel);
        cartaMesaPanel.setLayout(cartaMesaPanelLayout);
        cartaMesaPanelLayout.setHorizontalGroup(
            cartaMesaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartaMesaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nuevaCarta, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cartaMesaPanelLayout.setVerticalGroup(
            cartaMesaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartaMesaPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(nuevaCarta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        logArea.setEditable(false);
        logArea.setColumns(20);
        logArea.setRows(5);
        jScrollPane3.setViewportView(logArea);

        sayUNO.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        sayUNO.setText("UNO!");
        sayUNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sayUNOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(Jug1TablLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1)
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cartaMesaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(sayUNO, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(303, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cartaMesaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(sayUNO, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(Jug1TablLabel))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaCartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaCartaActionPerformed
        controller.getNuevaCarta();
        manoPanel.updateUI();
    }//GEN-LAST:event_nuevaCartaActionPerformed
    
    private void sayUNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sayUNOActionPerformed
        controller.decirUNO();
        
    }//GEN-LAST:event_sayUNOActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jug1TablLabel;
    private javax.swing.JPanel cartaMesaPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea logArea;
    private javax.swing.JPanel manoPanel;
    private javax.swing.JButton nuevaCarta;
    private javax.swing.JButton sayUNO;
    // End of variables declaration//GEN-END:variables

    public void showMano(List<JLabel> jTextAreas) {
        manoPanel.setLayout(new FlowLayout());
        manoPanel.removeAll();
        for (JLabel area : jTextAreas) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(area, BorderLayout.CENTER);
            manoPanel.add(panel);
        }
        manoPanel.updateUI();
    }
    
    public void showJugadoresLista(List<JLabel> jLabels) {
        
        jPanel1.removeAll();
        for (JLabel jLabel : jLabels) {
            jPanel1.add(jLabel);
        }
        jPanel1.updateUI();
    }
    
    public void showCarta(JLabel jLabel) {
        cartaMesaPanel.setLayout(new FlowLayout());
        cartaMesaPanel.removeAll();
        cartaMesaPanel.add(jLabel);
        cartaMesaPanel.updateUI();
    }
    
    public void setJugadorNombre(Jugador jugador) {
        Jug1TablLabel.setText(jugador.toString());
    }
    
    public void setButtonEnable(Boolean b) {
        nuevaCarta.setEnabled(b);
        sayUNO.setEnabled(b);
    }
    
    public void reload() {
        manoPanel.updateUI();
        cartaMesaPanel.updateUI();
        jPanel1.updateUI();
        repaint();
    }
    
    public void setLog(String sr) {
        logArea.setText(logArea.getText() + "\n" + sr);
    }
}