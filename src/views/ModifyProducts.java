/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import models.DBManager;
import models.Product;
import models.Staff;

/**
 *
 * @author Aidan
 */
public class ModifyProducts extends javax.swing.JFrame {

    //logged in staff
    private static Staff staff;
    
    //all products
    private HashMap<Integer, Product> products;
    
    /**
     * Creates new form ViewProducts
     */
    public ModifyProducts(Staff s) {
        this.staff = s;
        
        //load all products from database
        DBManager db = new DBManager();
        products = db.loadProducts();
        
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

        cmdBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProductTypes = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstProducts = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmdAddProduct = new javax.swing.JButton();
        cmdEditProduct = new javax.swing.JButton();
        cmdDeleteProduct = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdBack.setText("Back");
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });

        jLabel1.setText("Edit Products");

        lstProductTypes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Clothing", "Footwear" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstProductTypes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstProductTypesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstProductTypes);

        jScrollPane2.setViewportView(lstProducts);

        jLabel2.setText("Product Type");

        jLabel3.setText("Products");

        cmdAddProduct.setText("Add product");
        cmdAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddProductActionPerformed(evt);
            }
        });

        cmdEditProduct.setText("Edit product");
        cmdEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditProductActionPerformed(evt);
            }
        });

        cmdDeleteProduct.setText("Delete product");
        cmdDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdBack)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(45, 46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cmdEditProduct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdAddProduct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDeleteProduct)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdBack)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cmdAddProduct)
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdEditProduct)
                            .addComponent(cmdDeleteProduct))
                        .addGap(14, 14, 14))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //return to staff home page
    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        StaffHome sm = new StaffHome(staff);
        this.dispose();
        sm.setVisible(true);
    }//GEN-LAST:event_cmdBackActionPerformed

    //user selects product type
    private void lstProductTypesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstProductTypesValueChanged
        //get product type
        String typeSelected = lstProductTypes.getSelectedValue();
        
        //set table model
        DefaultListModel productList = new DefaultListModel();
        
        //for every product in the hashmap
        for(Map.Entry<Integer, Product> productEntry : products.entrySet())
        {
            //get product from current iteration
            Product product = productEntry.getValue();
            
            //if product types match
            if(product.getClass().getName().equals("models." + typeSelected))
            {
                //add product to table
                productList.addElement(product);
            }
        }
        
        //display table
        lstProducts.setModel(productList);
    }//GEN-LAST:event_lstProductTypesValueChanged

    //load add product page
    private void cmdAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddProductActionPerformed
        AddProduct ap = new AddProduct(staff);
        this.dispose();
        ap.setVisible(true);
    }//GEN-LAST:event_cmdAddProductActionPerformed

    //load edit product page
    private void cmdEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditProductActionPerformed
        //if user has selected a product
        if(lstProducts.getSelectedIndex() != -1)
        {
            //store product selected
            Object selectedObject = (Object)lstProducts.getSelectedValue();
            Product product = (Product) selectedObject;
            
            //load edit product page
            EditProduct ep = new EditProduct(staff, product);
            this.dispose();
            ep.setVisible(true);
        }
    }//GEN-LAST:event_cmdEditProductActionPerformed

    //delete product from database
    private void cmdDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteProductActionPerformed
        //if user has selected a product
        if(lstProducts.getSelectedIndex() != -1)
        {
            //get selected product
            Object productObject = (Object) lstProducts.getSelectedValue();
            Product product = (Product) productObject;
            
            //delete product from database
            DBManager db = new DBManager();
            db.deleteProduct(product);
            
            //reload modify products page
            ModifyProducts mp = new ModifyProducts(staff);
            this.dispose();
            mp.setVisible(true);
        }
    }//GEN-LAST:event_cmdDeleteProductActionPerformed

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
            java.util.logging.Logger.getLogger(ModifyProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifyProducts(staff).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAddProduct;
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdDeleteProduct;
    private javax.swing.JButton cmdEditProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstProductTypes;
    private javax.swing.JList<String> lstProducts;
    // End of variables declaration//GEN-END:variables
}
