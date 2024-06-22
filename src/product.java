
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class product extends javax.swing.JFrame {

  // mysql data base 
   String url = "jdbc:mysql://103.148.14.130:3306/ahmmedim_posPro";
   String user = "ahmmedim_siyam";
   String password = "yeTzcWePNPYS";
   
    public product() {
        initComponents();
        Image icon = new ImageIcon(this.getClass().getResource("/6.png")).getImage(); 
        this.setIconImage(icon);
        loadTable();
    }
    
    
    public final void loadTable(){
            try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM product");
            
            while(result.next())
            {
               String code,name,price,qty,discountType,discount;
                code = result.getString("code");
                name = result.getString("name");
                price = result.getString("price");
                qty = result.getString("qty");
                discountType = result.getString("discount_type");
                discount = result.getString("discount");
                
                String tableData[] = {code,name,qty,price,discountType,discount};
                DefaultTableModel table = (DefaultTableModel)jTableProduct.getModel();
                
                table.addRow(tableData);
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadLeatestData(){
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM product ORDER BY id DESC LIMIT 1");
            
                 while(result.next())
            {
                String code,name,price,qty,discountType,discount;
                code = result.getString("code");
                name = result.getString("name");
                price = result.getString("price");
                qty = result.getString("qty");
                discountType = result.getString("discount_type");
                discount = result.getString("discount");
                
                String tableData[] = {code,name,qty,price,discountType,discount};
                DefaultTableModel table = (DefaultTableModel)jTableProduct.getModel();
                
                table.addRow(tableData);
                
            }
            
           
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // search product info with product id
    public void productInfo(int Productcode){
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement Productstatement = conn.createStatement();
            ResultSet ProductResult = Productstatement.executeQuery("SELECT * FROM product WHERE code='"+Productcode+"'");
            
            if(ProductResult.next() == false){
                JOptionPane.showMessageDialog(null, "Invalid product ID. Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                jTextFieldProductId.setText("");         
            }else{
                String UpdateName,updatePrice,updateQty,updateDiscountType,updateDiscount;
                UpdateName = ProductResult.getString("name");
                updatePrice = ProductResult.getString("price");
                updateQty = ProductResult.getString("qty");
                updateDiscountType = ProductResult.getString("discount_type");
                updateDiscount = ProductResult.getString("discount");
                
                jTextFieldName.setText(UpdateName);
                jTextFieldQty.setText(updateQty);
                jTextFieldPrice.setText(updatePrice);
                jComboBox1.setSelectedItem(updateDiscountType);
                jTextFieldDiscount.setText(updateDiscount); 
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // function for delete product
    public void deleteProduct(int code){
                 try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement Productstatement = conn.createStatement();            
            Productstatement.executeUpdate("DELETE FROM product WHERE code="+code);
            
            JOptionPane.showMessageDialog(null, "Deleted Successfully!", "Error", JOptionPane.ERROR_MESSAGE);
            
                jTextFieldProductId.setText("");  
                jTextFieldName.setText("");
                jTextFieldQty.setText("");
                jTextFieldPrice.setText("");
                jComboBox1.setSelectedIndex(0);
                jTextFieldDiscount.setText("");
                
        DefaultTableModel productTable = (DefaultTableModel)jTableProduct.getModel();
        productTable.setRowCount(0);
        loadTable();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldProductId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldQty = new javax.swing.JTextField();
        jTextFieldPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldDiscount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButtonSave = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProduct = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Point of sale Software By SEI Innovations");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(394, 104));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Product Info:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Product ID:");

        jTextFieldProductId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldProductId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldProductIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldProductIdKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldProductId, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Name:");

        jTextFieldName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Qty:");

        jTextFieldQty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldQty.setText("1");
        jTextFieldQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldQtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldQtyKeyReleased(evt);
            }
        });

        jTextFieldPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldPrice.setText("0");
        jTextFieldPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPriceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPriceKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Price:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Discount Type:");

        jTextFieldDiscount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldDiscount.setText("0");
        jTextFieldDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDiscountKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Discount:");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Cash", "Percent" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButtonSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/checked.png"))); // NOI18N
        jButtonSave.setText("Add");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/updated (1).png"))); // NOI18N
        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/remove.png"))); // NOI18N
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPrice))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldQty))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDiscount)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldQty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 46, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableProduct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Qty", "Price", "Discount Type", "Discount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProduct.setCellSelectionEnabled(true);
        jTableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProduct);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
      
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
       // insert product into database
       
        Random random = new Random();
        
        if(jTextFieldName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter Product Name", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        int productCode = random.nextInt(Integer.MAX_VALUE);
        String changeProductName = jTextFieldName.getText();
        // replace ' --> ''
        String productName = changeProductName.replace("'","''");
        int qty = Integer.parseInt(jTextFieldQty.getText());
        String discountType = jComboBox1.getSelectedItem().toString();
        int discount = Integer.parseInt(jTextFieldDiscount.getText()) ;
        int productPrice = Integer.parseInt(jTextFieldPrice.getText());
             try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement st = conn.createStatement();
            String query = "INSERT INTO `product`(`code`, `name`, `price`,`qty`,`discount_type`,`discount`) VALUES('"+productCode+"','"+productName+"','"+productPrice+"','"+qty+"','"+discountType+"','"+discount+"')";
            int sql = st.executeUpdate(query);
            if(sql == 0){
                JOptionPane.showMessageDialog(null, "Failed", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            
            loadLeatestData();
            
            // emptu textFields\
            jTextFieldName.setText("");
            jTextFieldQty.setText("");
            jTextFieldDiscount.setText("");
            jTextFieldPrice.setText("");
            jComboBox1.setSelectedIndex(0);
            
      
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }   
        }
       
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTextFieldQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQtyKeyPressed
        // check integer number
          
    }//GEN-LAST:event_jTextFieldQtyKeyPressed

    private void jTextFieldPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPriceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPriceKeyPressed

    private void jTextFieldPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPriceKeyReleased
        // check integer number for price amount
         String text = jTextFieldPrice.getText();
            if(text != null) {
        if(!text.matches("[0-9]*")) {
            JOptionPane.showMessageDialog(null, "Invalid Price. Integer only!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldPrice.setText("");                
        }
            }
    }//GEN-LAST:event_jTextFieldPriceKeyReleased

    private void jTextFieldQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQtyKeyReleased
        // check integer number for quantity
         String text = jTextFieldQty.getText();
            if(text != null) {
        if(!text.matches("[0-9]*")) {
            JOptionPane.showMessageDialog(null, "Invalid Quantity. Integer only!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldQty.setText("");                
        }
            }
    }//GEN-LAST:event_jTextFieldQtyKeyReleased

    private void jTextFieldDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDiscountKeyReleased
        // check integer number for discount amount
         String text = jTextFieldDiscount.getText();
            if(text != null) {
        if(!text.matches("[0-9]*")) {
            JOptionPane.showMessageDialog(null, "Invalid Discount. Integer only!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldDiscount.setText("");                
        }
            }
    }//GEN-LAST:event_jTextFieldDiscountKeyReleased

    private void jTextFieldProductIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldProductIdKeyReleased
       // check integer number for discount amount
         String text = jTextFieldProductId.getText();
            if(text != null) {
        if(!text.matches("[0-9]*")) {
            JOptionPane.showMessageDialog(null, "Invalid Product Id. Integer only!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldProductId.setText("");                
        }
            }
    }//GEN-LAST:event_jTextFieldProductIdKeyReleased

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // update product
        String productCode = jTextFieldProductId.getText();
        if(jTextFieldName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter Product Name", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        String productName = jTextFieldName.getText();
        int qty = Integer.parseInt(jTextFieldQty.getText());
        String discountType = jComboBox1.getSelectedItem().toString();
        int discount = Integer.parseInt(jTextFieldDiscount.getText()) ;
        int productPrice = Integer.parseInt(jTextFieldPrice.getText());
             try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement st = conn.createStatement();
            String query = "UPDATE `product` SET `name`='"+productName+"', `price`='"+productPrice+"',`qty`='"+qty+"',`discount_type`='"+discountType+"',`discount`='"+discount+"' WHERE code='"+productCode+"'";
            int sql = st.executeUpdate(query);
            if(sql == 0){
                JOptionPane.showMessageDialog(null, "Failed", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Update Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            
      
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }   
        }
        
        DefaultTableModel productTable = (DefaultTableModel)jTableProduct.getModel();
        productTable.setRowCount(0);
        loadTable();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jTextFieldProductIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldProductIdKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            int prodCode = Integer.parseInt(jTextFieldProductId.getText());
            
            productInfo(prodCode);
        }
    }//GEN-LAST:event_jTextFieldProductIdKeyPressed

    private void jTableProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProductMouseClicked
        // code for show selected table row data
        int row = jTableProduct.getSelectedRow();
        String productId = (String) jTableProduct.getValueAt(row,0);
       
        jTextFieldProductId.setText(productId);
        // function and pass parameter for get product information
        productInfo(Integer.parseInt(productId));
    }//GEN-LAST:event_jTableProductMouseClicked

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        //  delete product
        int code = Integer.parseInt(jTextFieldProductId.getText());
        deleteProduct(code);
        
    }//GEN-LAST:event_jButtonDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new product().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProduct;
    private javax.swing.JTextField jTextFieldDiscount;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldProductId;
    private javax.swing.JTextField jTextFieldQty;
    // End of variables declaration//GEN-END:variables
}
