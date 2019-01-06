package learn;

import java.awt.*;
import javax.swing.*;

public class erg1GUI extends javax.swing.JFrame {
     
    public erg1GUI() {
        initComponents();
    }
     
    private void initComponents() {
        textInputField = new javax.swing.JTextField();
        inputLabel = new javax.swing.JLabel();
        convertButton = new javax.swing.JButton();
        outputLabel = new javax.swing.JLabel();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("erg1 but with a GUI");
	setLocationRelativeTo(null);
	setSize(800,600);
 
        inputLabel.setText("Input an integer");
 
        convertButton.setText("Do the thing");
        convertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });
 
        outputLabel.setText("Output");
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(convertButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputLabel)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
 
        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {convertButton, textInputField});
 
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(convertButton)
                    .addComponent(outputLabel))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
    }
 
    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {
	int num = 0;
        int sum = 0;
        int count = 0;
	String text = textInputField.getText();
	int w = Integer.parseInt(text);
	int wCount = 0;

	while (wCount < w) {
		num = (int)(Math.random()*10);
		wCount++;
	}
        
        while (num > 0) {
            sum += num;
            count ++;
            num = (int)(Math.random()*10);

        }
        
        outputLabel.setText(count + " non-zero numbers were created and their sum is " + sum);
    }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new erg1GUI().setVisible(true);
            }
        });
    }
     
    private javax.swing.JLabel inputLabel;
    private javax.swing.JButton convertButton;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextField textInputField;
     
}
