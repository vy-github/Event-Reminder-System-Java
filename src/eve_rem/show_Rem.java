/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eve_rem;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Vaibhav
 */
public class show_Rem extends javax.swing.JFrame
{
    Connection conn=null;
    PreparedStatement pstmtse =null;
    PreparedStatement pstmtde =null;
    PreparedStatement pstmtup =null;
    ResultSet rs = null;
    int ridp1=0,ridp2=0,ridp3=0,ridp4=0,ridp5=0,rc=0;

    /**
     * Creates new form show_Rem
     */
    public show_Rem() {
        initComponents();
        
        setBounds(410, 60, 565, 595);
        txtDesc1.setEditable(false);
        txtDesc2.setEditable(false);
        txtDesc3.setEditable(false);
        txtDesc4.setEditable(false);
        txtDesc5.setEditable(false);

        txtDesc1.setBorder(null);
        txtDesc1.setBackground(new Color(0,0,0,0));
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportBorder(null);

        txtDesc2.setBorder(null);
        txtDesc2.setBackground(new Color(0,0,0,0));
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setBorder(null);
        jScrollPane2.setViewportBorder(null);

        txtDesc3.setBorder(null);
        txtDesc3.setBackground(new Color(0,0,0,0));
        jScrollPane3.setOpaque(false);
        jScrollPane3.getViewport().setOpaque(false);
        jScrollPane3.setBorder(null);
        jScrollPane3.setViewportBorder(null);

        txtDesc4.setBorder(null);
        txtDesc4.setBackground(new Color(0,0,0,0));
        jScrollPane4.setOpaque(false);
        jScrollPane4.getViewport().setOpaque(false);
        jScrollPane4.setBorder(null);
        jScrollPane4.setViewportBorder(null);

        txtDesc5.setBorder(null);
        txtDesc5.setBackground(new Color(0,0,0,0));
        jScrollPane5.setOpaque(false);
        jScrollPane5.getViewport().setOpaque(false);
        jScrollPane5.setBorder(null);
        jScrollPane5.setViewportBorder(null);        
        
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///eve_remi","root","Vaibhav159");
            pstmtse = conn.prepareStatement("Select * from eve_remi.set_remi");
            rs = pstmtse.executeQuery();
            load_reminder();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            jPanel1.setVisible(false);
            jPanel2.setVisible(false);
            jPanel3.setVisible(false);
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);
            btnNext.setVisible(false);
            JOptionPane.showMessageDialog(null, "You don't have any Reminder","Show Reminder", JOptionPane.ERROR_MESSAGE);
            int res = JOptionPane.showConfirmDialog(this, "Do you want to set?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.YES_OPTION)
            {
                new set_Task().setVisible(true);
                dispos1();
            }
            else if(res == JOptionPane.NO_OPTION)
            {
                dispos1();
            }
        }
    }
    
    public void load_reminder() throws SQLException
    {   
        if (rs.next())
        {
            rs.first();
            jPanel1.setVisible(true);
            btnNext.setVisible(true);
            lblTitle1.setText(rs.getString("db_Title"));

            Date dat = new Date();
            DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
            DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
            String date1 = dfdt.format(dat);
            String dt = dfdt.format(rs.getDate("db_dteChooser"));
            Calendar cal1 = new GregorianCalendar();
            cal1.add(Calendar.DAY_OF_MONTH, 1);
            String date2 = dfdt.format(cal1.getTime());
            String day=null;
            if (dt == null ? date1 == null : dt.equals(date1))
            {
                day = "Today";
            }
            else if (dt == null ? date2 == null : dt.equals(date2))
            {
                day = "Tomorrow";
            }
            else
            {
                day = dfdtday.format(rs.getDate("db_dteChooser"));
            }
            lblTime1.setText(day+" on "+dt);

            txtDesc1.setText(rs.getString("db_desc"));
            ridp1 = rs.getInt("id");  
        }
        else
        {
            lblTitle1.setText("");
            lblTime1.setText("");
            txtDesc1.setText("");
            jPanel1.setVisible(false);
            btnNext.setVisible(false);
        }
        
        if (rs.next())
        {
            jPanel2.setVisible(true);
            btnNext.setVisible(true);
            lblTitle2.setText(rs.getString("db_Title"));

            Date dat = new Date();
            DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
            DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
            String date1 = dfdt.format(dat);
            String dt = dfdt.format(rs.getDate("db_dteChooser"));
            Calendar cal1 = new GregorianCalendar();
            cal1.add(Calendar.DAY_OF_MONTH, 1);
            String date2 = dfdt.format(cal1.getTime());
            String day=null;
            if (dt == null ? date1 == null : dt.equals(date1))
            {
                day = "Today";
            }
            else if (dt == null ? date2 == null : dt.equals(date2))
            {
                day = "Tomorrow";
            }
            else
            {
                day = dfdtday.format(rs.getDate("db_dteChooser"));
            }
            lblTime2.setText(day+" on "+dt);

            txtDesc2.setText(rs.getString("db_desc"));
            ridp2 = rs.getInt("id");
        }
        else
        {
            lblTitle2.setText("");
            lblTime2.setText("");
            txtDesc2.setText("");
            jPanel2.setVisible(false);
            btnNext.setVisible(false);
        }
        
        if (rs.next())
        {
            jPanel3.setVisible(true);
            btnNext.setVisible(true);
            lblTitle3.setText(rs.getString("db_Title"));
            Date dat = new Date();
            DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
            DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
            String date1 = dfdt.format(dat);
            String dt = dfdt.format(rs.getDate("db_dteChooser"));
            Calendar cal1 = new GregorianCalendar();
            cal1.add(Calendar.DAY_OF_MONTH, 1);
            String date2 = dfdt.format(cal1.getTime());
            String day=null;
            if (dt == null ? date1 == null : dt.equals(date1))
            {
                day = "Today";
            }
            else if (dt == null ? date2 == null : dt.equals(date2))
            {
                day = "Tomorrow";
            }
            else
            {
                day = dfdtday.format(rs.getDate("db_dteChooser"));
            }
            lblTime3.setText(day+" on "+dt);
            txtDesc3.setText(rs.getString("db_desc"));
            ridp3 = rs.getInt("id");
        }
        else
        {
            lblTitle3.setText("");
            lblTime3.setText("");
            txtDesc3.setText("");
            jPanel3.setVisible(false);
            btnNext.setVisible(false);
        }
        
        if (rs.next())
        {
            jPanel4.setVisible(true);
            btnNext.setVisible(true);
            lblTitle4.setText(rs.getString("db_Title"));
            Date dat = new Date();
            DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
            DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
            String date1 = dfdt.format(dat);
            String dt = dfdt.format(rs.getDate("db_dteChooser"));
            Calendar cal1 = new GregorianCalendar();
            cal1.add(Calendar.DAY_OF_MONTH, 1);
            String date2 = dfdt.format(cal1.getTime());
            String day=null;
            if (dt == null ? date1 == null : dt.equals(date1))
            {
                day = "Today";
            }
            else if (dt == null ? date2 == null : dt.equals(date2))
            {
                day = "Tomorrow";
            }
            else
            {
                day = dfdtday.format(rs.getDate("db_dteChooser"));
            }
            lblTime4.setText(day+" on "+dt);
            txtDesc4.setText(rs.getString("db_desc"));
            ridp4 = rs.getInt("id");
        }
        else
        {
            lblTitle4.setText("");
            lblTime4.setText("");
            txtDesc4.setText("");
            jPanel4.setVisible(false);
            btnNext.setVisible(false);
        }
        
        if (rs.next())
        {
            jPanel5.setVisible(true);
            btnNext.setVisible(true);
            lblTitle5.setText(rs.getString("db_Title"));
            Date dat = new Date();
            DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
            DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
            String date1 = dfdt.format(dat);
            String dt = dfdt.format(rs.getDate("db_dteChooser"));
            Calendar cal1 = new GregorianCalendar();
            cal1.add(Calendar.DAY_OF_MONTH, 1);
            String date2 = dfdt.format(cal1.getTime());
            String day=null;
            if (dt == null ? date1 == null : dt.equals(date1))
            {
                day = "Today";
            }
            else if (dt == null ? date2 == null : dt.equals(date2))
            {
                day = "Tomorrow";
            }
            else
            {
                day = dfdtday.format(rs.getDate("db_dteChooser"));
            }
            lblTime5.setText(day+" on "+dt);
            txtDesc5.setText(rs.getString("db_desc"));
            ridp5 = rs.getInt("id");
        }
        else
        {
            lblTitle5.setText("");
            lblTime5.setText("");
            txtDesc5.setText("");
            jPanel5.setVisible(false);
            btnNext.setVisible(false);
        }
    }
    
    public void dispos1() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {  
                timer.cancel();
                dispose();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1);
    }
    
    
    public int row_count() throws SQLException
    {
        int count = 0;
        rs.beforeFirst();
        while(rs.next())
            count++;
        return count;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNext = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblTime1 = new javax.swing.JLabel();
        lblTitle1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc1 = new javax.swing.JTextArea();
        lblEdit1 = new javax.swing.JLabel();
        lblDelete1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTime2 = new javax.swing.JLabel();
        lblTitle2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDesc2 = new javax.swing.JTextArea();
        lblEdit2 = new javax.swing.JLabel();
        lblDelete2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTime3 = new javax.swing.JLabel();
        lblTitle3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDesc3 = new javax.swing.JTextArea();
        lblEdit3 = new javax.swing.JLabel();
        lblDelete3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTime4 = new javax.swing.JLabel();
        lblTitle4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDesc4 = new javax.swing.JTextArea();
        lblEdit4 = new javax.swing.JLabel();
        lblDelete4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblTime5 = new javax.swing.JLabel();
        lblTitle5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDesc5 = new javax.swing.JTextArea();
        lblEdit5 = new javax.swing.JLabel();
        lblDelete5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuShow = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(550, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNext.setText("Next");
        btnNext.setToolTipText("Next Reminders");
        btnNext.setAlignmentX(300);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        getContentPane().add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 485, 160, 40));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lblTime1.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        lblTime1.setText("ASasASas");

        lblTitle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle1.setText(" ");
        lblTitle1.setToolTipText("Title");

        txtDesc1.setBackground(new java.awt.Color(234, 234, 234));
        txtDesc1.setColumns(1);
        txtDesc1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDesc1.setLineWrap(true);
        txtDesc1.setRows(1);
        txtDesc1.setTabSize(0);
        txtDesc1.setToolTipText("Description");
        txtDesc1.setMinimumSize(new java.awt.Dimension(1, 1));
        txtDesc1.setName(""); // NOI18N
        txtDesc1.setPreferredSize(new java.awt.Dimension(108, 20));
        jScrollPane1.setViewportView(txtDesc1);

        lblEdit1.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Edit3.png")); // NOI18N
        lblEdit1.setToolTipText("Edit Reminder");
        lblEdit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEdit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEdit1MouseClicked(evt);
            }
        });

        lblDelete1.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Delete3.png")); // NOI18N
        lblDelete1.setToolTipText("Delete Reminder");
        lblDelete1.setAlignmentX(20);
        lblDelete1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDelete1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDelete1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTime1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(lblEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle1)
                    .addComponent(lblTime1))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 540, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lblTime2.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        lblTime2.setText("ASasASas");

        lblTitle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle2.setText(" ");
        lblTitle2.setToolTipText("Title");

        txtDesc2.setBackground(new java.awt.Color(234, 234, 234));
        txtDesc2.setColumns(1);
        txtDesc2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDesc2.setLineWrap(true);
        txtDesc2.setRows(1);
        txtDesc2.setTabSize(0);
        txtDesc2.setToolTipText("Description");
        txtDesc2.setMinimumSize(new java.awt.Dimension(1, 1));
        txtDesc2.setName(""); // NOI18N
        txtDesc2.setPreferredSize(new java.awt.Dimension(108, 20));
        jScrollPane2.setViewportView(txtDesc2);

        lblEdit2.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Edit3.png")); // NOI18N
        lblEdit2.setToolTipText("Edit Reminder");
        lblEdit2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEdit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEdit2MouseClicked(evt);
            }
        });

        lblDelete2.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Delete3.png")); // NOI18N
        lblDelete2.setToolTipText("Delete Reminder");
        lblDelete2.setAlignmentX(20);
        lblDelete2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDelete2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDelete2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTime2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(lblEdit2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle2)
                    .addComponent(lblTime2))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEdit2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 540, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lblTime3.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        lblTime3.setText("ASasASas");

        lblTitle3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle3.setText(" ");
        lblTitle3.setToolTipText("Title");

        txtDesc3.setBackground(new java.awt.Color(234, 234, 234));
        txtDesc3.setColumns(1);
        txtDesc3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDesc3.setLineWrap(true);
        txtDesc3.setRows(1);
        txtDesc3.setTabSize(0);
        txtDesc3.setToolTipText("Description");
        txtDesc3.setMinimumSize(new java.awt.Dimension(1, 1));
        txtDesc3.setName(""); // NOI18N
        txtDesc3.setPreferredSize(new java.awt.Dimension(108, 20));
        jScrollPane3.setViewportView(txtDesc3);

        lblEdit3.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Edit3.png")); // NOI18N
        lblEdit3.setToolTipText("Edit Reminder");
        lblEdit3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEdit3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEdit3MouseClicked(evt);
            }
        });

        lblDelete3.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Delete3.png")); // NOI18N
        lblDelete3.setToolTipText("Delete Reminder");
        lblDelete3.setAlignmentX(20);
        lblDelete3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDelete3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDelete3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTime3)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(lblEdit3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDelete3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle3)
                    .addComponent(lblTime3))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEdit3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDelete3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 540, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lblTime4.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        lblTime4.setText("ASasASas");

        lblTitle4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle4.setText(" ");
        lblTitle4.setToolTipText("Title");

        txtDesc4.setBackground(new java.awt.Color(234, 234, 234));
        txtDesc4.setColumns(1);
        txtDesc4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDesc4.setLineWrap(true);
        txtDesc4.setRows(1);
        txtDesc4.setTabSize(0);
        txtDesc4.setToolTipText("Description");
        txtDesc4.setMinimumSize(new java.awt.Dimension(1, 1));
        txtDesc4.setName(""); // NOI18N
        txtDesc4.setPreferredSize(new java.awt.Dimension(108, 20));
        jScrollPane4.setViewportView(txtDesc4);

        lblEdit4.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Edit3.png")); // NOI18N
        lblEdit4.setToolTipText("Edit Reminder");
        lblEdit4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEdit4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEdit4MouseClicked(evt);
            }
        });

        lblDelete4.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Delete3.png")); // NOI18N
        lblDelete4.setToolTipText("Delete Reminder");
        lblDelete4.setAlignmentX(20);
        lblDelete4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDelete4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDelete4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTime4)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(lblEdit4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDelete4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle4)
                    .addComponent(lblTime4))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEdit4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDelete4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 540, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lblTime5.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        lblTime5.setText("ASasASas");

        lblTitle5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle5.setText(" ");
        lblTitle5.setToolTipText("Title");

        txtDesc5.setBackground(new java.awt.Color(234, 234, 234));
        txtDesc5.setColumns(1);
        txtDesc5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDesc5.setLineWrap(true);
        txtDesc5.setRows(1);
        txtDesc5.setTabSize(0);
        txtDesc5.setToolTipText("Description");
        txtDesc5.setMinimumSize(new java.awt.Dimension(1, 1));
        txtDesc5.setName(""); // NOI18N
        txtDesc5.setPreferredSize(new java.awt.Dimension(108, 20));
        jScrollPane5.setViewportView(txtDesc5);

        lblEdit5.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Edit3.png")); // NOI18N
        lblEdit5.setToolTipText("Edit Reminder");
        lblEdit5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEdit5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEdit5MouseClicked(evt);
            }
        });

        lblDelete5.setIcon(new javax.swing.ImageIcon("D:\\MCA\\Icon\\New folder\\Delete3.png")); // NOI18N
        lblDelete5.setToolTipText("Delete Reminder");
        lblDelete5.setAlignmentX(20);
        lblDelete5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDelete5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDelete5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblTitle5, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTime5)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(lblEdit5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDelete5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle5)
                    .addComponent(lblTime5))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEdit5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDelete5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 540, -1));

        mnuShow.setText("Show Reminder");
        mnuShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuShowMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuShow);

        mnuExit.setText("Exit");
        mnuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuExitMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuExit);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
       if (btnNext.getText().equals("Go First"))
        {
            btnNext.setText("Next");
            try {
                rs.first();
                load_reminder();
            } catch (SQLException ex) {
                Logger.getLogger(show_Rem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
        try {
            if (rs.next())
            {
                jPanel1.setVisible(true);
                lblTitle1.setText(rs.getString("db_Title"));
                Date dat = new Date();
                DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
                String date1 = dfdt.format(dat);
                String dt = dfdt.format(rs.getDate("db_dteChooser"));
                Calendar cal1 = new GregorianCalendar();
                cal1.add(Calendar.DAY_OF_MONTH, 1);
                String date2 = dfdt.format(cal1.getTime());
                String day=null;
                if (dt == null ? date1 == null : dt.equals(date1))
                {
                    day = "Today";
                }
                else if (dt == null ? date2 == null : dt.equals(date2))
                {
                    day = "Tomorrow";
                }
                else
                {
                    day = dfdtday.format(rs.getDate("db_dteChooser"));
                }
                lblTime1.setText(day+" on "+dt);
                txtDesc1.setText(rs.getString("db_desc"));
                ridp1 = rs.getInt("id");
            }
            else
            {
                lblTitle1.setText("");
                lblTime1.setText("");
                txtDesc1.setText("");
                jPanel1.setVisible(false);
                btnNext.setText("Go First");
            }
           
            if (rs.next())
            {
                jPanel2.setVisible(true);
                lblTitle2.setText(rs.getString("db_Title"));
                Date dat = new Date();
                DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
                String date1 = dfdt.format(dat);
                String dt = dfdt.format(rs.getDate("db_dteChooser"));
                Calendar cal1 = new GregorianCalendar();
                cal1.add(Calendar.DAY_OF_MONTH, 1);
                String date2 = dfdt.format(cal1.getTime());
                String day=null;
                if (dt == null ? date1 == null : dt.equals(date1))
                {
                    day = "Today";
                }
                else if (dt == null ? date2 == null : dt.equals(date2))
                {
                    day = "Tomorrow";
                }
                else
                {
                    day = dfdtday.format(rs.getDate("db_dteChooser"));
                }
                lblTime2.setText(day+" on "+dt);
                txtDesc2.setText(rs.getString("db_desc"));
                ridp2 = rs.getInt("id");
            }
            else
            {
                lblTitle2.setText("");
                lblTime2.setText("");
                txtDesc2.setText("");
                jPanel2.setVisible(false);
                btnNext.setText("Go First");
            }
            
            if (rs.next())
            {
                jPanel3.setVisible(true);
                lblTitle3.setText(rs.getString("db_Title"));
                Date dat = new Date();
                DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
                String date1 = dfdt.format(dat);
                String dt = dfdt.format(rs.getDate("db_dteChooser"));
                Calendar cal1 = new GregorianCalendar();
                cal1.add(Calendar.DAY_OF_MONTH, 1);
                String date2 = dfdt.format(cal1.getTime());
                String day=null;
                if (dt == null ? date1 == null : dt.equals(date1))
                {
                    day = "Today";
                }
                else if (dt == null ? date2 == null : dt.equals(date2))
                {
                    day = "Tomorrow";
                }
                else
                {
                    day = dfdtday.format(rs.getDate("db_dteChooser"));
                }
                lblTime3.setText(day+" on "+dt);
                txtDesc3.setText(rs.getString("db_desc"));
                ridp3 = rs.getInt("id");
            }
            else
            {
                lblTitle3.setText("");
                lblTime3.setText("");
                txtDesc3.setText("");
                jPanel3.setVisible(false);
                btnNext.setText("Go First");
            }
            
            if (rs.next())
            {
                jPanel4.setVisible(true);
                lblTitle4.setText(rs.getString("db_Title"));
                Date dat = new Date();
                DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
                String date1 = dfdt.format(dat);
                String dt = dfdt.format(rs.getDate("db_dteChooser"));
                Calendar cal1 = new GregorianCalendar();
                cal1.add(Calendar.DAY_OF_MONTH, 1);
                String date2 = dfdt.format(cal1.getTime());
                String day=null;
                if (dt == null ? date1 == null : dt.equals(date1))
                {
                    day = "Today";
                }
                else if (dt == null ? date2 == null : dt.equals(date2))
                {
                    day = "Tomorrow";
                }
                else
                {
                    day = dfdtday.format(rs.getDate("db_dteChooser"));
                }
                lblTime4.setText(day+" on "+dt);
                txtDesc4.setText(rs.getString("db_desc"));
                ridp4 = rs.getInt("id");
            }
            else
            {
                lblTitle4.setText("");
                lblTime4.setText("");
                txtDesc4.setText("");
                jPanel4.setVisible(false);
                btnNext.setText("Go First");
            }
            
            if (rs.next())
            {
                jPanel5.setVisible(true);
                lblTitle5.setText(rs.getString("db_Title"));
                Date dat = new Date();
                DateFormat dfdt = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat dfdtday = new SimpleDateFormat("EEEEEEEE");
                String date1 = dfdt.format(dat);
                String dt = dfdt.format(rs.getDate("db_dteChooser"));
                Calendar cal1 = new GregorianCalendar();
                cal1.add(Calendar.DAY_OF_MONTH, 1);
                String date2 = dfdt.format(cal1.getTime());
                String day=null;
                if (dt == null ? date1 == null : dt.equals(date1))
                {
                    day = "Today";
                }
                else if (dt == null ? date2 == null : dt.equals(date2))
                {
                    day = "Tomorrow";
                }
                else
                {
                    day = dfdtday.format(rs.getDate("db_dteChooser"));
                }
                lblTime5.setText(day+" on "+dt);
                txtDesc5.setText(rs.getString("db_desc"));
                ridp5 = rs.getInt("id");
            }
            else
            {
                lblTitle5.setText("");
                lblTime5.setText("");
                txtDesc5.setText("");
                jPanel5.setVisible(false);
                btnNext.setText("Go First");
            }
        } catch (SQLException ex) {
            Logger.getLogger(show_Rem.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void lblDelete1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDelete1MouseClicked
        try
        {
            pstmtde = conn.prepareStatement("Delete from eve_remi.set_remi where id=?");
            int res = JOptionPane.showConfirmDialog(this, "Are you sure to delete this reminder?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.YES_OPTION)
            {
                pstmtde.setInt(1, ridp1);
                pstmtde.executeUpdate();
                pstmtse = conn.prepareStatement("Select * from eve_remi.set_remi");
                rs = pstmtse.executeQuery();
                rc = row_count();
                if(rc==0)
                {
                    this.setVisible(false);
                    JOptionPane.showMessageDialog(null, "You don't have any Reminder","Show Reminder", JOptionPane.ERROR_MESSAGE);
                    int res1 = JOptionPane.showConfirmDialog(this, "Do you want to set?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(res1 == JOptionPane.YES_OPTION)
                    {
                        new set_Task().setVisible(true);
                        dispos1();
                    }
                    else if(res1 == JOptionPane.NO_OPTION)
                    {
                        //System.out.println("dispos");
                        dispos1();
                    }
                }                
                else
                {
                    rs.beforeFirst();
                    load_reminder();
                    btnNext.setText("Next");
                }
            }
            else if(res == JOptionPane.NO_OPTION)
            {
                
            }
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblDelete1MouseClicked

    private void lblEdit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEdit1MouseClicked
        String txtTitle,txtDesc,dbhh = "",dbmm = "",dbeid,dbsms;
        int dum_dbhh,dum_dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat;
        Date dbdate;
        try
        {
            
            pstmtup = conn.prepareStatement("Select * from eve_remi.set_remi where id=?");
            pstmtup.setInt(1, ridp1);
            rs = pstmtup.executeQuery();
            rs.next();
            txtTitle=rs.getString(2);
            dbdate = rs.getDate(3);
            dbhh = rs.getString(4);
            dum_dbhh = Integer.parseInt(dbhh);
            if(dum_dbhh<10)
            {dbhh = "0"+rs.getString(4);}
            else
            {dbhh = rs.getString(4);}
            dbmm = rs.getString(5);
            dum_dbmm = Integer.parseInt(dbmm);
            if(dum_dbmm<10)
            {dbmm = "0"+rs.getString(5);}
            else
            {dbmm = rs.getString(5);}
            
            dbonce = rs.getInt(6);
            dbdaily = rs.getInt(7);
            dbweekly = rs.getInt(8);
            dbmonthly = rs.getInt(9);
            dbannually = rs.getInt(10);
            dbsun = rs.getInt(11);
            dbmon = rs.getInt(12);
            dbtue = rs.getInt(13);
            dbwed = rs.getInt(14);
            dbthu = rs.getInt(15);
            dbfri = rs.getInt(16);
            dbsat = rs.getInt(17);
            txtDesc=rs.getString(18);
            dbeid = rs.getString(19);
            dbsms = rs.getString(20);

            new set_Task(ridp1,txtTitle,dbdate,dbhh,dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat,txtDesc,dbeid,dbsms).setVisible(true);
            
            this.dispose();
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblEdit1MouseClicked

    private void lblEdit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEdit2MouseClicked
        String txtTitle,txtDesc,dbhh = null,dbmm = null,dbeid,dbsms;
        int dum_dbhh,dum_dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat;
        Date dbdate;
        try
        {
            pstmtup = conn.prepareStatement("Select * from eve_remi.set_remi where id=?");
            pstmtup.setInt(1, ridp2);
            rs = pstmtup.executeQuery();
            rs.next();
            
            txtTitle=rs.getString(2);
            dbdate = rs.getDate(3);
            dbhh = rs.getString(4);
            dum_dbhh = Integer.parseInt(dbhh);
            if(dum_dbhh<10)
            {dbhh = "0"+rs.getString(4);}
            else
            {dbhh = rs.getString(4);}
            dbmm = rs.getString(5);
            dum_dbmm = Integer.parseInt(dbmm);
            if(dum_dbmm<10)
            {dbmm = "0"+rs.getString(5);}
            else
            {dbmm = rs.getString(5);}
            dbonce = rs.getInt(6);
            dbdaily = rs.getInt(7);
            dbweekly = rs.getInt(8);
            dbmonthly = rs.getInt(9);
            dbannually = rs.getInt(10);
            dbsun = rs.getInt(11);
            dbmon = rs.getInt(12);
            dbtue = rs.getInt(13);
            dbwed = rs.getInt(14);
            dbthu = rs.getInt(15);
            dbfri = rs.getInt(16);
            dbsat = rs.getInt(17);
            txtDesc=rs.getString(18);
            dbeid = rs.getString(19);
            dbsms = rs.getString(20);
            
            new set_Task(ridp2,txtTitle,dbdate,dbhh,dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat,txtDesc,dbeid,dbsms).setVisible(true);
            
            this.dispose();
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblEdit2MouseClicked

    private void lblDelete2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDelete2MouseClicked
        try
        {    
            pstmtde = conn.prepareStatement("Delete from eve_remi.set_remi where id=?");
            int res = JOptionPane.showConfirmDialog(this, "Are you sure to delete this reminder?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.YES_OPTION)
            {
                pstmtde.setInt(1, ridp2);
                pstmtde.executeUpdate();
                pstmtse = conn.prepareStatement("Select * from eve_remi.set_remi");
                rs = pstmtse.executeQuery();
                rs.beforeFirst();
                load_reminder();   
                btnNext.setText("Next");
            }
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblDelete2MouseClicked

    private void lblEdit3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEdit3MouseClicked
        String txtTitle,txtDesc,dbhh = null,dbmm = null,dbeid,dbsms;
        int dum_dbhh,dum_dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat;
        Date dbdate;
        try
        {
            pstmtup = conn.prepareStatement("Select * from eve_remi.set_remi where id=?");
            pstmtup.setInt(1, ridp3);
            rs = pstmtup.executeQuery();
            rs.next();
            txtTitle=rs.getString(2);
            dbdate = rs.getDate(3);
            dbhh = rs.getString(4);
            dum_dbhh = Integer.parseInt(dbhh);
            if(dum_dbhh<10)
            {dbhh = "0"+rs.getString(4);}
            else
            {dbhh = rs.getString(4);}
            dbmm = rs.getString(5);
            dum_dbmm = Integer.parseInt(dbmm);
            if(dum_dbmm<10)
            {dbmm = "0"+rs.getString(5);}
            else
            {dbmm = rs.getString(5);}
            dbonce = rs.getInt(6);
            dbdaily = rs.getInt(7);
            dbweekly = rs.getInt(8);
            dbmonthly = rs.getInt(9);
            dbannually = rs.getInt(10);
            dbsun = rs.getInt(11);
            dbmon = rs.getInt(12);
            dbtue = rs.getInt(13);
            dbwed = rs.getInt(14);
            dbthu = rs.getInt(15);
            dbfri = rs.getInt(16);
            dbsat = rs.getInt(17);
            txtDesc=rs.getString(18);
            dbeid = rs.getString(19);
            dbsms = rs.getString(20);
            
            new set_Task(ridp3,txtTitle,dbdate,dbhh,dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat,txtDesc,dbeid,dbsms).setVisible(true);
            
            this.dispose();
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblEdit3MouseClicked

    private void lblDelete3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDelete3MouseClicked
        try
        {    
            pstmtde = conn.prepareStatement("Delete from eve_remi.set_remi where id=?");
            int res = JOptionPane.showConfirmDialog(this, "Are you sure to delete this reminder?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.YES_OPTION)
            {
                pstmtde.setInt(1, ridp3);
                pstmtde.executeUpdate();
                pstmtse = conn.prepareStatement("Select * from eve_remi.set_remi");
                rs = pstmtse.executeQuery();
                rs.beforeFirst();
                load_reminder();
                btnNext.setText("Next");
            }
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblDelete3MouseClicked

    private void lblEdit4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEdit4MouseClicked
        String txtTitle,txtDesc,dbhh = null,dbmm = null,dbeid,dbsms;
        int dum_dbhh,dum_dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat;
        Date dbdate;
        try
        {
            pstmtup = conn.prepareStatement("Select * from eve_remi.set_remi where id=?");
            pstmtup.setInt(1, ridp4);
            rs = pstmtup.executeQuery();
            rs.next();
            txtTitle=rs.getString(2);
            dbdate = rs.getDate(3);
            dbhh = rs.getString(4);
            dum_dbhh = Integer.parseInt(dbhh);
            if(dum_dbhh<10)
            {dbhh = "0"+rs.getString(4);}
            else
            {dbhh = rs.getString(4);}
            dbmm = rs.getString(5);
            dum_dbmm = Integer.parseInt(dbmm);
            if(dum_dbmm<10)
            {dbmm = "0"+rs.getString(5);}
            else
            {dbmm = rs.getString(5);}
            dbonce = rs.getInt(6);
            dbdaily = rs.getInt(7);
            dbweekly = rs.getInt(8);
            dbmonthly = rs.getInt(9);
            dbannually = rs.getInt(10);
            dbsun = rs.getInt(11);
            dbmon = rs.getInt(12);
            dbtue = rs.getInt(13);
            dbwed = rs.getInt(14);
            dbthu = rs.getInt(15);
            dbfri = rs.getInt(16);
            dbsat = rs.getInt(17);
            txtDesc=rs.getString(18);
            dbeid = rs.getString(19);
            dbsms = rs.getString(20);
            
            new set_Task(ridp4,txtTitle,dbdate,dbhh,dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat,txtDesc,dbeid,dbsms).setVisible(true);
            
            this.dispose();
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblEdit4MouseClicked

    private void lblDelete4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDelete4MouseClicked
        try
        {    
            pstmtde = conn.prepareStatement("Delete from eve_remi.set_remi where id=?");
            int res = JOptionPane.showConfirmDialog(this, "Are you sure to delete this reminder?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.YES_OPTION)
            {
                pstmtde.setInt(1, ridp4);
                pstmtde.executeUpdate();
                pstmtse = conn.prepareStatement("Select * from eve_remi.set_remi");
                rs = pstmtse.executeQuery();
                rs.beforeFirst();
                load_reminder();
                btnNext.setText("Next");
            }
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblDelete4MouseClicked

    private void lblEdit5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEdit5MouseClicked
        String txtTitle,txtDesc,dbhh = null,dbmm = null,dbeid,dbsms;
        int dum_dbhh,dum_dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat;
        Date dbdate;
        try
        {
            pstmtup = conn.prepareStatement("Select * from eve_remi.set_remi where id=?");
            pstmtup.setInt(1, ridp5);
            rs = pstmtup.executeQuery();
            rs.next();
            txtTitle=rs.getString(2);
            dbdate = rs.getDate(3);
            dbhh = rs.getString(4);
            dum_dbhh = Integer.parseInt(dbhh);
            if(dum_dbhh<10)
            {dbhh = "0"+rs.getString(4);}
            else
            {dbhh = rs.getString(4);}
            dbmm = rs.getString(5);
            dum_dbmm = Integer.parseInt(dbmm);
            if(dum_dbmm<10)
            {dbmm = "0"+rs.getString(5);}
            else
            {dbmm = rs.getString(5);}
            dbonce = rs.getInt(6);
            dbdaily = rs.getInt(7);
            dbweekly = rs.getInt(8);
            dbmonthly = rs.getInt(9);
            dbannually = rs.getInt(10);
            dbsun = rs.getInt(11);
            dbmon = rs.getInt(12);
            dbtue = rs.getInt(13);
            dbwed = rs.getInt(14);
            dbthu = rs.getInt(15);
            dbfri = rs.getInt(16);
            dbsat = rs.getInt(17);
            txtDesc=rs.getString(18);
            dbeid = rs.getString(19);
            dbsms = rs.getString(20);
            
            new set_Task(ridp5,txtTitle,dbdate,dbhh,dbmm,dbonce,dbdaily,dbweekly,dbmonthly,dbannually,dbsun,dbmon,dbtue,dbwed,dbthu,dbfri,dbsat,txtDesc,dbeid,dbsms).setVisible(true);
            
            this.dispose();
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblEdit5MouseClicked

    private void lblDelete5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDelete5MouseClicked
        try
        {    
            pstmtde = conn.prepareStatement("Delete from eve_remi.set_remi where id=?");
            int res = JOptionPane.showConfirmDialog(this, "Are you sure to delete this reminder?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.YES_OPTION)
            {
                pstmtde.setInt(1, ridp5);
                pstmtde.executeUpdate();
                pstmtse = conn.prepareStatement("Select * from eve_remi.set_remi");
                rs = pstmtse.executeQuery();
                rs.beforeFirst();
                load_reminder();
                btnNext.setText("Next");
            }
        }
        catch(SQLException e)
        {
            
        }
    }//GEN-LAST:event_lblDelete5MouseClicked

    private void mnuShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuShowMouseClicked
        new set_Task().setVisible(true);
        mnuExit.setEnabled(false);
        dispose();
    }//GEN-LAST:event_mnuShowMouseClicked

    private void mnuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuExitMouseClicked
        dispose();
    }//GEN-LAST:event_mnuExitMouseClicked

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(show_Rem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new show_Rem().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblDelete1;
    private javax.swing.JLabel lblDelete2;
    private javax.swing.JLabel lblDelete3;
    private javax.swing.JLabel lblDelete4;
    private javax.swing.JLabel lblDelete5;
    private javax.swing.JLabel lblEdit1;
    private javax.swing.JLabel lblEdit2;
    private javax.swing.JLabel lblEdit3;
    private javax.swing.JLabel lblEdit4;
    private javax.swing.JLabel lblEdit5;
    private javax.swing.JLabel lblTime1;
    private javax.swing.JLabel lblTime2;
    private javax.swing.JLabel lblTime3;
    private javax.swing.JLabel lblTime4;
    private javax.swing.JLabel lblTime5;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lblTitle2;
    private javax.swing.JLabel lblTitle3;
    private javax.swing.JLabel lblTitle4;
    private javax.swing.JLabel lblTitle5;
    private javax.swing.JMenu mnuExit;
    private javax.swing.JMenu mnuShow;
    private javax.swing.JTextArea txtDesc1;
    private javax.swing.JTextArea txtDesc2;
    private javax.swing.JTextArea txtDesc3;
    private javax.swing.JTextArea txtDesc4;
    private javax.swing.JTextArea txtDesc5;
    // End of variables declaration//GEN-END:variables
}
