/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import java.awt.Dimension;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author RAVI PANCHAL
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        setCustomerTable();
        hideTopPanel();
    }
    public void hideTopPanel()
    {
        top_panel.setVisible(false);
    }
    public Boolean checkBlank()
    {
        String meterno = txt_meterno.getText();
        String fname = txt_fname.getText();
        String lname = txt_lname.getText();
        String district = txt_district.getText();
        String city = txt_city.getText();
        String mobile = txt_mobile.getText();
        String pincode = txt_pincode.getText();
        if(meterno.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter meter no");
            return false;
        }
        if(fname.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter First Name");
            return false;
        }
        if(lname.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter Last Name");
            return false;
        }
        if(district.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter District");
            return false;
        }
        if(city.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter City");
            return false;
        }
        if(mobile.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter Mobile no");
            return false;
        }
        if(pincode.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter Pin Code no");
            return false;
        }
        return true;
    }
    public void setBlank()
    {
        txt_meterno.setText("");
        txt_fname.setText("");
        txt_lname.setText("");
        txt_district.setText("");
        txt_city.setText("");
        txt_mobile.setText("");
        txt_pincode.setText("");
    }
    public void addCustomer()
    {
        int meterno = Integer.parseInt(txt_meterno.getText());
        String fname = txt_fname.getText();
        String lname = txt_lname.getText();
        String district = txt_district.getText();
        String city = txt_city.getText();
        int mobile = Integer.parseInt(txt_mobile.getText());
        int pincode = Integer.parseInt(txt_pincode.getText());
        
        try
        {
            Connection con = DBconnection.getConnection();
            String sql = "insert into customer(meterno,fname,lname,district,city,mobile,pincode) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, meterno);
            pst.setString(2,fname);
            pst.setString(3,lname);
            pst.setString(4,district);
            pst.setString(5,city);
            pst.setInt(6,mobile);
            pst.setInt(7,pincode);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0)
            {
                JOptionPane.showMessageDialog(this,"Customer Added");
                setBlank();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Failed To Add Customer");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void updateCustomer()
    {
        int meterno = Integer.parseInt(txt_meterno.getText());
        String fname = txt_fname.getText();
        String lname = txt_lname.getText();
        String district = txt_district.getText();
        String city = txt_city.getText();
        int mobile = Integer.parseInt(txt_mobile.getText());
        int pincode = Integer.parseInt(txt_pincode.getText());
        
        try
        {
            Connection con = DBconnection.getConnection();
            String sql = "update customer set fname=?,lname=?,district=?,city=?,mobile=?,pincode=? where meterno=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,fname);
            pst.setString(2,lname);
            pst.setString(3,district);
            pst.setString(4,city);
            pst.setInt(5,mobile);
            pst.setInt(6,pincode);
            pst.setInt(7, meterno);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0)
            {
                JOptionPane.showMessageDialog(this,"Customer Updated");
                setBlank();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Failed To Update Customer");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void deleteCustomer()
    {
        int meterno = Integer.parseInt(txt_meterno.getText());
        try
        {
            Connection con = DBconnection.getConnection();
            String sql = "delete from customer where meterno=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, meterno);
            int rowCount = pst.executeUpdate();
            if(rowCount > 0)
            {
                JOptionPane.showMessageDialog(this,"Customer Deleted");
                setBlank();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Failed To Delete Customer");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    int id,meterno;
    String fname,lname,district,city,mobile,pincode;
    DefaultTableModel model;
    int count = 0;
    public void setCustomerTable()
    {
        //hide a scrollbar
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        try
        {
            Connection con = DBconnection.getConnection();
            Statement st = (Statement) con.createStatement();
            String sql = "Select * from customer";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                int meterno = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String district = rs.getString(4);
                String city = rs.getString(5);
                String mobile = rs.getString(6);
                String pincode = rs.getString(7);
                int id = rs.getInt(8);
                
                
                Object[] obj = {id,meterno,fname,lname,district,city,mobile,pincode};
                
                model = (DefaultTableModel) c_table.getModel();
                model.addRow(obj);
                count += 1;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        total_c.setText(String.valueOf(count));
    }
    public void searchCustomer()
    {
        String s_meterno = txt_search.getText();
        try
        {
            Connection con = DBconnection.getConnection();
            if(s_meterno.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Please number in search field");
            }
            else
            {
                String sql = "Select * from customer where meterno=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(s_meterno));
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {
                    int meterno = rs.getInt(1);
                    String fname = rs.getString(2);
                    String lname = rs.getString(3);
                    String district = rs.getString(4);
                    String city = rs.getString(5);
                    String mobile = rs.getString(6);
                    String pincode = rs.getString(7);
                    int id = rs.getInt(8);
                
                    Object[] obj = {id,meterno,fname,lname,district,city,mobile,pincode};
                
                    model = (DefaultTableModel) c_table.getModel();
                    model.addRow(obj);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Data Not Found !");
                }
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void clearTable()
    {
        model = (DefaultTableModel) c_table.getModel();
        model.setRowCount(0);
    }
    public void setUnit()
    {
        int meterno = Integer.parseInt(txt_unit_meter.getText());
        String year = (String) combo_year.getSelectedItem();
        String month = (String) combo_month.getSelectedItem();
        int unit = Integer.parseInt(txt_unit_unit.getText());
        try
        {
            Connection con = DBconnection.getConnection();
            String sql = "select * from customer where meterno=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, meterno);
            ResultSet rs = pst.executeQuery();
            if(rs.next())//this check a customer is craeted or not
            {
                String sql3 = "select * from unit_data where meterno=? and year=? and month=?";
                PreparedStatement pst3 = con.prepareStatement(sql3);
                pst3.setInt(1, meterno);
                pst3.setString(2, year);
                pst3.setString(3, month);
                ResultSet rs3 = pst3.executeQuery();
                if(rs3.next())//this check the record in unit data and if any data already  added or not
                {
                    JOptionPane.showMessageDialog(this, month+" "+year+" record already reveice");
                }
                else
                {
                    String sql2 = "insert into unit_data values(?,?,?,?)";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    pst2.setInt(1, meterno);
                    pst2.setString(2, year);
                    pst2.setString(3, month);
                    pst2.setInt(4, unit);
                    int rowCount = pst2.executeUpdate();
                    if(rowCount>0)//if all condition is clear then it insert new data
                    {
                        JOptionPane.showMessageDialog(this, "Unit Set Done");
                        txt_unit_meter.setText("");
                        combo_year.setSelectedItem("SELECT");
                        combo_month.setSelectedItem("SELECT");
                        txt_unit_unit.setText("");                      
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Faild to Set Unit");
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "There is NO Data of "+meterno+" Please add it first");
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
        bt_logout = new javax.swing.JLabel();
        bt_home = new javax.swing.JLabel();
        bt_paybill = new javax.swing.JLabel();
        bt_setunit = new javax.swing.JLabel();
        LeftPanel = new rojerusan.RSPanelImage();
        txt_meterno = new app.bolivia.swing.JCTextField();
        txt_fname = new app.bolivia.swing.JCTextField();
        txt_lname = new app.bolivia.swing.JCTextField();
        txt_district = new app.bolivia.swing.JCTextField();
        txt_city = new app.bolivia.swing.JCTextField();
        txt_mobile = new app.bolivia.swing.JCTextField();
        txt_pincode = new app.bolivia.swing.JCTextField();
        bt_add = new javax.swing.JLabel();
        bt_update = new javax.swing.JLabel();
        bt_delete = new javax.swing.JLabel();
        top_panel = new rojerusan.RSPanelImage();
        jLabel2 = new javax.swing.JLabel();
        txt_unit_meter = new app.bolivia.swing.JCTextField();
        txt_unit_unit = new app.bolivia.swing.JCTextField();
        bt_unitset = new javax.swing.JLabel();
        combo_year = new rojerusan.RSComboMetro();
        combo_month = new rojerusan.RSComboMetro();
        jScrollPane1 = new javax.swing.JScrollPane();
        c_table = new javax.swing.JTable();
        txt_search = new app.bolivia.swing.JCTextField();
        bt_search = new rojerusan.RSButtonMetro();
        bt_showall = new rojerusan.RSButtonMetro();
        bt_clear = new rojerusan.RSButtonMetro();
        total_c = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        MainPanel.setForeground(new java.awt.Color(78, 197, 38));
        MainPanel.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Icon/MainPage.png"))); // NOI18N
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bt_logout.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_logout.setForeground(new java.awt.Color(255, 255, 255));
        bt_logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_logout.setText("LOGOUT");
        bt_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_logoutMouseClicked(evt);
            }
        });
        MainPanel.add(bt_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, 105, 26));

        bt_home.setBackground(new java.awt.Color(255, 255, 255));
        bt_home.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_home.setForeground(new java.awt.Color(255, 255, 255));
        bt_home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_home.setText("HOME");
        MainPanel.add(bt_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 8, 108, 30));

        bt_paybill.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_paybill.setForeground(new java.awt.Color(255, 255, 255));
        bt_paybill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_paybill.setText("PAY-BILLS");
        bt_paybill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_paybillMouseClicked(evt);
            }
        });
        MainPanel.add(bt_paybill, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 8, 107, 30));

        bt_setunit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_setunit.setForeground(new java.awt.Color(255, 255, 255));
        bt_setunit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_setunit.setText("SET UNIT");
        bt_setunit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_setunitMouseClicked(evt);
            }
        });
        MainPanel.add(bt_setunit, new org.netbeans.lib.awtextra.AbsoluteConstraints(798, 8, 108, 30));

        LeftPanel.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Icon/ADD Panel.png"))); // NOI18N
        LeftPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LeftPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LeftPanelMouseExited(evt);
            }
        });
        LeftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_meterno.setBackground(new java.awt.Color(8, 130, 220));
        txt_meterno.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(255, 255, 255)));
        txt_meterno.setForeground(new java.awt.Color(255, 255, 255));
        txt_meterno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_meterno.setPhColor(new java.awt.Color(255, 255, 255));
        txt_meterno.setPlaceholder("Enter Meter No.");
        LeftPanel.add(txt_meterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 85, 170, 30));

        txt_fname.setBackground(new java.awt.Color(8, 130, 220));
        txt_fname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(255, 255, 255)));
        txt_fname.setForeground(new java.awt.Color(255, 255, 255));
        txt_fname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fname.setPhColor(new java.awt.Color(255, 255, 255));
        txt_fname.setPlaceholder("Enter First Name");
        LeftPanel.add(txt_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 170, 30));

        txt_lname.setBackground(new java.awt.Color(8, 130, 220));
        txt_lname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(255, 255, 255)));
        txt_lname.setForeground(new java.awt.Color(255, 255, 255));
        txt_lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_lname.setPhColor(new java.awt.Color(255, 255, 255));
        txt_lname.setPlaceholder("Enter Last Name");
        LeftPanel.add(txt_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 195, 170, 30));

        txt_district.setBackground(new java.awt.Color(8, 130, 220));
        txt_district.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(255, 255, 255)));
        txt_district.setForeground(new java.awt.Color(255, 255, 255));
        txt_district.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_district.setPhColor(new java.awt.Color(255, 255, 255));
        txt_district.setPlaceholder("Enter Your District");
        LeftPanel.add(txt_district, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 170, 30));

        txt_city.setBackground(new java.awt.Color(8, 130, 220));
        txt_city.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(255, 255, 255)));
        txt_city.setForeground(new java.awt.Color(255, 255, 255));
        txt_city.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_city.setPhColor(new java.awt.Color(255, 255, 255));
        txt_city.setPlaceholder("Enter Your City");
        LeftPanel.add(txt_city, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 305, 170, 30));

        txt_mobile.setBackground(new java.awt.Color(8, 130, 220));
        txt_mobile.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(255, 255, 255)));
        txt_mobile.setForeground(new java.awt.Color(255, 255, 255));
        txt_mobile.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_mobile.setPhColor(new java.awt.Color(255, 255, 255));
        txt_mobile.setPlaceholder("Enter Your Mobile Number");
        LeftPanel.add(txt_mobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 365, 170, 30));

        txt_pincode.setBackground(new java.awt.Color(8, 130, 220));
        txt_pincode.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(255, 255, 255)));
        txt_pincode.setForeground(new java.awt.Color(255, 255, 255));
        txt_pincode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pincode.setPhColor(new java.awt.Color(255, 255, 255));
        txt_pincode.setPlaceholder("Enter Your Pin Code");
        LeftPanel.add(txt_pincode, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 170, 30));

        bt_add.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_add.setForeground(new java.awt.Color(255, 255, 255));
        bt_add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_add.setText("ADD");
        bt_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addMouseClicked(evt);
            }
        });
        LeftPanel.add(bt_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 535, 78, 28));

        bt_update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_update.setForeground(new java.awt.Color(255, 255, 255));
        bt_update.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_update.setText("UPDATE");
        bt_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_updateMouseClicked(evt);
            }
        });
        LeftPanel.add(bt_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 535, 94, 28));

        bt_delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_delete.setForeground(new java.awt.Color(255, 255, 255));
        bt_delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_delete.setText("DELETE");
        bt_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_deleteMouseClicked(evt);
            }
        });
        LeftPanel.add(bt_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 535, 95, 28));

        MainPanel.add(LeftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 333, 600));

        top_panel.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Icon/Set Unit.png"))); // NOI18N
        top_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        top_panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 0, 30, 30));

        txt_unit_meter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 204, 0)));
        txt_unit_meter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_unit_meter.setPlaceholder("Enter Meter No.");
        top_panel.add(txt_unit_meter, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        txt_unit_unit.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 204, 0)));
        txt_unit_unit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_unit_unit.setPlaceholder("Enter Unit Consume");
        top_panel.add(txt_unit_unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        bt_unitset.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_unitset.setForeground(new java.awt.Color(255, 255, 255));
        bt_unitset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_unitset.setText("SET UNIT");
        bt_unitset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_unitsetMouseClicked(evt);
            }
        });
        top_panel.add(bt_unitset, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 295, 120, 40));

        combo_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
        top_panel.add(combo_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 125, -1, 25));

        combo_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT", "January", "February", "March", "April", "May", "June", "July", "Augest", "Octomber", "November", "December" }));
        top_panel.add(combo_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, 25));

        MainPanel.add(top_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 45, 475, 375));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        c_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Meter No", "First Name", "Last  Name", "District", "City", "Mobile No.", "Pin Code"
            }
        ));
        c_table.setGridColor(new java.awt.Color(255, 255, 255));
        c_table.setRowHeight(20);
        c_table.setSelectionBackground(new java.awt.Color(54, 214, 14));
        c_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(c_table);

        MainPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 750, -1));

        txt_search.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(0, 51, 204)));
        txt_search.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_search.setPlaceholder("Search By Meter No.");
        MainPanel.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, -1, 30));

        bt_search.setText("Search");
        bt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_searchActionPerformed(evt);
            }
        });
        MainPanel.add(bt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, -1, 30));

        bt_showall.setText("Show All");
        bt_showall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_showallActionPerformed(evt);
            }
        });
        MainPanel.add(bt_showall, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, 30));

        bt_clear.setText("Clear");
        bt_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_clearActionPerformed(evt);
            }
        });
        MainPanel.add(bt_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 120, -1, 30));

        total_c.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        total_c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_c.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(0, 0, 0)));
        MainPanel.add(total_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 60, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Total Customer");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));
        MainPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1200, 650));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_logoutMouseClicked
        System.exit(0);
    }//GEN-LAST:event_bt_logoutMouseClicked

    private void bt_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addMouseClicked
       if(checkBlank())
       {
           addCustomer();
           count = 0;
           clearTable();
           setCustomerTable();
       }
    }//GEN-LAST:event_bt_addMouseClicked

    private void bt_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateMouseClicked
        if(checkBlank())
       {
           updateCustomer();
           count = 0;
           clearTable();
           setCustomerTable();
       }
    }//GEN-LAST:event_bt_updateMouseClicked

    private void bt_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteMouseClicked
        if(txt_meterno.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter Meter Number");
        }
        else
        {
            deleteCustomer();
            count = 0;
            clearTable();
            setCustomerTable();
        }
    }//GEN-LAST:event_bt_deleteMouseClicked

    private void LeftPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LeftPanelMouseEntered
        
    }//GEN-LAST:event_LeftPanelMouseEntered

    private void LeftPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LeftPanelMouseExited
        
    }//GEN-LAST:event_LeftPanelMouseExited

    private void c_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_tableMouseClicked
        int rowNo = c_table.getSelectedRow();
        model = (DefaultTableModel) c_table.getModel();
        
        txt_meterno.setText(model.getValueAt(rowNo,1).toString());
        txt_fname.setText(model.getValueAt(rowNo,2).toString());
        txt_lname.setText(model.getValueAt(rowNo,3).toString());
        txt_district.setText(model.getValueAt(rowNo,4).toString());
        txt_city.setText(model.getValueAt(rowNo,5).toString());
        txt_mobile.setText(model.getValueAt(rowNo,6).toString());
        txt_pincode.setText(model.getValueAt(rowNo,7).toString());
    }//GEN-LAST:event_c_tableMouseClicked

    private void bt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_searchActionPerformed
        clearTable();
        searchCustomer();
    }//GEN-LAST:event_bt_searchActionPerformed

    private void bt_showallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_showallActionPerformed
        clearTable();
        count = 0;
        setCustomerTable();
    }//GEN-LAST:event_bt_showallActionPerformed

    private void bt_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_clearActionPerformed
        setBlank();
        txt_search.setText("");
    }//GEN-LAST:event_bt_clearActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        jScrollPane1.setVisible(true);
        top_panel.setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void bt_setunitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_setunitMouseClicked
        jScrollPane1.setVisible(false);
        top_panel.setVisible(true);
    }//GEN-LAST:event_bt_setunitMouseClicked

    private void bt_unitsetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_unitsetMouseClicked
        // TODO add your handling code here:
        setUnit();
    }//GEN-LAST:event_bt_unitsetMouseClicked

    private void bt_paybillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_paybillMouseClicked
        PayBill pay = new PayBill();
        pay.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bt_paybillMouseClicked

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSPanelImage LeftPanel;
    private rojerusan.RSPanelImage MainPanel;
    private javax.swing.JLabel bt_add;
    private rojerusan.RSButtonMetro bt_clear;
    private javax.swing.JLabel bt_delete;
    private javax.swing.JLabel bt_home;
    private javax.swing.JLabel bt_logout;
    private javax.swing.JLabel bt_paybill;
    private rojerusan.RSButtonMetro bt_search;
    private javax.swing.JLabel bt_setunit;
    private rojerusan.RSButtonMetro bt_showall;
    private javax.swing.JLabel bt_unitset;
    private javax.swing.JLabel bt_update;
    private javax.swing.JTable c_table;
    private rojerusan.RSComboMetro combo_month;
    private rojerusan.RSComboMetro combo_year;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSPanelImage top_panel;
    private javax.swing.JLabel total_c;
    private app.bolivia.swing.JCTextField txt_city;
    private app.bolivia.swing.JCTextField txt_district;
    private app.bolivia.swing.JCTextField txt_fname;
    private app.bolivia.swing.JCTextField txt_lname;
    private app.bolivia.swing.JCTextField txt_meterno;
    private app.bolivia.swing.JCTextField txt_mobile;
    private app.bolivia.swing.JCTextField txt_pincode;
    private app.bolivia.swing.JCTextField txt_search;
    private app.bolivia.swing.JCTextField txt_unit_meter;
    private app.bolivia.swing.JCTextField txt_unit_unit;
    // End of variables declaration//GEN-END:variables
}
