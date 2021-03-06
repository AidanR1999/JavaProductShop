/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Customer;

/**
 *
 * @author Aidan
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
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
        cmdCustomerLogin = new javax.swing.JButton();
        cmdStaffLogin = new javax.swing.JButton();
        cmdViewProducts = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Main Menu");

        cmdCustomerLogin.setText("Customer Login");
        cmdCustomerLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCustomerLoginActionPerformed(evt);
            }
        });

        cmdStaffLogin.setText("Staff Login");
        cmdStaffLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdStaffLoginActionPerformed(evt);
            }
        });

        cmdViewProducts.setText("View Products");
        cmdViewProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdViewProductsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(164, 164, 164)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(cmdCustomerLogin)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(cmdViewProducts)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(cmdStaffLogin)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(cmdCustomerLogin)
                .addGap(18, 18, 18)
                .addComponent(cmdStaffLogin)
                .addGap(18, 18, 18)
                .addComponent(cmdViewProducts)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //load customer login page
    private void cmdCustomerLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCustomerLoginActionPerformed
        CustomerLogin cl = new CustomerLogin();
        this.dispose();
        cl.setVisible(true);
    }//GEN-LAST:event_cmdCustomerLoginActionPerformed

    //load staff login page
    private void cmdStaffLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdStaffLoginActionPerformed
        StaffLogin sl = new StaffLogin();
        this.dispose();
        sl.setVisible(true);
    }//GEN-LAST:event_cmdStaffLoginActionPerformed

    //load view products page
    private void cmdViewProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdViewProductsActionPerformed
        ViewProducts vp = new ViewProducts(new Customer());
        this.dispose();
        vp.setVisible(true);
    }//GEN-LAST:event_cmdViewProductsActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCustomerLogin;
    private javax.swing.JButton cmdStaffLogin;
    private javax.swing.JButton cmdViewProducts;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
