/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eve_rem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Vaibhav
 */
public class set_Task extends javax.swing.JFrame {
    int frm = 480, dum = 1,dumupdate=0,rid=0;
    int once,daily, weekly, monthly, annually;
    int sun, mon, tue, wed, thu, fri, sat;
    int hhi,mmi,hhch,mmch,dayi,monthi,yeari,daych,monthch,yearch,dtdayi,dtmonthi,dtyeari,hhitem=0;
    String date1,date2,gTitle,gdt,ghh,gmm,gdesc;
    String sday,smonth,syear,dtday,dtmonth,dtyear;
    String eid,sms;
    Date dt1000 = new Date();
    ResultSet rs = null;
    /**
     * Creates new form set_Task
     */
    public set_Task() {
        initComponents();
        
        setBounds(500, 70, 360, 480);
        txtTitle.setBackground(new Color(0, 0, 0,0));
        txtTitle.setOpaque(false);
        txtDesc.setBackground(new Color(0, 0, 0, 0));
        chbSun.setVisible(false);
        chbMon.setVisible(false);
        chbTue.setVisible(false);
        chbWed.setVisible(false);
        chbThu.setVisible(false);
        chbFri.setVisible(false);
        chbSat.setVisible(false);
        jTextField.setVisible(false);

        load_form();
    }

    set_Task(int ridp,String dbtitle,Date dbdate,String dbhh,String dbmm,int dbonce,int dbdaily,int dbweekly,int dbmonthly,int dbannually,int dbsun,int dbmon,int dbtue,int dbwed,int dbthu,int dbfri,int dbsat,String dbdesc,String dbeid,String dbsms) 
    {
        initComponents();
        dumupdate=1;
        rid=ridp;
        move_up();
        load_form();
        txtTitle.setText(dbtitle);
        JOptionPane.showMessageDialog(null, "Reminder's Previous Date is "+dbdate+" and Time is "+dbhh+":"+dbhh+" in update today's date will set automatically.", "Reminder Update",JOptionPane.INFORMATION_MESSAGE);
        if (dbdaily == 0)
        {
            rdoDaily.setSelected(false);
        }
        else
        {
            rdoDaily.setSelected(true);
            daily = 1;
            weekly = 0;
            monthly = 0;
            annually = 0;
        }
        if (dbweekly == 0)
        {
            rdoWeekly.setSelected(false);
        }
        else
        {
            move_down();
            rdoWeekly.setSelected(true);
            daily = 0;
            weekly = 1;
            monthly = 0;
            annually = 0;
            if (dbsun == 0)
            {chbSun.setSelected(false);}
            else
            {chbSun.setSelected(true);}
            if (dbmon == 0)
            {chbMon.setSelected(false);}
            else
            {chbMon.setSelected(true);}
            if (dbtue == 0)
            {chbTue.setSelected(false);}
            else
            {chbTue.setSelected(true);}
            if (dbwed == 0)
            {chbWed.setSelected(false);}
            else
            {chbWed.setSelected(true);}
            if (dbthu == 0)
            {chbThu.setSelected(false);}
            else
            {chbThu.setSelected(true);}
            if (dbfri == 0)
            {chbFri.setSelected(false);}
            else
            {chbFri.setSelected(true);}
            if (dbsat == 0)
            {chbSat.setSelected(false);}
            else
            {chbSat.setSelected(true);}
        }
        if (dbmonthly == 0)
        {
            rdoMonthly.setSelected(false);
        }
        else
        {
            rdoMonthly.setSelected(true);
            daily = 0;
            weekly = 0;
            monthly = 1;
            annually = 0;
        }
        if (dbannually == 0)
        {
            rdoAnnually.setSelected(false);
        }
        else
        {
            rdoAnnually.setSelected(true);
            daily = 0;
            weekly = 0;
            monthly = 0;
            annually = 1;
        }
        if (dbonce == 1 )
        {
            once = 1;
            daily = 0;
            weekly = 0;
            monthly = 0;
            annually = 0;
        }
        txtDesc.setText(dbdesc);
        if(dbeid!=null)
        {
            eid=dbeid;
            chbEmail.setSelected(true);
        }
        if(dbsms!=null)
        {
            sms=dbsms;
            chbSMS.setSelected(true);
        }
    }

