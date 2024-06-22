
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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
public class pos extends javax.swing.JFrame {

     // mysql data base 
   String url = "jdbc:mysql://103.148.14.130:3306/ahmmedim_posPro";
   String user = "ahmmedim_siyam";
   String password = "yeTzcWePNPYS";
   
   int invoiceNo;
   String formattedDate;
   
    public pos() {
        initComponents();
        Image icon = new ImageIcon(this.getClass().getResource("/6.png")).getImage(); 
        this.setIconImage(icon);
        bill();
        //this.setExtendedState(pos.MAXIMIZED_BOTH);
    }

   public void bill(){
         DecimalFormat numberFormat = new DecimalFormat("#.00");
        double sum = 0;
       jTextPaneBill.setText("\t\t  SEI Innovations           \n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"\t\t  Bonpara,Natore           \n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"\t\t  01722111133           \n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"------------------------------------------------------------------------------------------------\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"Item   \tQty\tUnit Price    \t Discount \t    Total\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"------------------------------------------------------------------------------------------------\n");
        DefaultTableModel table = (DefaultTableModel)jTable1.getModel();
        for(int i=0;i<table.getRowCount();i++){
            String item = table.getValueAt(i, 1).toString();
            String qty = table.getValueAt(i, 2).toString();
            String price = table.getValueAt(i, 3).toString();
            String discountType = table.getValueAt(i, 4).toString();
            String discount = table.getValueAt(i, 5).toString();
            String totalAmount = table.getValueAt(i, 6).toString();
                        
            // calculate discount and subtotal and grandtotal
                        int CalMulti = Integer.parseInt(discount)*Integer.parseInt(qty);
                        double subs;
                        if("Cash".equals(discountType)){
                          subs = Double.parseDouble(discount);   
                        }else{
                          subs = Integer.parseInt(price)*Integer.parseInt(qty)*(Double.parseDouble(discount)/(double)100);
                        }
        
                        sum = sum+Double.parseDouble(jTable1.getValueAt(i,6).toString());
                        
                        double tax = Double.parseDouble(jTextFieldTax.getText());
                        double dis = Double.parseDouble(jTextFieldDisount.getText());

                        double taxAmount = sum*(tax/(double)100);
                        double discountAmount = (sum+taxAmount)*(dis/(double)100);
                        double grandTotal = (sum+taxAmount) - discountAmount;
                        
                      
                        
            jTextPaneBill.setText(jTextPaneBill.getText()+item+"\n");
            jTextPaneBill.setText(jTextPaneBill.getText()+"\t"+qty+"\t"+price+"\t"+numberFormat.format(subs)+"\t"+totalAmount+" \n");
            
            
        }
        jTextPaneBill.setText(jTextPaneBill.getText()+"------------------------------------------------------------------------------------------------\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"\t\t\t Sub Total : "+numberFormat.format(sum)+"\n\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"\t\t\t Vat Amount :" +numberFormat.format(sum)+"\n\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"\t\t\t Discount Amount : "+numberFormat.format(sum)+"\n\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"\t\t\t Grand Total : "+numberFormat.format(sum)+"\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"------------------------------------------------------------------------------------------------\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"\t\t\t Paid Amount : "+jTextFieldCash.getText()+"\n\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"\t\t\t Payment Type : "+jComboBox1.getSelectedItem()+"\n\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"\t\t\t Change Back : "+jLabel2.getText()+"\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"------------------------------------------------------------------------------------------------\n");
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        formattedDate = dt.format(myFormatObj);
         jTextPaneBill.setText(jTextPaneBill.getText()+"Invoice No: "+invoiceNo+"\n\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"Printed on: "+formattedDate+"\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"--------------------------------------------------------------------------------------------\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"Thanks for your purchased\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"--------------------------------------------------------------------------------------------\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"Software By SEI Innovations\n"); 
        jTextPaneBill.setText(jTextPaneBill.getText()+"Phone : +880137328723\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"Email : info@seiinnovations.com\n");
        jTextPaneBill.setText(jTextPaneBill.getText()+"Website : seiinnovations.com\n");
        
   }    
   
   public void calculateBill(){
       DefaultTableModel table = (DefaultTableModel)jTable1.getModel();                
                // calculate sub total
                
                int i; double sum = 0;
        for(i=0;i<jTable1.getRowCount();i++){
            sum = sum+Double.parseDouble(jTable1.getValueAt(i,6).toString());
        }
        double tax = Double.parseDouble(jTextFieldTax.getText());
        double dis = Double.parseDouble(jTextFieldDisount.getText());
        
        double taxAmount = sum*(tax/(double)100);
        double discountAmount = (sum+taxAmount)*(dis/(double)100);
        double grandTotal = (sum+taxAmount) - discountAmount;
        
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        
        jLabelSubTotal.setText(String.valueOf(numberFormat.format(sum)));
        jLabelVatAmount.setText(String.valueOf(numberFormat.format(taxAmount)));
        jTextFieldDiscountAmount.setText(String.valueOf(numberFormat.format(discountAmount)));
        jTextFieldGrandTotal.setText(String.valueOf(numberFormat.format(grandTotal)));
   }
   
   public void updateTable(int i, int cal){
       // cal means quantity
       // i means row index
       
                       jTable1.setValueAt(String.valueOf(cal), i, 2);
                       
                       // calculate price and amount according to quantity update
                       String getPrice = (String) jTable1.getValueAt(i, 3);
                       String getDiscount = (String) jTable1.getValueAt(i, 5);
                       String getDiscountType = (String) jTable1.getValueAt(i, 4);
                       
                        int CalMulti = Integer.parseInt(getPrice)*cal;
                        double subs;
                        if("Cash".equals(getDiscountType)){
                          subs = Double.parseDouble(getDiscount);   
                        }else{
                          subs = Integer.parseInt(getPrice)*cal*(Double.parseDouble(getDiscount)/(double)100);
                        }

                        double CalAmount = CalMulti-subs;
                       
                        DecimalFormat numberFormat = new DecimalFormat("#.00");
                        
                        jTable1.setValueAt(String.valueOf(numberFormat.format(CalAmount)), i, 6);
                        
                        
                        calculateBill();
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
        jLabel1 = new javax.swing.JLabel();
        jTextFieldScanCode = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldTax = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDisount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldCash = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabelSubTotal = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabelVatAmount = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldGrandTotal = new javax.swing.JLabel();
        jTextFieldDiscountAmount = new javax.swing.JLabel();
        jButtonPayPrint = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneBill = new javax.swing.JTextPane();
        jrow = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Point of sale Software By SEI Innovations");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Product ID/Barcode: ");

        jTextFieldScanCode.setBackground(new java.awt.Color(0, 153, 153));
        jTextFieldScanCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldScanCode.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldScanCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldScanCodeActionPerformed(evt);
            }
        });
        jTextFieldScanCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldScanCodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldScanCodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldScanCodeKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jSpinner1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/remove.png"))); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/checked.png"))); // NOI18N
        jButton3.setText("Confirm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldScanCode, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner1)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldScanCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldTax.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTax.setText("0");
        jTextFieldTax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTaxActionPerformed(evt);
            }
        });
        jTextFieldTax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTaxKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("%");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/gift.png"))); // NOI18N
        jLabel4.setText("Discount:");

        jTextFieldDisount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldDisount.setText("0");
        jTextFieldDisount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDisountKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("%");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/vat.png"))); // NOI18N
        jLabel6.setText("Vat:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/money.png"))); // NOI18N
        jLabel7.setText("Paid Amount:");

        jTextFieldCash.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTextFieldCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCashActionPerformed(evt);
            }
        });
        jTextFieldCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCashKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Payment Type:");

        jComboBox1.setFont(new java.awt.Font("Yu Gothic", 1, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Bkash", "Nagad", "Card" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Change Back:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("0");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Tk");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/money (1).png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("SUB TOTAL :");

        jLabelSubTotal.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSubTotal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabelSubTotal.setText("0");
        jLabelSubTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setText("DISCOUNT AMOUNT :");

        jLabelVatAmount.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabelVatAmount.setText("0");
        jLabelVatAmount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("VAT AMOUNT :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setText("GRAND TOTAL AMOUNT :");

        jTextFieldGrandTotal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTextFieldGrandTotal.setText("0");
        jTextFieldGrandTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldDiscountAmount.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTextFieldDiscountAmount.setText("0");
        jTextFieldDiscountAmount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonPayPrint.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButtonPayPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/check.png"))); // NOI18N
        jButtonPayPrint.setText("Pay & Print");
        jButtonPayPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPayPrintActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pro/remove.png"))); // NOI18N
        jButton4.setText("Remove All");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addComponent(jTextFieldCash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPayPrint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldDisount, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addComponent(jTextFieldTax))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(280, 280, 280)
                                        .addComponent(jLabel14))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(130, 130, 130)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel16)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVatAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDiscountAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldTax)
                        .addComponent(jLabel3)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVatAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldDisount)
                        .addComponent(jLabel5)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jTextFieldDiscountAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jButtonPayPrint)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addComponent(jTextFieldCash, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product", "Quantity", "Unit Price", "Discount Type", "Discount", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jScrollPane3.setViewportView(jTextPaneBill);

        jrow.setFont(new java.awt.Font("Segoe UI", 0, 1)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(371, 371, 371)
                .addComponent(jrow)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jrow, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldScanCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldScanCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldScanCodeActionPerformed

    private void jTextFieldTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTaxActionPerformed

    private void jTextFieldScanCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldScanCodeKeyPressed
        // search product
       
          if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            int count =0;
            int code = Integer.parseInt(jTextFieldScanCode.getText());
            
           // update quantity for duplicate scan
           if(jTable1.getRowCount() > 0){
           int i;
           for(i=0;i<jTable1.getRowCount();i++){
              String id= (String) jTable1.getValueAt(i, 0);
               
                   if(String.valueOf(code).equals(id)){
                       String getQty = (String) jTable1.getValueAt(i, 2);
                       int cal = Integer.parseInt(getQty)+1;
                       jTable1.setValueAt(String.valueOf(cal), i, 2);
                       
                       // calculate price and amount according to quantity update
                       String getPrice = (String) jTable1.getValueAt(i, 3);
                       String getDiscount = (String) jTable1.getValueAt(i, 5);
                       String getDiscountType = (String) jTable1.getValueAt(i, 4);
                       
                        int CalMulti = Integer.parseInt(getPrice)*cal;
                        double subs;
                        if("Cash".equals(getDiscountType)){
                          subs = Double.parseDouble(getDiscount);   
                        }else{
                          subs = Integer.parseInt(getPrice)*cal*(Double.parseDouble(getDiscount)/(double)100);
                        }

                        double CalAmount = CalMulti-subs;
                       
                        DecimalFormat numberFormat = new DecimalFormat("#.00");
                        
                        jTable1.setValueAt(String.valueOf(numberFormat.format(CalAmount)), i, 6);
                        
                        calculateBill();
                       count++;
                   }
               
           }
       }
           if(count == 0){
                            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM product WHERE code="+code);
            
            if(result.next() == false){
                 JOptionPane.showMessageDialog(null, "Invalid product code. Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                String name,quantity,price,discount,discountType;
                
                name = result.getString("name");
                quantity = "1";
                price = result.getString("price");
                discount = result.getString("discount");
                discountType = result.getString("discount_type");
                
                int multi = Integer.parseInt(price)*Integer.parseInt(quantity);
                double sub;
                if("Cash".equals(discountType)){
                  sub = Double.parseDouble(discount);   
                }else{
                  sub = Integer.parseInt(price)*Integer.parseInt(quantity)*(Double.parseDouble(discount)/(double)100);
                }
                
                double amount = multi-sub;
                
                String tableData[] = {String.valueOf(code),name,quantity,price,discountType,discount,String.valueOf(amount)};

                DefaultTableModel table = (DefaultTableModel)jTable1.getModel();

                table.addRow(tableData);

                
                // calculate sub total
                
        int j; double sum = 0;
        for(j=0;j<jTable1.getRowCount();j++){
            sum = sum+Double.parseDouble(jTable1.getValueAt(j,6).toString());
        }
        double tax = Double.parseDouble(jTextFieldTax.getText());
        double dis = Double.parseDouble(jTextFieldDisount.getText());
        
        double taxAmount = sum*(tax/(double)100);
        double discountAmount = (sum+taxAmount)*(dis/(double)100);
        double grandTotal = (sum+taxAmount) - discountAmount;
        
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        
        jLabelSubTotal.setText(String.valueOf(numberFormat.format(sum)));
        jLabelVatAmount.setText(String.valueOf(numberFormat.format(taxAmount)));
        jTextFieldDiscountAmount.setText(String.valueOf(numberFormat.format(discountAmount)));
        jTextFieldGrandTotal.setText(String.valueOf(numberFormat.format(grandTotal)));
            }    
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(pos.class.getName()).log(Level.SEVERE, null, ex);
        }
           }

       jTextFieldScanCode.setText("");
       bill();
             
        }
    }//GEN-LAST:event_jTextFieldScanCodeKeyPressed

    private void jTextFieldTaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTaxKeyReleased
        calculateBill();
        bill();
    }//GEN-LAST:event_jTextFieldTaxKeyReleased

    private void jTextFieldDisountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDisountKeyReleased
        calculateBill();
        bill();
    }//GEN-LAST:event_jTextFieldDisountKeyReleased

    private void jTextFieldCashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCashKeyReleased
        double grandTotal = Double.parseDouble(jTextFieldGrandTotal.getText());
        double cash = Double.parseDouble(jTextFieldCash.getText());
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        jLabel2.setText(String.valueOf(numberFormat.format(cash-grandTotal)));
        bill();
    }//GEN-LAST:event_jTextFieldCashKeyReleased

    private void jTextFieldCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCashActionPerformed

    private void jTextFieldScanCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldScanCodeKeyTyped
        // TODO add your handling code here:
   
    }//GEN-LAST:event_jTextFieldScanCodeKeyTyped

    private void jTextFieldScanCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldScanCodeKeyReleased
        // check product id integer or not
           String text = jTextFieldScanCode.getText();
            if(text != null) {
        if(!text.matches("[0-9]*")) {
            JOptionPane.showMessageDialog(null, "Invalid product code. Integer only!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldScanCode.setText("");                
        }
    }
        
    }//GEN-LAST:event_jTextFieldScanCodeKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // code for update or delete selected row
        int j = jTable1.getSelectedRow();
        String product = jTable1.getValueAt(j,1).toString();
        int selectedQty =  Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        int selectedCode =  Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        jLabel13.setText(String.valueOf(product));
        jSpinner1.setValue(selectedQty);
        jTextFieldScanCode.setText(String.valueOf(selectedCode));
        jrow.setText(String.valueOf(j));
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // code for confirm button
         if(jrow.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No product selected!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
             int row = Integer.parseInt(jrow.getText());
            int changedQuantity = (int) jSpinner1.getValue();
        updateTable(row,changedQuantity);
        jTextFieldScanCode.setText("");
        jLabel13.setText("");
        jSpinner1.setValue(0);
         }
        bill();       
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // remove btn
         if(jrow.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No product selected!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
             DefaultTableModel table = (DefaultTableModel)jTable1.getModel();  
            int row = Integer.parseInt(jrow.getText());
            table.removeRow(row);
            jTextFieldScanCode.setText("");
            jLabel13.setText("");
            jSpinner1.setValue(0);
         }
         bill();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // remove all btn code
        DefaultTableModel table = (DefaultTableModel)jTable1.getModel();
        table.setRowCount(0);
        bill();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
 
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // payment type
        bill();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButtonPayPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPayPrintActionPerformed

        // check is there have table row exist or not
        
        if(jTable1.getRowCount() > 0){
            
          // insert invoice after print invoice
            
            String subTotal = jLabelSubTotal.getText();
            String vatAmount = jLabelVatAmount.getText();
            String discountAmount = jTextFieldDiscountAmount.getText();
            String grandTotal = jTextFieldGrandTotal.getText();
            String paymentMethod = jComboBox1.getSelectedItem().toString();
            String paidAmount = jTextFieldCash.getText();
            String cashBack = jLabel2.getText();
            String printedOn = formattedDate;
            
            // check paid amount empty or not
            
            if(paidAmount.isEmpty()){
                JOptionPane.showMessageDialog(null, "Paid amount empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                         // print reciept 
          Random random = new Random();

        // generate a random integer from 0 to 899, then add 100
         invoiceNo = ThreadLocalRandom.current().nextInt(1111, 9999)+random.nextInt(11);
        bill();
        try {
            jTextPaneBill.print();
        } catch (PrinterException ex) {
            Logger.getLogger(pos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement st = conn.createStatement();
            String query = "INSERT INTO `invoice`(`invoiceNo`, `sub_total`, `vat_amount`, `discount_amount`, `grand_total`, `payment_method`, `paid_amount`, `cash_back`, `printed_on`) VALUES('"+invoiceNo+"','"+subTotal+"','"+vatAmount+"','"+discountAmount+"','"+grandTotal+"','"+paymentMethod+"','"+paidAmount+"','"+cashBack+"','"+printedOn+"')";
            st.executeUpdate(query);  
            for(int k=0;k<jTable1.getRowCount();k++){
                String itemId = jTable1.getValueAt(k,0).toString(); 
                String changeItemName = jTable1.getValueAt(k,1).toString();
                // replace ' --> ''
                String item = changeItemName.replace("'","''");
                String qty = jTable1.getValueAt(k,2).toString();
                String unit_price = jTable1.getValueAt(k,3).toString();
                String discount_type = jTable1.getValueAt(k, 4).toString();
                String discount_amount	 = jTable1.getValueAt(k,5).toString();
                String total_amount = jTable1.getValueAt(k,6).toString();
                String invoiceSql = "INSERT INTO `invoice_item`(`invoiceId`, `item_id`, `item`, `qty`, `unit_price`, `discount_type`, `discount_amount`, `total_amount`) VALUES('"+invoiceNo+"','"+itemId+"','"+item+"','"+qty+"','"+unit_price+"','"+discount_type+"','"+discount_amount+"','"+total_amount+"')"; 
                st.executeUpdate(invoiceSql);
                
            }
            
            DefaultTableModel table = (DefaultTableModel)jTable1.getModel();
            table.setRowCount(0);
            jTextFieldTax.setText("0");
            jTextFieldDisount.setText("0");
            jTextFieldCash.setText("");
            jLabel2.setText("");
            jComboBox1.setSelectedIndex(0);
            
            calculateBill();
            bill();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }    
            } 
       }else{
            JOptionPane.showMessageDialog(null, "No product added yet!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonPayPrintActionPerformed

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
            java.util.logging.Logger.getLogger(pos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonPayPrint;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelSubTotal;
    private javax.swing.JLabel jLabelVatAmount;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCash;
    private javax.swing.JLabel jTextFieldDiscountAmount;
    private javax.swing.JTextField jTextFieldDisount;
    private javax.swing.JLabel jTextFieldGrandTotal;
    private javax.swing.JTextField jTextFieldScanCode;
    private javax.swing.JTextField jTextFieldTax;
    private javax.swing.JTextPane jTextPaneBill;
    private javax.swing.JLabel jrow;
    // End of variables declaration//GEN-END:variables
}
