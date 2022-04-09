/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author RAVI PANCHAL
 */
public class Payment extends javax.swing.JFrame {

    /**
     * Creates new form Payment
     */
    public Payment() {
        initComponents();
        setAmt();
    }
    public void setAmt()
    {
        txt_amt.setText(String.valueOf(PayBill.payAmt));
    }
    public void payAmt()
    {
        int accno = Integer.parseInt(txt_acno.getText());
        int pwd = Integer.parseInt(txt_pwd.getText());
        int amt = PayBill.payAmt;
        int oldbal;
        int newbal;
        String type = "EC-BILL-PAY";
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver"); 			//call oracle driver before that add it into enviro. --> classpath add new
            Connection con = DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            String sql = "select * from bank2 where no=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,accno);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int pw = rs.getInt(4);
            if(pwd==pw)
            {
                oldbal = rs.getInt(3);//first bal of payer who pay the bill
                newbal = oldbal - amt;
                
                String sql2 = "update bank2 set bal=? where no = ?";
                PreparedStatement pst2 = con.prepareStatement(sql2);
                pst2.setInt(1, newbal);
                pst2.setInt(2, accno);
                int rowCount = pst2.executeUpdate();
                if(rowCount > 0)
                {
                    int my_acc = 4;//alpha compnay
                    String sql3 = "select * from bank2 where no=?";
                    PreparedStatement pst3 = con.prepareStatement(sql3);
                    pst3.setInt(1, my_acc);
                    ResultSet rs3 = pst3.executeQuery();
                    if(rs3.next())//here credit in company balance 
                    {
                        int my_oldbal = rs3.getInt(3);
                        int my_newbal = my_oldbal + amt;
                        String sql4 = "update bank2 set bal=? where no = ?";
                        PreparedStatement pst4 = con.prepareStatement(sql4);
                        pst4.setInt(1, my_newbal);
                        pst4.setInt(2, my_acc);
                        int rowCount4 = pst4.executeUpdate();
                        if(rowCount4>0)//if compnay balance is credit then update transaction
                        {
                            Connection con2 = DBconnection.getConnection();
                            String sql7 = "insert into paid_data values(?,?,?,?)";
                            PreparedStatement pst7 = con2.prepareStatement(sql7);
                            pst7.setInt(1,PayBill.meterno);
                            pst7.setString(2,PayBill.year);
                            pst7.setString(3,PayBill.month);
                            pst7.setInt(4,PayBill.payAmt);
                            int Paid_RowCount = pst7.executeUpdate();
                            if(Paid_RowCount>0)
                            {
                                String sql5 = "insert into bank_tran values(?,?,?,?,?)";
                                PreparedStatement pst5 = con.prepareStatement(sql5);
                                pst5.setInt(1, accno);
                                pst5.setInt(2, my_acc);
                                pst5.setInt(3, amt);
                                pst5.setString(4, "Dr.");
                                pst5.setString(5, type);
                                int t_row_1 = pst5.executeUpdate();
                            
                                String sql6 = "insert into bank_tran values(?,?,?,?,?)";
                                PreparedStatement pst6 = con.prepareStatement(sql6);
                                pst6.setInt(1, my_acc);
                                pst6.setInt(2, accno);
                                pst6.setInt(3, amt);
                                pst6.setString(4, "Cr.");
                                pst6.setString(5, type);
                                int t_row_2 = pst6.executeUpdate();
                                if(t_row_1 > 0 && t_row_2 > 0)
                                {
                                    JOptionPane.showMessageDialog(this,"Payment Succesfully Done");
                                    txt_acno.setText("");
                                    txt_pwd.setText("");
                                    txt_amt.setText("");
                                }
                            }
                                                        
                        }
                        
                    }
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Password or Account No is Incorrect" );
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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

        MainPanel = new rojerusan.RSPanelImage();
        bt_back = new javax.swing.JLabel();
        txt_acno = new app.bolivia.swing.JCTextField();
        txt_pwd = new app.bolivia.swing.JCTextField();
        txt_amt = new app.bolivia.swing.JCTextField();
        bt_pay = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        MainPanel.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Icon/Payment.png"))); // NOI18N
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bt_back.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_back.setForeground(new java.awt.Color(255, 255, 255));
        bt_back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_back.setText("Back");
        bt_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_backMouseClicked(evt);
            }
        });
        MainPanel.add(bt_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 60, 30));

        txt_acno.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 204, 0)));
        txt_acno.setPlaceholder("Enter Your Bank Account No");
        MainPanel.add(txt_acno, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, -1, -1));

        txt_pwd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 204, 0)));
        txt_pwd.setPlaceholder("Enter Your Password");
        MainPanel.add(txt_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 175, -1, -1));

        txt_amt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 204, 0)));
        txt_amt.setPlaceholder("Enter Amount");
        MainPanel.add(txt_amt, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        bt_pay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_pay.setForeground(new java.awt.Color(255, 255, 255));
        bt_pay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_pay.setText("PAY");
        bt_pay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_payMouseClicked(evt);
            }
        });
        MainPanel.add(bt_pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 120, 40));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 204, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PAY HERE");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 102)));
        MainPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 210, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(620, 423));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_backMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bt_backMouseClicked

    private void bt_payMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_payMouseClicked
        payAmt();
    }//GEN-LAST:event_bt_payMouseClicked

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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSPanelImage MainPanel;
    private javax.swing.JLabel bt_back;
    private javax.swing.JLabel bt_pay;
    private javax.swing.JLabel jLabel1;
    private app.bolivia.swing.JCTextField txt_acno;
    private app.bolivia.swing.JCTextField txt_amt;
    private app.bolivia.swing.JCTextField txt_pwd;
    // End of variables declaration//GEN-END:variables
}