    public void load_form()
    {
        setBounds(500, 70, 360, 480);
        
        txtTitle.setBackground(new Color(0, 0, 0,0));
        txtTitle.setOpaque(false);
        txtDesc.setBackground(new Color(0, 0, 0, 0));
        txtTitle.setDocument(new set_Maxlimit(45));
        txtDesc.setDocument(new set_Maxlimit(100));
        
        Date dt100 = new Date();
        DateFormat dftmyear = new SimpleDateFormat("yyyy");
        DateFormat dftmmonth = new SimpleDateFormat("MM");
        DateFormat dftday = new SimpleDateFormat("dd");
        syear = dftmyear.format(dt100);
        smonth = dftmmonth.format(dt100);
        sday = dftday.format(dt100);
        yeari = Integer.parseInt(syear);
        monthi = Integer.parseInt(smonth);
        dayi = Integer.parseInt(sday);
            
        Date dt1 = new Date();
        DateFormat dfdt = new SimpleDateFormat("dd/MM/yyyy");
        date2 = dfdt.format(dt1);
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() 
        {
            @Override
            public void run() 
            {   
                Date dt2 = new Date();
                DateFormat dftmhh = new SimpleDateFormat("HH");
                DateFormat dftmmm = new SimpleDateFormat("mm");
                DateFormat dftday = new SimpleDateFormat("dd");
                String shh = dftmhh.format(dt2);
                String smm = dftmmm.format(dt2);
                String sday = dftday.format(dt2);
                dayi = Integer.parseInt(sday);
                if (daych != dayi)
                {
                    dteChoose.setMinSelectableDate(dt2);
                    dteChoose.setDate(dteChoose.getMinSelectableDate());
                }
                daych = dayi;
                hhi = Integer.parseInt(shh);
                if (hhch != hhi)
                {
                    add_hh();
                }
                hhch = hhi;
                mmi = Integer.parseInt(smm);
                if (mmch != mmi)
                {
                    add_mm();
                }
                mmch = mmi;
            }
        };
        timer.scheduleAtFixedRate(task, 100, 1000);
    }
    
