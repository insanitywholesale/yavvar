/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg4;

import java.util.Hashtable;
import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
    }
    Hashtable table = new Hashtable();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        LBass = new javax.swing.JLabel();
        LMidrange = new javax.swing.JLabel();
        LTremble = new javax.swing.JLabel();
        LBalance = new javax.swing.JLabel();
        LVolume = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        SlBass = new javax.swing.JSlider();
        SlMidrange = new javax.swing.JSlider();
        SlTremble = new javax.swing.JSlider();
        SlBalance = new javax.swing.JSlider();
        SlVolume = new javax.swing.JSlider();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        BStore = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        LBass.setText("Bass");
        getContentPane().add(LBass, new java.awt.GridBagConstraints());

        LMidrange.setText("Midrange");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(LMidrange, gridBagConstraints);

        LTremble.setText("Tremble");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(LTremble, gridBagConstraints);

        LBalance.setText("Balance");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        getContentPane().add(LBalance, gridBagConstraints);

        LVolume.setText("Volume");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(LVolume, gridBagConstraints);

        info.setText("Info");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        getContentPane().add(info, gridBagConstraints);

        SlBass.setMajorTickSpacing(2);
        SlBass.setMaximum(10);
        SlBass.setMinimum(-10);
        SlBass.setMinorTickSpacing(1);
        SlBass.setPaintLabels(true);
        SlBass.setPaintTicks(true);
        SlBass.setSnapToTicks(true);
        SlBass.setValue(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(SlBass, gridBagConstraints);

        SlMidrange.setMajorTickSpacing(2);
        SlMidrange.setMaximum(10);
        SlMidrange.setMinimum(-10);
        SlMidrange.setMinorTickSpacing(1);
        SlMidrange.setPaintLabels(true);
        SlMidrange.setPaintTicks(true);
        SlMidrange.setSnapToTicks(true);
        SlMidrange.setValue(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(SlMidrange, gridBagConstraints);

        SlTremble.setMajorTickSpacing(2);
        SlTremble.setMaximum(10);
        SlTremble.setMinimum(-10);
        SlTremble.setMinorTickSpacing(1);
        SlTremble.setPaintLabels(true);
        SlTremble.setPaintTicks(true);
        SlTremble.setValue(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(SlTremble, gridBagConstraints);

        table.put(new Integer(0), new JLabel("Center"));
        table.put(new Integer(-5), new JLabel("L"));
        table.put(new Integer(5), new JLabel("R"));
        SlBalance.setLabelTable(table);
        SlBalance.setMajorTickSpacing(2);
        SlBalance.setMaximum(5);
        SlBalance.setMinimum(-5);
        SlBalance.setMinorTickSpacing(1);
        SlBalance.setPaintLabels(true);
        SlBalance.setPaintTicks(true);
        SlBalance.setValue(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(SlBalance, gridBagConstraints);

        SlVolume.setMajorTickSpacing(1);
        SlVolume.setMaximum(10);
        SlVolume.setPaintLabels(true);
        SlVolume.setPaintTicks(true);
        SlVolume.setValue(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(SlVolume, gridBagConstraints);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Default");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        getContentPane().add(jRadioButton1, gridBagConstraints);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Preset1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        getContentPane().add(jRadioButton2, gridBagConstraints);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Preset2");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        getContentPane().add(jRadioButton3, gridBagConstraints);

        BStore.setText("Save");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        getContentPane().add(BStore, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BStore;
    private javax.swing.JLabel LBalance;
    private javax.swing.JLabel LBass;
    private javax.swing.JLabel LMidrange;
    private javax.swing.JLabel LTremble;
    private javax.swing.JLabel LVolume;
    private javax.swing.JSlider SlBalance;
    private javax.swing.JSlider SlBass;
    private javax.swing.JSlider SlMidrange;
    private javax.swing.JSlider SlTremble;
    private javax.swing.JSlider SlVolume;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel info;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    // End of variables declaration//GEN-END:variables
}
