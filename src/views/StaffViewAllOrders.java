/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import models.Customer;
import models.DBManager;
import models.Order;
import models.Staff;

/**
 *
 * @author Aidan
 */
public class StaffViewAllOrders extends javax.swing.JFrame {

    
    private static Staff staff;
    private HashMap<String, Customer> customers;
    
    /**
     * Creates new form CustomerPreviousOrder
     */
    public StaffViewAllOrders(Staff s) {
        staff = s;
        initComponents();
        this.setLocationRelativeTo(null);
        
        DBManager db = new DBManager();
        customers = db.loadCustomers();
        
        DefaultTableModel orders = (DefaultTableModel) tblOrders.getModel();
        
        for(Map.Entry<String, Customer> cEntry : customers.entrySet())
        {
            Customer customer = cEntry.getValue();
            
            for(Map.Entry<Integer, Order> oEntry : customer.getOrders().entrySet())
            {
                Order order = oEntry.getValue();
                
                if(order.getStatus().equals("Complete"))
                {
                    orders.addRow(new Object[] 
                    {
                        customer.getUsername(),
                        order.getOrderId(),
                        new SimpleDateFormat("dd/MM/yyyy").format(order.getOrderDate()),
                        "£" + String.format("%.02f", order.getOrderTotal())
                    });
                }
            }
        }
        
        tblOrders.setModel(orders);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        cmdViewOrder = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdBack.setText("Back");
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer", "Order Id", "Date", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblOrders);

        cmdViewOrder.setText("View Order");
        cmdViewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdViewOrderActionPerformed(evt);
            }
        });

        lblError.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdViewOrder)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(cmdBack))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblError)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(cmdBack, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdViewOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblError)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        StaffHome sh = new StaffHome(staff);
        this.dispose();
        sh.setVisible(true);
    }//GEN-LAST:event_cmdBackActionPerformed

    private void cmdViewOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdViewOrderActionPerformed
        if(tblOrders.getSelectedRow() == -1)
        {
            lblError.setText("Error: Please select an order");
        }
        else
        {
            DefaultTableModel orders = (DefaultTableModel) tblOrders.getModel();
            int orderId = Integer.parseInt(String.valueOf(orders.getValueAt(tblOrders.getSelectedRow(), 1)));
            
            String username = String.valueOf(orders.getValueAt(tblOrders.getSelectedRow(), 0));
            Customer customer = customers.get(username);
            
            ViewPreviousOrderLines vpol = new ViewPreviousOrderLines(staff, orderId, customer);
            this.dispose();
            vpol.setVisible(true);
        }
    }//GEN-LAST:event_cmdViewOrderActionPerformed

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
            java.util.logging.Logger.getLogger(StaffViewAllOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffViewAllOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffViewAllOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffViewAllOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffViewAllOrders(staff).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdViewOrder;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblError;
    private javax.swing.JTable tblOrders;
    // End of variables declaration//GEN-END:variables
}