    public void add_hh()
    {
        Date dt3 = new Date();
        DateFormat dfdt = new SimpleDateFormat("dd/MM/yyyy");
        date1 = dfdt.format(dt3);
        if (dfdt.format(dt3) == null ? date2 == null : dfdt.format(dt3).equals(date2))
        {
            switch (hhi) {
                case 23:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    ckbHour.addItem("23");
                    break;
                case 22:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=22;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 21:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=21;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 20:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=20;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 19:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=19;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 18:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=18;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 17:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=17;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 16:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=16;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 15:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=15;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 14:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=14;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 13:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=13;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 12:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=12;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 11:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=11;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 10:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=10;i<24;i++)
                        ckbHour.addItem(Integer.toString(i));
                    break;
                case 9:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=9;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
                case 8:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=8;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
                case 7:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=7;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
                case 6:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=6;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
                case 5:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=5;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
                case 4:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=4;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
                case 3:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=3;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
                case 2:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=2;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
                case 1:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=1;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
                default:
                    ckbHour.removeAllItems();
                    ckbHour.addItem("HH");
                    for(int i=0;i<24;i++)
                    {
                        if(i<10)
                            ckbHour.addItem("0"+Integer.toString(i));
                        else
                            ckbHour.addItem(Integer.toString(i));
                    }   break;
            }
        }
    }
    
    public void add_mm()
    {
        Date dt4 = new Date();
        DateFormat dfdt = new SimpleDateFormat("dd/MM/yyyy");
        String getdate1 = dfdt.format(dt4);
        String getdate2 = dfdt.format(dteChoose.getDate());
        
        if(hhitem == 1 & (getdate1 == null ? getdate2 == null : getdate1.equals(getdate2))) 
        {
            switch (mmi) {
                case 59:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    break;
                case 58:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    break;
                case 57:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    ckbMinute.addItem("59");
                    break;
                case 56:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=58;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 55:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=57;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 54:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=56;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 53:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=55;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 52:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=54;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 51:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=53;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 50:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=52;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 49:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=51;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 48:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=50;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 47:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=49;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 46:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=48;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 45:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=47;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 44:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=46;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 43:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=45;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 42:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=44;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 41:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=43;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 40:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=42;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 39:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=41;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 38:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=40;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 37:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=39;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 36:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=38;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 35:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=37;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 34:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=36;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 33:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=35;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 32:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=34;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 31:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=33;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 30:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=32;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 29:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=31;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 28:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=30;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 27:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=29;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 26:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=28;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 25:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=27;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 24:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=26;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 23:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=25;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 22:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=24;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 21:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=23;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 20:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=22;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 19:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=21;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 18:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=20;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 17:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=19;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 16:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=18;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 15:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=17;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 14:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=16;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 13:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=15;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 12:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=14;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 11:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=13;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 10:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=12;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 9:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=11;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 8:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=10;i<60;i++)
                    ckbMinute.addItem(Integer.toString(i));
                    break;
                case 7:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=9;i<60;i++)
                    {
                        if(i<10)
                            ckbMinute.addItem("0"+Integer.toString(i));
                        else
                            ckbMinute.addItem(Integer.toString(i));
                    }   
                    break;
                case 6:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=8;i<60;i++)
                    {
                        if(i<10)
                            ckbMinute.addItem("0"+Integer.toString(i));
                        else
                            ckbMinute.addItem(Integer.toString(i));
                    }
                    break;
                case 5:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=7;i<60;i++)
                    {
                        if(i<10)
                            ckbMinute.addItem("0"+Integer.toString(i));
                        else
                            ckbMinute.addItem(Integer.toString(i));
                    }
                    break;
                case 4:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=6;i<60;i++)
                    {
                        if(i<10)
                            ckbMinute.addItem("0"+Integer.toString(i));
                        else
                            ckbMinute.addItem(Integer.toString(i));
                    }
                    break;
                case 3:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=5;i<60;i++)
                    {
                        if(i<10)
                            ckbMinute.addItem("0"+Integer.toString(i));
                        else
                            ckbMinute.addItem(Integer.toString(i));
                    }
                    break;
                case 2:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=4;i<60;i++)
                    {
                        if(i<10)
                            ckbMinute.addItem("0"+Integer.toString(i));
                        else
                            ckbMinute.addItem(Integer.toString(i));
                    }
                    break;
                case 1:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=3;i<60;i++)
                    {
                        if(i<10)
                            ckbMinute.addItem("0"+Integer.toString(i));
                        else
                            ckbMinute.addItem(Integer.toString(i));
                    }
                    break;
                case 0:
                    ckbMinute.removeAllItems();
                    ckbMinute.addItem("MM");
                    for(int i=2;i<60;i++)
                    {
                        if(i<10)
                            ckbMinute.addItem("0"+Integer.toString(i));
                        else
                            ckbMinute.addItem(Integer.toString(i));
                    }
                    break;
            }
        }
        else
        {
            ckbMinute.removeAllItems();
            ckbMinute.addItem("MM");
            for(int i=0;i<60;i++)
            {
                if(i<10)
                    ckbMinute.addItem("0"+Integer.toString(i));
                else
                    ckbMinute.addItem(Integer.toString(i));
            }
        }
        ckbMinute.setSelectedItem("MM");
    }
    
    public void move_down() {
        Timer timer1 = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                if (getHeight() >= 530) {
                    timer1.cancel();
                    rdoDaily.setEnabled(true);
                    rdoWeekly.setEnabled(true);
                    rdoMonthly.setEnabled(true);
                    rdoAnnually.setEnabled(true);

                    chbSun.setVisible(true);
                    chbMon.setVisible(true);
                    chbTue.setVisible(true);
                    chbWed.setVisible(true);
                    chbThu.setVisible(true);
                    chbFri.setVisible(true);
                    chbSat.setVisible(true);
                } 
                else 
                {
                    rdoDaily.setEnabled(false);
                    rdoMonthly.setEnabled(false);
                    rdoAnnually.setEnabled(false);
                    frm = frm + 1;
                    setBounds(getX(), getY(), 360, frm);
                }

            }
        };
        timer1.scheduleAtFixedRate(task1, 0, 2);
    }

    public void move_up() {
        Timer timer2 = new Timer();
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                if (getHeight() <= 480) {
                    timer2.cancel();
                    rdoDaily.setEnabled(true);
                    rdoWeekly.setEnabled(true);
                    rdoMonthly.setEnabled(true);
                    rdoAnnually.setEnabled(true);

                    chbSun.setVisible(false);
                    chbMon.setVisible(false);
                    chbTue.setVisible(false);
                    chbWed.setVisible(false);
                    chbThu.setVisible(false);
                    chbFri.setVisible(false);
                    chbSat.setVisible(false);
                    chbSun.setSelected(false);
                    chbMon.setSelected(false);
                    chbTue.setSelected(false);
                    chbWed.setSelected(false);
                    chbThu.setSelected(false);
                    chbFri.setSelected(false);
                    chbSat.setSelected(false);
                } 
                else 
                {
                    rdoDaily.setEnabled(false);
                    rdoWeekly.setEnabled(false);
                    rdoMonthly.setEnabled(false);
                    rdoAnnually.setEnabled(false);
                    frm = frm - 1;
                    setBounds(getX(), getY(), 360, frm);
                }
            }
        };
        timer2.scheduleAtFixedRate(task2, 0, 1);
    }

    public int row_count() throws SQLException, ClassNotFoundException
    {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql:///eve_remi", "root", "Vaibhav159");
            
        pstmt = conn.prepareStatement("Select count(id) from eve_remi.set_remi");
        rs = pstmt.executeQuery();
        if(rs.next())
        {
            count = rs.getInt("count(id)");
        }
        return count;
    }
    
    public void value_read() {
        hhitem = ckbHour.getSelectedIndex();
        add_mm();
    }

    public void chkbox_enable_tr() {
        chbSun.setEnabled(true);
        chbMon.setEnabled(true);
        chbTue.setEnabled(true);
        chbWed.setEnabled(true);
        chbThu.setEnabled(true);
        chbFri.setEnabled(true);
        chbSat.setEnabled(true);
    }

    public void chkbox_enable_fl() {
        chbSun.setEnabled(false);
        chbMon.setEnabled(false);
        chbTue.setEnabled(false);
        chbWed.setEnabled(false);
        chbThu.setEnabled(false);
        chbFri.setEnabled(false);
        chbSat.setEnabled(false);
    }

    public void con() {
        if (chbSun.isSelected()) {
            if (chbMon.isSelected()) {
                if (chbTue.isSelected()) {
                    if (chbWed.isSelected()) {
                        if (chbThu.isSelected()) {
                            if (chbFri.isSelected()) {
                                if (chbSat.isSelected()) {
                                    rdoDaily.setSelected(true);
                                    move_up();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void db_code() throws ClassNotFoundException, SQLException
    {
        if (rdoDaily.isSelected()==false & rdoWeekly.isSelected()==false & rdoMonthly.isSelected()==false & rdoAnnually.isSelected()==false)
        {
            once = 1;
            daily = 0;
            weekly = 0;
            monthly = 0;
            annually = 0;
        }
        else
        {
            once = 0;
        }
        
        Connection conn = null;
        PreparedStatement pstmtin = null;
        PreparedStatement pstmtup = null;
        
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///eve_remi", "root", "Vaibhav159");
            if(dumupdate==0)
            {
                try 
                {
                    int res = JOptionPane.showConfirmDialog(this, "Are you sure to Save Reminder?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(res == JOptionPane.YES_OPTION)
                    {
                        int rc = row_count();
                        if(rc<20)
                        {
                            pstmtin = conn.prepareStatement("insert into eve_remi.set_remi values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            pstmtin.setInt(1, 0);
                            pstmtin.setString(2, gTitle);
                            pstmtin.setString(3, gdt);
                            pstmtin.setString(4, ghh);
                            pstmtin.setString(5, gmm);
                            pstmtin.setInt(6, once);
                            pstmtin.setInt(7, daily);
                            pstmtin.setInt(8, weekly);
                            pstmtin.setInt(9, monthly);
                            pstmtin.setInt(10, annually);
                            pstmtin.setInt(11, sun);
                            pstmtin.setInt(12, mon);
                            pstmtin.setInt(13, tue);
                            pstmtin.setInt(14, wed);
                            pstmtin.setInt(15, thu);
                            pstmtin.setInt(16, fri);
                            pstmtin.setInt(17, sat);
                            pstmtin.setString(18, gdesc);
                            pstmtin.setString(19, eid);
                            pstmtin.setString(20, sms);
                            int i = pstmtin.executeUpdate();

                            if (i > 0) 
                            {
                                txtTitle.setText("");
                                ckbHour.setSelectedIndex(0);
                                ckbMinute.setSelectedIndex(0);
                                chbSun.setSelected(false);
                                chbMon.setSelected(false);
                                chbTue.setSelected(false);
                                chbWed.setSelected(false);
                                chbThu.setSelected(false);
                                chbFri.setSelected(false);
                                chbSat.setSelected(false);
                                rdoDaily.setSelected(false);
                                rdoWeekly.setSelected(false);
                                rdoMonthly.setSelected(false);
                                rdoAnnually.setSelected(false);
                                txtDesc.setText("");
                                chbEmail.setSelected(false);
                                chbSMS.setSelected(false);
                                Date dt8 = new Date();
                                dteChoose.setDate(dt8);
                                txtTitle.requestFocus();
                                move_up();
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "You cann't set Reminder more than 20","Set Reminder", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else if(res == JOptionPane.NO_OPTION)
                    {
                        txtTitle.requestFocus();
                    }
                }
                catch (HeadlessException | SQLException e) 
                {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if(dumupdate==1)
            {
            try 
            {
                int res = JOptionPane.showConfirmDialog(this, "Are you sure to Update Reminder?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(res == JOptionPane.YES_OPTION)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_Title=?,db_dteChooser=?,db_hh=?,db_mm=?,db_once=?,db_daily=?,db_weekly=?"
                        + ",db_monthly=?,db_annually=?,db_sun=?,db_mon=?,db_tue=?,db_wed=?,db_thu=?,db_fri=?,db_sat=?,db_desc=?,db_eid=?,db_sms=? where id=?");
                    pstmtup.setInt(20, rid);
                    pstmtup.setString(1, gTitle);
                    pstmtup.setString(2, gdt);
                    pstmtup.setString(3, ghh);
                    pstmtup.setString(4, gmm);
                    pstmtup.setInt(5, once);
                    pstmtup.setInt(6, daily);
                    pstmtup.setInt(7, weekly);
                    pstmtup.setInt(8, monthly);
                    pstmtup.setInt(9, annually);
                    pstmtup.setInt(10, sun);
                    pstmtup.setInt(11, mon);
                    pstmtup.setInt(12, tue);
                    pstmtup.setInt(13, wed);
                    pstmtup.setInt(14, thu);
                    pstmtup.setInt(15, fri);
                    pstmtup.setInt(16, sat);
                    pstmtup.setString(17, gdesc);
                    pstmtup.setString(18, eid);
                    pstmtup.setString(19, sms);
                    pstmtup.executeUpdate();
                    this.dispose();
                    new show_Rem().setVisible(true);
                }
                else if(res == JOptionPane.NO_OPTION)
                {
                    txtTitle.requestFocus();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(set_Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
    }
    
    public void dateValidate()
    {
        if (dteChoose.getDate()==null )
        {    
            JOptionPane.showMessageDialog(null, "Date format cann't match","Set Date", JOptionPane.WARNING_MESSAGE);
            dteChoose.setDate(dteChoose.getMinSelectableDate());
            dteChoose.requestFocus(); 
        }
        else
        {
            SimpleDateFormat dfrtyear = new SimpleDateFormat("yyyy");
            SimpleDateFormat dfrtmonth = new SimpleDateFormat("MM");
            SimpleDateFormat dfrtday = new SimpleDateFormat("dd");
            dtyear = (dfrtyear.format(dteChoose.getDate()));
            dtmonth = (dfrtmonth.format(dteChoose.getDate()));
            dtday = (dfrtday.format(dteChoose.getDate()));
            dtyeari = Integer.parseInt(dtyear);
            dtmonthi = Integer.parseInt(dtmonth);
            dtdayi = Integer.parseInt(dtday);
            if (yeari==dtyeari)
                {
                if (monthi==dtmonthi)
                {
                    if (dayi<=dtdayi)
                    {
                        DateFormat dfdt = new SimpleDateFormat("dd/MM/yyyy");
                        date2 = dfdt.format(dteChoose.getDate());
                        add_hh();
                    }
                    else
                    {
                        try
                        {
                        JOptionPane.showMessageDialog(null, "Day cann't be less than today","Set Date", JOptionPane.WARNING_MESSAGE);
                        dteChoose.setDate(dteChoose.getMinSelectableDate());
                        }
                        catch(HeadlessException e)
                        {
                            JOptionPane.showMessageDialog(null, e);
                            dteChoose.setDate(dteChoose.getMinSelectableDate());
                        }
                    }
                }
                else if (monthi<dtmonthi)
                {

                    DateFormat dfdt = new SimpleDateFormat("dd/MM/yyyy");
                    date2 = dfdt.format(dteChoose.getDate());
                    add_hh(); 
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Month cann't be less than today","Set Date", JOptionPane.WARNING_MESSAGE);
                    dteChoose.setDate(dteChoose.getMinSelectableDate());
                }
            }
            else if (yeari<=dtyeari)
            {
                DateFormat dfdt = new SimpleDateFormat("dd/MM/yyyy");
                date2 = dfdt.format(dteChoose.getDate());
                add_hh();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Year cann't be less than today","Set Date", JOptionPane.WARNING_MESSAGE);
                dteChoose.setDate(dteChoose.getMinSelectableDate());
            }
        }
    }
    
    public void email_validate()
    {
        eid = (String) JOptionPane.showInputDialog(this, "Enter the E-mail id", "E-mail ID", JOptionPane.INFORMATION_MESSAGE,null,null,eid);
        if(eid==null)
        {
            chbEmail.setSelected(false);
        }
        else
        {
        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(eid);
        if(matcher.matches())
        {
            
        }
        else
        {
            if("".equals(eid))
            {
                JOptionPane.showMessageDialog(null, "You are not filled in E-mail id","E-mail ID", JOptionPane.WARNING_MESSAGE);    
                email_validate();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please provide the valid E-mail id","E-mail ID", JOptionPane.WARNING_MESSAGE);
                email_validate();
            }
        }
        }
        
    }
    
    public boolean valPhone(String in)
    {
        return in.length() == 10 & in.matches("[0-9]+") & in.charAt(0) == '6' | in.charAt(0) == '7' | in.charAt(0) == '8' | in.charAt(0) == '9';
    }
    
   /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        dteChoose = new com.toedter.calendar.JDateChooser();
        ckbHour = new javax.swing.JComboBox<>();
        ckbMinute = new javax.swing.JComboBox<>();
        rdoDaily = new javax.swing.JRadioButton();
        rdoWeekly = new javax.swing.JRadioButton();
        rdoMonthly = new javax.swing.JRadioButton();
        rdoAnnually = new javax.swing.JRadioButton();
        chbSun = new javax.swing.JCheckBox();
        chbMon = new javax.swing.JCheckBox();
        chbTue = new javax.swing.JCheckBox();
        chbWed = new javax.swing.JCheckBox();
        chbThu = new javax.swing.JCheckBox();
        chbFri = new javax.swing.JCheckBox();
        chbSat = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        btnSet = new javax.swing.JButton();
        chbEmail = new javax.swing.JCheckBox();
        chbSMS = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuShow = new javax.swing.JMenu();
        mnuSMSAPI = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenu();

        jTextField.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(240, 173, 126));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(0, 255, 0));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Title");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Date");
        jLabel2.setAlignmentY(108);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Time");
        jLabel3.setAlignmentY(146);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Repeat");
        jLabel4.setAlignmentY(194);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Set Reminder");

        txtTitle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTitle.setToolTipText("Set Title");

        dteChoose.setToolTipText("Set Date\n");
        dteChoose.setAutoscrolls(true);
        dteChoose.setDateFormatString("dd/MM/yyyy");
        dteChoose.setFocusable(false);
        dteChoose.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        dteChoose.setMinSelectableDate(dt1000);
        dteChoose.setOpaque(false);
        dteChoose.setRequestFocusEnabled(false);
        dteChoose.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                dteChooseAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dteChooseAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        dteChoose.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dteChooseFocusLost(evt);
            }
        });
        dteChoose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dteChooseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dteChooseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dteChooseMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dteChooseMouseReleased(evt);
            }
        });

        ckbHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HH", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        ckbHour.setToolTipText("Set Hour");
        ckbHour.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ckbHourFocusGained(evt);
            }
        });
        ckbHour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbHourActionPerformed(evt);
            }
        });

        ckbMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM" }));
        ckbMinute.setToolTipText("Set Minute");
        ckbMinute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbMinuteActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaily);
        rdoDaily.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdoDaily.setText("Daily");
        rdoDaily.setToolTipText("Set Daily");
        rdoDaily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDailyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoWeekly);
        rdoWeekly.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdoWeekly.setText("Weekly");
        rdoWeekly.setToolTipText("Set Weekly");
        rdoWeekly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoWeeklyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMonthly);
        rdoMonthly.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdoMonthly.setText("Monthly");
        rdoMonthly.setToolTipText("Set Monthly");
        rdoMonthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMonthlyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoAnnually);
        rdoAnnually.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdoAnnually.setText("Annually");
        rdoAnnually.setToolTipText("Set Annually");
        rdoAnnually.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoAnnuallyActionPerformed(evt);
            }
        });

        chbSun.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        chbSun.setText("Sun");
        chbSun.setToolTipText("Set Sunday");
        chbSun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbSunActionPerformed(evt);
            }
        });

        chbMon.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        chbMon.setText("Mon");
        chbMon.setToolTipText("Set Monday");
        chbMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMonActionPerformed(evt);
            }
        });

        chbTue.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        chbTue.setText("Tue");
        chbTue.setToolTipText("Set Tuesday");
        chbTue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbTueActionPerformed(evt);
            }
        });

        chbWed.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        chbWed.setText("Wed");
        chbWed.setToolTipText("Set Wednesday");
        chbWed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbWedActionPerformed(evt);
            }
        });

        chbThu.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        chbThu.setText("Thu");
        chbThu.setToolTipText("Set Thursday");
        chbThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbThuActionPerformed(evt);
            }
        });

        chbFri.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        chbFri.setText("Fri");
        chbFri.setToolTipText("Set Friday");
        chbFri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbFriActionPerformed(evt);
            }
        });

        chbSat.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        chbSat.setText("Sat");
        chbSat.setToolTipText("Set Saturday");
        chbSat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbSatActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Desc.");

        txtDesc.setColumns(20);
        txtDesc.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDesc.setLineWrap(true);
        txtDesc.setTabSize(0);
        txtDesc.setToolTipText("Set Description");
        txtDesc.setWrapStyleWord(true);
        txtDesc.setAutoscrolls(false);
        jScrollPane1.setViewportView(txtDesc);

        btnSet.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnSet.setText("Set");
        btnSet.setToolTipText("Set Reminder");
        btnSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetActionPerformed(evt);
            }
        });

        chbEmail.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        chbEmail.setText("Remind me by E-mail");
        chbEmail.setToolTipText("Set Email ID");
        chbEmail.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                chbEmailMouseMoved(evt);
            }
        });
        chbEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbEmailActionPerformed(evt);
            }
        });

        chbSMS.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        chbSMS.setText("Remind me by SMS");
        chbSMS.setToolTipText("Set Mobile No.");
        chbSMS.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                chbSMSMouseMoved(evt);
            }
        });
        chbSMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbSMSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnSet, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chbEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(chbSMS))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnSet, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        mnuShow.setBorder(null);
        mnuShow.setText("Show Remonder");
        mnuShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuShowMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuShow);

        mnuSMSAPI.setBorder(null);
        mnuSMSAPI.setText("Set SMS API");
        mnuSMSAPI.setIconTextGap(13);
        mnuSMSAPI.setMargin(new java.awt.Insets(0, 15, 0, 0));
        mnuSMSAPI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuSMSAPIMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuSMSAPI);

        mnuExit.setBorder(null);
        mnuExit.setText("Exit");
        mnuExit.setIconTextGap(13);
        mnuExit.setMargin(new java.awt.Insets(0, 15, 0, 0));
        mnuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuExitMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuExit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(21, 21, 21)
                .addComponent(ckbHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(ckbMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25)
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(dteChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addGap(10, 10, 10)
                        .addComponent(rdoDaily)
                        .addGap(2, 2, 2)
                        .addComponent(rdoWeekly)
                        .addGap(2, 2, 2)
                        .addComponent(rdoMonthly)
                        .addGap(1, 1, 1)
                        .addComponent(rdoAnnually))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(chbSun)
                        .addGap(9, 9, 9)
                        .addComponent(chbMon)
                        .addGap(8, 8, 8)
                        .addComponent(chbTue)
                        .addGap(8, 8, 8)
                        .addComponent(chbWed))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(chbThu))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(chbFri, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(212, 212, 212)
                                .addComponent(chbSat))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1))
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2))
                    .addComponent(dteChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3))
                    .addComponent(ckbHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(rdoDaily)
                    .addComponent(rdoWeekly)
                    .addComponent(rdoMonthly)
                    .addComponent(rdoAnnually))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbSun)
                    .addComponent(chbMon)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chbTue)
                        .addComponent(chbWed)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbThu)
                    .addComponent(chbFri, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbSat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetActionPerformed
        gTitle = txtTitle.getText();
        SimpleDateFormat dfrt = new SimpleDateFormat("yyyy-MM-dd");
        jTextField.setText(dfrt.format(dteChoose.getDate()));
        String dt = jTextField.getText();
        gdt = dt;
        String hh = ckbHour.getSelectedItem().toString();
        ghh = hh;
        String mm = ckbMinute.getSelectedItem().toString();
        gmm = mm;
        String desc = txtDesc.getText();
        gdesc = desc;

        if (chbSun.isSelected()) {
            sun = 1;
        }
        else
            sun = 0;
        if (chbMon.isSelected()) {
            mon = 1;
        }
        else
            mon = 0;
        if (chbTue.isSelected()) {
            tue = 1;
        }
        else
            tue = 0;
        if (chbWed.isSelected()) {
            wed = 1;
        }
        else
            wed = 0;
        if (chbThu.isSelected()) {
            thu = 1;
        }
        else
            thu = 0;
        if (chbFri.isSelected()) {
            fri = 1;
        }
        else
            fri = 0;
        if (chbSat.isSelected()) {
            sat = 1;
        }
        else
            sat = 0;
        
        if ("".equals(gTitle))
        {
            JOptionPane.showMessageDialog(null, "Title cann't be empty", gTitle, JOptionPane.WARNING_MESSAGE);
            txtTitle.requestFocus();
        }
        else
        {
        if ("HH".equals(hh))
            {
                JOptionPane.showMessageDialog(null, "Hour cann't be empty", gTitle, JOptionPane.WARNING_MESSAGE);
                dteChoose.requestFocus();
            }
            else
            { 
                if ("MM".equals(mm))
                {
                    JOptionPane.showMessageDialog(null, "Minute cann't be empty", gTitle, JOptionPane.WARNING_MESSAGE);
                    ckbMinute.requestFocus();
                }
                else
                {
                    if (rdoWeekly.isSelected())
                    {
                        if (chbSun.isSelected() | chbMon.isSelected() | chbTue.isSelected() | chbWed.isSelected()
                                | chbThu.isSelected() | chbFri.isSelected() | chbSat.isSelected())
                            {
                               if ("".equals(desc))
                                {
                                    JOptionPane.showMessageDialog(null, "Description cann't be empty", gTitle, JOptionPane.WARNING_MESSAGE);
                                    txtDesc.requestFocus();
                                }
                                else
                                {
                                   try {
                                       db_code();
                                   } catch (ClassNotFoundException | SQLException ex) {
                                       Logger.getLogger(set_Task.class.getName()).log(Level.SEVERE, null, ex);
                                   }
                                }
                            }
                            else
                                JOptionPane.showMessageDialog(null, "Please select the days first", gTitle, JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        if ("".equals(desc))
                        {
                            JOptionPane.showMessageDialog(null, "Description cann't be empty", gTitle, JOptionPane.WARNING_MESSAGE);
                            txtDesc.requestFocus();
                        }
                        else
                        {
                            
                            try {
                                db_code();
                            } catch (ClassNotFoundException | SQLException ex) {
                                Logger.getLogger(set_Task.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }          
        }   
    }//GEN-LAST:event_btnSetActionPerformed

    private void chbMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMonActionPerformed
        con();
    }//GEN-LAST:event_chbMonActionPerformed

    private void rdoAnnuallyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoAnnuallyActionPerformed
        move_up();
        daily = 0;
        weekly = 0;
        monthly = 0;
        annually = 1;
    }//GEN-LAST:event_rdoAnnuallyActionPerformed

    private void rdoMonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMonthlyActionPerformed
        move_up();
        daily = 0;
        weekly = 0;
        monthly = 1;
        annually = 0;
    }//GEN-LAST:event_rdoMonthlyActionPerformed

    private void rdoWeeklyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoWeeklyActionPerformed
        move_down();
        daily = 0;
        weekly = 1;
        monthly = 0;
        annually = 0;
    }//GEN-LAST:event_rdoWeeklyActionPerformed

    private void rdoDailyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDailyActionPerformed
        move_up();
        daily = 1;
        weekly = 0;
        monthly = 0;
        annually = 0;
    }//GEN-LAST:event_rdoDailyActionPerformed

    private void chbSunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbSunActionPerformed
        con();
    }//GEN-LAST:event_chbSunActionPerformed

    private void chbTueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbTueActionPerformed
        con();
    }//GEN-LAST:event_chbTueActionPerformed

    private void chbWedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbWedActionPerformed
        con();
    }//GEN-LAST:event_chbWedActionPerformed

    private void chbThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbThuActionPerformed
        con();
    }//GEN-LAST:event_chbThuActionPerformed

    private void chbFriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbFriActionPerformed
        con();
    }//GEN-LAST:event_chbFriActionPerformed

    private void chbSatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbSatActionPerformed
        con();
    }//GEN-LAST:event_chbSatActionPerformed

    private void mnuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuExitMouseClicked
        dispose();
    }//GEN-LAST:event_mnuExitMouseClicked

    private void mnuShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuShowMouseClicked
        new show_Rem().setVisible(true);
        mnuExit.setEnabled(false);
        dispose();
    }//GEN-LAST:event_mnuShowMouseClicked

    private void ckbHourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbHourActionPerformed
        value_read();
    }//GEN-LAST:event_ckbHourActionPerformed

    private void dteChooseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dteChooseMouseClicked
  
    }//GEN-LAST:event_dteChooseMouseClicked

    private void dteChooseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dteChooseFocusLost
        
    }//GEN-LAST:event_dteChooseFocusLost

    private void dteChooseAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dteChooseAncestorAdded
        
    }//GEN-LAST:event_dteChooseAncestorAdded

    private void dteChooseAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dteChooseAncestorMoved

    }//GEN-LAST:event_dteChooseAncestorMoved

    private void dteChooseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dteChooseMouseExited

    }//GEN-LAST:event_dteChooseMouseExited

    private void dteChooseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dteChooseMouseEntered

    }//GEN-LAST:event_dteChooseMouseEntered

    private void dteChooseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dteChooseMouseReleased

    }//GEN-LAST:event_dteChooseMouseReleased

    private void ckbHourFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ckbHourFocusGained
        dateValidate();
    }//GEN-LAST:event_ckbHourFocusGained

    private void ckbMinuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbMinuteActionPerformed
        
    }//GEN-LAST:event_ckbMinuteActionPerformed

    private void chbEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEmailActionPerformed
        if(chbEmail.isSelected())
        {
            email_validate();
        }
        else
        {
            int res1 = JOptionPane.showConfirmDialog(this, "Are you sure to remove remind by E-mail?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res1 == JOptionPane.YES_OPTION)
            {
                eid="";
            }
            else if(res1 == JOptionPane.NO_OPTION)
            {
                chbEmail.setSelected(true);
            }
        }        
    }//GEN-LAST:event_chbEmailActionPerformed

    private void chbSMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbSMSActionPerformed
        if(chbSMS.isSelected())
        {
            sms = (String) JOptionPane.showInputDialog(this, "Enter the Mobile No.", "Mobile Number", JOptionPane.INFORMATION_MESSAGE,null,null,sms);
            if(sms==null)
            {
                chbSMS.setSelected(false);
            }
            else
            {
                if("".equals(sms))
                {
                    JOptionPane.showMessageDialog(null, "You are not filled in Mobile No.","Mobile Number", JOptionPane.WARNING_MESSAGE);    
                    chbSMSActionPerformed(evt);
                }
                else
                {
                    if(valPhone(sms))
                    {  }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please provide the valid Mobile No.","Mobile Number", JOptionPane.WARNING_MESSAGE);
                        chbSMSActionPerformed(evt);
                    }
                }
            }
            }
        else
        {
            int res1 = JOptionPane.showConfirmDialog(this, "Are you sure to remove remind by SMS?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res1 == JOptionPane.YES_OPTION)
            {
                sms="";
            }
            else if(res1 == JOptionPane.NO_OPTION)
            {
                chbSMS.setSelected(true);
            }
        }
    }//GEN-LAST:event_chbSMSActionPerformed

    private void mnuSMSAPIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSMSAPIMouseClicked
        try {
            new SMS_API().setVisible(true);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(set_Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuSMSAPIMouseClicked

    private void chbEmailMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chbEmailMouseMoved
        if(chbEmail.isSelected())
            chbEmail.setToolTipText("E-ID is :- "+eid);
        else
            chbEmail.setToolTipText("Set Email ID");
    }//GEN-LAST:event_chbEmailMouseMoved

    private void chbSMSMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chbSMSMouseMoved
        if(chbSMS.isSelected())
            chbSMS.setToolTipText("Mob. No. is :- "+sms);
        else
            chbSMS.setToolTipText("Set Mobile No.");
    }//GEN-LAST:event_chbSMSMouseMoved

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
            java.util.logging.Logger.getLogger(set_Task.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new set_Task().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSet;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbEmail;
    private javax.swing.JCheckBox chbFri;
    private javax.swing.JCheckBox chbMon;
    private javax.swing.JCheckBox chbSMS;
    private javax.swing.JCheckBox chbSat;
    private javax.swing.JCheckBox chbSun;
    private javax.swing.JCheckBox chbThu;
    private javax.swing.JCheckBox chbTue;
    private javax.swing.JCheckBox chbWed;
    private javax.swing.JComboBox<String> ckbHour;
    private javax.swing.JComboBox<String> ckbMinute;
    private com.toedter.calendar.JDateChooser dteChoose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField;
    private javax.swing.JMenu mnuExit;
    private javax.swing.JMenu mnuSMSAPI;
    private javax.swing.JMenu mnuShow;
    private javax.swing.JRadioButton rdoAnnually;
    private javax.swing.JRadioButton rdoDaily;
    private javax.swing.JRadioButton rdoMonthly;
    private javax.swing.JRadioButton rdoWeekly;
    private javax.swing.JTextArea txtDesc;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables

    
}
