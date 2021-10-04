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
import java.text.SimpleDateFormat;  
import java.util.Date;  

/**
 *
 * @author freeprojectz
 */
public class Sales extends javax.swing.JFrame {

    Connect ps = new Connect();
    String id;
    int order_id = 0;

    /**
     * Creates new form Product
     *
     * @param id
     */
    public Sales(String id) {
        this.id = id;
        initComponents();
        Connect.connect_mysql();
        jPanel1.setVisible(false);
        this.setComboBoxValue(oi_product_id, "product", "product_id", "product_name");     
    }
    
    /**
     * Function for setting Dropdown value
     * @param comboBox
     * @param table
     * @param id_col
     * @param val_col 
     */
    public void setComboBoxValue(javax.swing.JComboBox comboBox, String table, String id_col, String val_col) {
        comboBox.addItem(new ComboItem("Please Select", ""));
        try {
            String SQL = "SELECT * FROM "+table;
            ps.statement = ps.connection.createStatement();
            ps.rs = ps.statement.executeQuery(SQL);
            while (ps.rs.next()) {
               comboBox.addItem(new ComboItem(ps.rs.getString(val_col), ps.rs.getString(id_col)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }        
    }
    
    public void report() {
        final_cost.setText(this.getFinalCost(this.order_id));
        String[] tblHead = {"ID", "Product Name", "Cost Per Unit", "Quantity", "Total"};
        DefaultTableModel header = new DefaultTableModel(tblHead, 0);

        try {
            System.out.println("Order ID = "+this.order_id);
            String SQL = "SELECT * FROM product, order_item, orders WHERE order_id = oi_order_id AND product_id = oi_product_id AND order_id = "+this.order_id;
            ps.statement = ps.connection.createStatement();
            ps.rs = ps.statement.executeQuery(SQL);
            while (ps.rs.next()) {
                String[] item = {
                    ps.rs.getString("oi_id"),
                    ps.rs.getString("product_name"),
                    ps.rs.getString("oi_cost"),
                    ps.rs.getString("oi_quantity"),
                    ps.rs.getString("oi_total")
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
                Object[] options = {"Yes", "No"};

                int row = target.getSelectedRow(); // select a row
                int clicks = JOptionPane.showOptionDialog(null,
                        "Do you want to delete the Item ?",
                        "Delete Cart Item",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (clicks == 0) {
                    deleteItem(String.valueOf(jTable2.getValueAt(row, 0)));
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
    public void deleteItem(String id) {
        String SQL = "DELETE FROM order_item WHERE oi_id = " + id;
        try {
            ps.statement = ps.connection.createStatement();
            if (ps.statement.executeUpdate(SQL) == 1) {
                this.report();
                JOptionPane.showMessageDialog(null, "Record Deleted Successfully !!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                order_name.setText(ps.rs.getString("product_name"));
//                prduct_description.setText(ps.rs.getString("prduct_description"));
                order_mobile.setText(ps.rs.getString("product_cost"));
//                product_company.setText(ps.rs.getString("product_company"));
//                product_type.setText(ps.rs.getString("product_type"));
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
    public String getFinalCost(int id) {
        String cost = "";
        try {
            ps.statement = ps.connection.createStatement();
            String SQL = "Select SUM(oi_total) as total from order_item where oi_order_id=" + id + ";";
            ps.rs = ps.statement.executeQuery(SQL);
            if (ps.rs.next()) {
               cost = ps.rs.getString("total");
            } else {
                JOptionPane.showMessageDialog(null, "Sorry!\nAccount number can't be found");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return cost;
    }
    /**
     * Function for set form data
     *
     * @param id
     */
    public String getCost(String id) {
        String cost = "";
        try {
            ps.statement = ps.connection.createStatement();
            String SQL = "Select * from product where product_id=" + id + ";";
            ps.rs = ps.statement.executeQuery(SQL);
            if (ps.rs.next()) {
               cost = ps.rs.getString("product_cost");
            } else {
                JOptionPane.showMessageDialog(null, "Sorry!\nAccount number can't be found");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return cost;
    }

    /**
     * Function for set form data
     *
     * @param id
     */
    public void clearFormData() {
      
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
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        bt_open = new javax.swing.JButton();
        oi_product_id = new javax.swing.JComboBox();
        oi_item = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        final_cost = new javax.swing.JLabel();
        lb_projectName = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        order_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        order_mobile = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sales Dashboard"));

        jLabel4.setText("Select Product");

        jLabel11.setText("Quantity");

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

        bt_open.setText("Add Item");
        bt_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_openActionPerformed(evt);
            }
        });

        oi_item.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jButton2.setText("Save Order and Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Total Cost : ");

        final_cost.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        final_cost.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(oi_product_id, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oi_item, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_open, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(final_cost)
                        .addGap(114, 114, 114))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11)
                    .addComponent(oi_product_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oi_item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_open))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(final_cost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );

        lb_projectName.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lb_projectName.setText("Sales Dashboard");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Details"));

        jLabel2.setText("Customer Name");

        jLabel3.setText("Mobile");

        jButton1.setText("Start Billing");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(order_name, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(order_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(order_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(order_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_projectName)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_projectName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Employee Management");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_openActionPerformed
        String product_id= ((ComboItem) oi_product_id.getSelectedItem()).getValue();
        String quantity=(String)oi_item.getSelectedItem();
        String cost = this.getCost(product_id);
        int total_cost = Integer.parseInt(quantity) * Integer.parseInt(cost);
                //validation
        if (product_id.equals("")) {
            JOptionPane.showMessageDialog(this, "Name can't be left empty");
       } else {
            // MySQL
            try {
                String SQL;
                SQL = "INSERT INTO `order_item` SET \n"
                        + "`oi_order_id` = '" + this.order_id + "', \n"
                        + "`oi_product_id` = '" + product_id + "', \n"
                        + "`oi_quantity` = '" + quantity + "', \n"
                        + "`oi_cost` = '" + cost + "', \n"
                        + "`oi_total` = '" + total_cost + "'";
                
                ps.statement = ps.connection.createStatement();
                int saved = ps.statement.executeUpdate(SQL);
                if (saved == 1) {
                    this.report();
                    
                    JOptionPane.showMessageDialog(this, "Product has been saved successfully. ");
                } else {
                    JOptionPane.showMessageDialog(this, "Issue with saving the record");
                }
                this.clearFormData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_bt_openActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String name = order_name.getText();
        String mobile = order_mobile.getText();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        String today_date = formatter.format(date); 
       
        //validation
        if (name.equals("") ||  mobile.equals("")) {
            JOptionPane.showMessageDialog(this, "Name and mobile can't be left empty");
        } else {
            // MySQL
            try {
                String SQL;
                System.out.println("This ID = " + this.id);
                SQL = "INSERT INTO `orders` SET \n"
                        + "`order_name` = '" + name + "', \n"
                        + "`order_date` = '" + today_date + "', \n"
                        + "`order_mobile` = '" + mobile + "'";
                ps.statement = ps.connection.createStatement();
                this.order_id = ps.statement.executeUpdate(SQL, ps.statement.RETURN_GENERATED_KEYS);
                ps.rs= ps.statement.getGeneratedKeys();
                if (ps.rs.next()) {
                   this.order_id = ps.rs.getInt(1);
                   System.out.print(this.order_id);
                   jPanel1.setVisible(true);
                   jButton1.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Issue with saving the record");
                }
                this.report();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String total = final_cost.getText();
        try {
                String SQL;
                System.out.println("This ID = " + this.id);
                SQL = "UPdate `orders` SET \n"
                        + "`order_total` = '" + total + "'"
                        + "WHERE order_id = " + this.order_id;
                ps.statement = ps.connection.createStatement();
                ps.statement.executeUpdate(SQL);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
        }
       this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_open;
    private javax.swing.JLabel final_cost;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lb_projectName;
    private javax.swing.JComboBox oi_item;
    private javax.swing.JComboBox oi_product_id;
    private javax.swing.JTextField order_mobile;
    private javax.swing.JTextField order_name;
    // End of variables declaration//GEN-END:variables
}
