/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_store_management_system;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author freeprojectz
 */
public class Product extends javax.swing.JFrame {

    Connect ps = new Connect();
    String id;

    /**
     * Creates new form Product
     *
     * @param id
     */
    public Product(String id) {
        this.id = id;
        initComponents();
        Connect.connect_mysql();
        this.report();
    }

    public void report() {
        Connect.connect_mysql();

        String[] tblHead = {"ID", "Product Name", "Cost", "Company", "Type"};
        DefaultTableModel header = new DefaultTableModel(tblHead, 0);

        try {
            String SQL = "SELECT * FROM product";
            ps.statement = ps.connection.createStatement();
            ps.rs = ps.statement.executeQuery(SQL);
            while (ps.rs.next()) {
                String[] item = {
                    ps.rs.getString("product_id"),
                    ps.rs.getString("product_name"),
                    ps.rs.getString("product_cost"),
                    ps.rs.getString("product_company"),
                    ps.rs.getString("product_type")
                };
                header.addRow(item);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        jTable2.setModel(header);
        jTable2.setAutoscrolls(true);
        jTable2.setBackground(Color.LIGHT_GRAY);
        jTable2.setBorder(BorderFactory.createCompoundBorder());
        jTable2.setForeground(Color.BLACK);
        jTable2.setShowGrid(true);

        jTable2.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                JTable target = (JTable) me.getSource();
                Object[] options = {"Edit Record", "Delete Record"};

                int row = target.getSelectedRow(); // select a row
                int clicks = JOptionPane.showOptionDialog(null,
                        "You want to edit or delete the record ?",
                        "Choose a colour",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (clicks == 1) {
                    deleteProduct(String.valueOf(jTable2.getValueAt(row, 0)));
                }
                if (clicks == 0) {
                    editProduct(String.valueOf(jTable2.getValueAt(row, 0)));
                }
            }
        });
    }

    /**
     * Delete the product
     *
     * @param id
     * @throws SQLException
     */
    public void deleteProduct(String id) {
        String SQL = "DELETE FROM product WHERE product_id = " + id;
        try {
            ps.statement = ps.connection.createStatement();
            if (ps.statement.executeUpdate(SQL) == 1) {
                JOptionPane.showMessageDialog(null, "Record Deleted Successfully !!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.report();
    }

    /**
     * Edit the Product
     *
     * @param id
     */
    public void editProduct(String id) {
        this.id = id;
        this.setFormData(id);
    }

    /**
     * Function for set form data
     *
     * @param id
     */
    public void setFormData(String id) {
        try {
            ps.statement = ps.connection.createStatement();
            String SQL = "Select * from product where product_id=" + id + ";";
            System.out.print(SQL);
            ps.rs = ps.statement.executeQuery(SQL);
            if (ps.rs.next()) {
                product_name.setText(ps.rs.getString("product_name"));
                prduct_description.setText(ps.rs.getString("prduct_description"));
                product_cost.setText(ps.rs.getString("product_cost"));
                product_company.setText(ps.rs.getString("product_company"));
                product_type.setText(ps.rs.getString("product_type"));
            } else {
                JOptionPane.showMessageDialog(null, "Sorry!\nAccount number can't be found");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Function for set form data
     *
     * @param id
     */
    public void clearFormData() {
        product_name.setText("");
        prduct_description.setText("");
        product_cost.setText("");
        product_company.setText("");
        product_type.setText("");
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
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        product_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        product_cost = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        prduct_description = new javax.swing.JTextField();
        product_company = new javax.swing.JTextField();
        product_type = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        bt_open = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lb_projectName = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Product Management"));

        jLabel2.setText("Product Name");

        jLabel3.setText("Cost Per Unit");

        jLabel4.setText("Company Name");

        jLabel11.setText("Type");

        jLabel12.setText("Description");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        bt_open.setText("Save Product");
        bt_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_openActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(product_company, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(product_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(product_type, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bt_open)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addComponent(prduct_description, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(397, 397, 397))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(product_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(product_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(product_company, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(product_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(prduct_description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_open)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lb_projectName.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lb_projectName.setText("Product Management");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_projectName)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_projectName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Employee Management");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bt_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_openActionPerformed
        String name = product_name.getText();
        String parent = product_cost.getText();
        String date = product_company.getText();
        String amnt;
        amnt = prduct_description.getText();
        String product_add = product_type.getText();;
        //validation
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Name can't be left empty");
        } else if (parent.equals("")) {
            JOptionPane.showMessageDialog(this, "Father/Husband name can't be left empty");
        } else if (amnt.equals("")) {
            JOptionPane.showMessageDialog(this, "Kindly enter the salary of the product");
            prduct_description.setText(500 + "");
        } else {
            // MySQL
            try {
                String SQL;
                System.out.println("This ID = " + this.id);
                if (this.id.equals("")) {
                    SQL = "INSERT INTO `product` SET \n"
                            + "`product_name` = '" + name + "', \n"
                            + "`product_cost` = '" + parent + "', \n"
                            + "`product_company` = '" + date + "', \n"
                            + "`product_type` = '" + product_add + "', \n"
                            + "`prduct_description` = '" + amnt + "'";
                } else {

                    SQL = "UPDATE `product` SET \n"
                            + "`product_name` = '" + name + "', \n"
                            + "`product_cost` = '" + parent + "', \n"
                            + "`product_company` = '" + date + "', \n"
                            + "`product_type` = '" + product_add + "', \n"
                            + "`prduct_description` = '" + amnt + "'"
                            + "WHERE product_id = " + this.id;
                    this.id = "";
                }
                ps.statement = ps.connection.createStatement();
                int saved = ps.statement.executeUpdate(SQL);
                if (saved == 1) {
                    JOptionPane.showMessageDialog(this, "Product has been saved successfully. ");
                    this.report();
                } else {
                    JOptionPane.showMessageDialog(this, "Issue with saving the record");
                }
                this.clearFormData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_bt_openActionPerformed

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
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_open;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lb_projectName;
    private javax.swing.JTextField prduct_description;
    private javax.swing.JTextField product_company;
    private javax.swing.JTextField product_cost;
    private javax.swing.JTextField product_name;
    private javax.swing.JTextField product_type;
    // End of variables declaration//GEN-END:variables
}
