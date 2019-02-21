/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.ButtonGroup;
import models.Clothing;
import models.DBManager;
import models.Footwear;
import models.Product;
import models.Staff;

/**
 *
 * @author Aidan
 */
public class EditProduct extends javax.swing.JFrame {

    
    private static Staff staff;
    private static Product product;
    
    /**
     * Creates new form EditProduct
     */
    public EditProduct(Staff s, Product p) {
        this.staff = s;
        this.product = p;
        
        initComponents();
        this.setLocationRelativeTo(null);
        
        txtId.setText(String.valueOf(p.getProductID()));
        txtId.setEnabled(false);
        txtName.setText(p.getProductName());
        txtPrice.setText(String.valueOf(p.getPrice()));
        txtStockLevel.setText(String.valueOf(p.getStockLevel()));
        
        if(product.getClass().getName().equals("models.Clothing"))
        {
            lblMeasureSize.setText("Measurement:");
            Clothing c = (Clothing) product;
            txtMeasureSize.setText(c.getMeasurement());
            
        }
        else
        {
            lblMeasureSize.setText("Shoe Size:");
            Footwear f = (Footwear) product;
            txtMeasureSize.setText(String.valueOf(f.getSize()));
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMeasureSize = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtStockLevel = new javax.swing.JTextField();
        txtMeasureSize = new javax.swing.JTextField();
        cmdSubmit = new javax.swing.JButton();
        cmdBack = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblErrorMessage = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Edit Product");

        jLabel3.setText("Name:");

        jLabel4.setText("Price:");

        jLabel5.setText("Stock Level:");

        lblMeasureSize.setText("jLabel6");

        cmdSubmit.setText("Submit");
        cmdSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSubmitActionPerformed(evt);
            }
        });

        cmdBack.setText("Back");
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });

        jLabel6.setText("ID:");

        lblErrorMessage.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(lblMeasureSize)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txtPrice)
                                    .addComponent(txtStockLevel)
                                    .addComponent(txtMeasureSize)
                                    .addComponent(txtId)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(cmdSubmit)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblErrorMessage)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdBack)
                    .addComponent(jLabel1))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMeasureSize)
                    .addComponent(txtMeasureSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cmdSubmit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(lblErrorMessage)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        ModifyProducts vps = new ModifyProducts(staff);
        this.dispose();
        vps.setVisible(true);
    }//GEN-LAST:event_cmdBackActionPerformed

    private void cmdSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSubmitActionPerformed
        String name = txtName.getText();
        String price = txtPrice.getText();
        String stockLevel = txtStockLevel.getText();
        String MeasureSize = txtMeasureSize.getText();
        
        DBManager db = new DBManager();
        
        if(name.equals("") || price.equals("") || stockLevel.equals("") || MeasureSize.equals(""))
            lblErrorMessage.setText("Error: Please fill out all data");
        else
        {
            try 
            {
                double doublePrice = Double.parseDouble(price);
                
                try 
                {
                    int intStockLevel = Integer.parseInt(stockLevel);
                    
                    if(product.getClass().getName().equals("models.Footwear"))
                    {
                        try 
                        {
                            int intSize = Integer.parseInt(MeasureSize);
                            
                            Footwear f = new Footwear(intSize, product.getProductID(), name, doublePrice, intStockLevel);
                            db.editProduct(f);

                            ModifyProducts mp = new ModifyProducts(staff);
                            this.dispose();
                            mp.setVisible(true);
                        } 
                        catch (Exception e) 
                        {
                            lblErrorMessage.setText("Error: Please enter number for size");
                        }
                    }
                    else
                    {
                        Clothing c = new Clothing(MeasureSize, product.getProductID(), name, doublePrice, intStockLevel);
                        db.editProduct(c);
                        
                        ModifyProducts mp = new ModifyProducts(staff);
                        this.dispose();
                        mp.setVisible(true);
                    }
                } 
                catch (Exception e) 
                {
                    lblErrorMessage.setText("Error: Please enter number for stock level");
                }
            } 
            catch (Exception e) 
            {
                lblErrorMessage.setText("Error: Please enter decimal for price");
            }
        }
    }//GEN-LAST:event_cmdSubmitActionPerformed

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
            java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditProduct(new Staff(), new Product()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErrorMessage;
    private javax.swing.JLabel lblMeasureSize;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMeasureSize;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtStockLevel;
    // End of variables declaration//GEN-END:variables
}
