/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Adrián
 */
public class Congruencial extends javax.swing.JDialog {

    private static Congruencial instancia = new Congruencial();
    
    /**
     * Creates new form Congruencial
     */
    private Congruencial() {
        
        initComponents();
        
        super.setModal(false);
        
    }
    
    public static Congruencial getInstance(){
    
        return instancia;
    
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
        jLabel2 = new javax.swing.JLabel();
        ta = new javax.swing.JTextField();
        tx = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tm = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tr = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bLimpiar = new javax.swing.JButton();
        bCalcular = new javax.swing.JButton();
        bSig = new javax.swing.JButton();
        vDefecto = new javax.swing.JButton();

        jLabel1.setText("<html><h2>a</h2></html>");

        jLabel2.setText("<html><h1>(</h1></html>");

        ta.setText("16333");
        ta.setToolTipText("");
        ta.setInputVerifier(new InputVerifier(){
            public boolean verify(JComponent tf) {
                String cadena = ((JTextField)tf).getText();
                // Aqui verificamos si cadena es correcta y devolvemos
                try {
                    return true;
                } catch (NumberFormatException nfe){
                    return false;
                }
            }
        });
        ta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taActionPerformed(evt);
            }
        });

        tx.setText("1234567890");
        tx.setInputVerifier(new InputVerifier(){
            public boolean verify(JComponent tf) {
                String cadena = ((JTextField)tf).getText();
                // Aqui verificamos si cadena es correcta y devolvemos
                try {
                    return true;
                } catch (NumberFormatException nfe){
                    return false;
                }
            }
        });

        jLabel3.setText("<html><h1>+</h1></html>");

        tc.setText("25887");
        tc.setInputVerifier(new InputVerifier(){
            public boolean verify(JComponent tf) {
                String cadena = ((JTextField)tf).getText();
                // Aqui verificamos si cadena es correcta y devolvemos
                try {
                    return true;
                } catch (NumberFormatException nfe){
                    return false;
                }
            }
        });

        jLabel4.setText("<html><h1>)</h1></html>");

        jLabel5.setText("<html><h1>=</h1></html>");

        tm.setText("32768");
        tm.setInputVerifier(new InputVerifier(){
            public boolean verify(JComponent tf) {
                String cadena = ((JTextField)tf).getText();
                // Aqui verificamos si cadena es correcta y devolvemos
                try {
                    return true;
                } catch (NumberFormatException nfe){
                    return false;
                }
            }
        });

        jLabel6.setText("<html><h1>%</h1></html>");

        tr.setText("21833");
        tr.setInputVerifier(new InputVerifier(){
            public boolean verify(JComponent tf) {
                String cadena = ((JTextField)tf).getText();
                // Aqui verificamos si cadena es correcta y devolvemos
                try {
                    return true;
                } catch (NumberFormatException nfe){
                    return false;
                }
            }
        });
        tr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trActionPerformed(evt);
            }
        });

        jLabel7.setText("<html><h1>*</h1></html>");

        jLabel8.setText("<html><h2>x<sub>n</sub></h2></html>");

        jLabel9.setText("<html><h2>c</h2></html>");

        jLabel10.setText("<html><h2>M</h2></html>");

        jLabel11.setText("<html><h2>x<sub>n+1</sub></h2></html>");

        bLimpiar.setText("Limpiar");
        bLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpiarActionPerformed(evt);
            }
        });

        bCalcular.setText("Calcular");
        bCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCalcularActionPerformed(evt);
            }
        });

        bSig.setText("Sig Numero");
        bSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSigActionPerformed(evt);
            }
        });

        vDefecto.setText("Valores Defecto");
        vDefecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vDefectoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ta, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(vDefecto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tc, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tm, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tr, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bSig, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bLimpiar)
                    .addComponent(bCalcular)
                    .addComponent(bSig)
                    .addComponent(vDefecto))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vDefectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vDefectoActionPerformed

        ta.setText("16333");
        tc.setText("25887");
        tx.setText("1234567890");
        tm.setText("32768");
        tr.setText("21833");

    }//GEN-LAST:event_vDefectoActionPerformed

    private void taActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taActionPerformed

    private void bLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarActionPerformed

        ta.setText("0");
        tc.setText("0");
        tx.setText("0");
        tm.setText("0");
        tr.setText("0");

    }//GEN-LAST:event_bLimpiarActionPerformed

    private void bCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCalcularActionPerformed

        long res = ((Long.parseLong(ta.getText()) * Long.parseLong(tx.getText())) + Long.parseLong(tc.getText())) % Long.parseLong(tm.getText());
        
        tr.setText(String.valueOf(res));

    }//GEN-LAST:event_bCalcularActionPerformed

    private void bSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSigActionPerformed
        
        tx.setText(tr.getText());
        
        long res = ((Long.parseLong(ta.getText()) * Long.parseLong(tx.getText())) + Long.parseLong(tc.getText())) % Long.parseLong(tm.getText());
        
        tr.setText(String.valueOf(res));
        
    }//GEN-LAST:event_bSigActionPerformed

    private void trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCalcular;
    private javax.swing.JButton bLimpiar;
    private javax.swing.JButton bSig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField ta;
    private javax.swing.JTextField tc;
    private javax.swing.JTextField tm;
    private javax.swing.JTextField tr;
    private javax.swing.JTextField tx;
    private javax.swing.JButton vDefecto;
    // End of variables declaration//GEN-END:variables
}