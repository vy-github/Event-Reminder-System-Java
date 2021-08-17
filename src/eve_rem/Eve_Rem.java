/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eve_rem;

/**
 *
 * @author Vaibhav
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

import doryan.windowsnotificationapi.fr.Notification;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

public class Eve_Rem 
{

    
    static int hhi,mmi,hhch,mmch,dayi,daych,MMi,yyyyi;
    static String dtd, dbdt;;
    static Connection conn = null;
    static PreparedStatement pstmtse = null;
    static PreparedStatement pstmtup = null;
    static ResultSet rs = null;
    static int dumdaily1 = 0,dumweekly = 0,dummonthly = 0,dumannually = 0, dum=0,dumrs=0;
    
    
    public void Send(String to,String tit,String desc)
    {
        try
        {
            String host ="smtp.googlemail.com" ;
            String user = "your.task.reminder@gmail.com";
            String pass = "eventreminder";
            String from = "your.task.reminder@gmail.com";
            String subject = tit;
            String message = desc;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(message);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
        }
        catch(MessagingException ex)
        {
            //JOptionPane.showMessageDialog(null, "E-mail message not send because no internet connection for "+"'"+tit+"'","Show Reminder", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void notification(String tit,String desc) throws AWTException
    {
        try {
            Notification.sendNotification(tit,desc, MessageType.INFO);
        }
        catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void smsSend(String mobno,String desc) throws MalformedURLException, IOException
    {
        try
        {
            String URLS = "https://www.pay2all.in/web-api/send_sms?api_token="+pubSMSAPI+"&number="+mobno+"&senderid=Remind&message="+URLEncoder.encode(desc, "UTF-8")+"&route=4";
            URL url = new URL(URLS);System.out.println(URLS);
            HttpsURLConnection connurl = (HttpsURLConnection)url.openConnection();
            System.out.println(connurl.getResponseMessage());System.out.println("run SMS API Token");
            connurl.disconnect();
        }
        catch(IOException e)
        {
            //JOptionPane.showMessageDialog(null, e);
        }
    }
    public static void loop_check_weekly() throws SQLException
    {
        int sun = rs.getInt(11);
        int mon = rs.getInt(12);
        int tue = rs.getInt(13);
        int wed = rs.getInt(14);
        int thu = rs.getInt(15);
        int fri = rs.getInt(16);
        int sat = rs.getInt(17);
        int id = rs.getInt(1);
        
        for (int i=1 ; i<7 ; i++)
        {
            if (dum==0)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("EEE");
                Calendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH, i);
                String dbday = sdf.format(cal.getTime());
               
                if (dum==0)
                {
                    if("Sun".equals(dbday))
                    {
                        if(sun==1)
                        {
                            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cal1 = new GregorianCalendar();
                            cal1.add(Calendar.DAY_OF_MONTH, i);
                            dbdt = sdft.format(cal1.getTime());
                            pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                            pstmtup.setString(1, dbdt);
                            pstmtup.setInt(2, id);
                            pstmtup.executeUpdate();
                            dum = 1;
                        }
                    }
                }
                if (dum==0)
                {
                    if("Mon".equals(dbday))
                    {
                        if(mon==1)
                        {
                            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cal1 = new GregorianCalendar();
                            cal1.add(Calendar.DAY_OF_MONTH, i);
                            dbdt = sdft.format(cal1.getTime());
                            pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                            pstmtup.setString(1, dbdt);
                            pstmtup.setInt(2, id);
                            pstmtup.executeUpdate();
                            dum = 1;
                        }
                    }
                }
                if (dum==0)
                {
                    if("Tue".equals(dbday))
                    {
                        if(tue==1)
                        {
                            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cal1 = new GregorianCalendar();
                            cal1.add(Calendar.DAY_OF_MONTH, i);
                            dbdt = sdft.format(cal1.getTime());
                            pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                            pstmtup.setString(1, dbdt);
                            pstmtup.setInt(2, id);
                            pstmtup.executeUpdate();
                            dum = 1;
                        }
                    }
                }
                if (dum==0)
                {
                    if("Wed".equals(dbday))
                    {
                        if(wed==1)
                        {
                            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cal1 = new GregorianCalendar();
                            cal1.add(Calendar.DAY_OF_MONTH, i);
                            dbdt = sdft.format(cal1.getTime());
                            pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                            pstmtup.setString(1, dbdt);
                            pstmtup.setInt(2, id);
                            pstmtup.executeUpdate();
                            dum = 1;
                        }
                    }
                }
                if (dum==0)
                {
                    if("Thu".equals(dbday))
                    {
                        if(thu==1)
                        {
                            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cal1 = new GregorianCalendar();
                            cal1.add(Calendar.DAY_OF_MONTH, i);
                            dbdt = sdft.format(cal1.getTime());
                            pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                            pstmtup.setString(1, dbdt);
                            pstmtup.setInt(2, id);
                            pstmtup.executeUpdate();
                            dum = 1;
                        }
                    }
                }
                if (dum==0)
                {
                    if("Fri".equals(dbday))
                    {
                        if(fri==1)
                        {
                            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cal1 = new GregorianCalendar();
                            cal1.add(Calendar.DAY_OF_MONTH, i);
                            dbdt = sdft.format(cal1.getTime());
                            pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                            pstmtup.setString(1, dbdt);
                            pstmtup.setInt(2, id);
                            pstmtup.executeUpdate();
                            dum = 1;
                        }
                    }
                }
                if (dum==0)
                {
                    if("Sat".equals(dbday))
                    {
                        if(sat==1)
                        {
                            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cal1 = new GregorianCalendar();
                            cal1.add(Calendar.DAY_OF_MONTH, i);
                            dbdt = sdft.format(cal1.getTime());
                            pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                            pstmtup.setString(1, dbdt);
                            pstmtup.setInt(2, id);
                            pstmtup.executeUpdate();
                            dum = 1;
                        }
                    }
                }
            }
        }
    }
    public static void check_weekly() throws SQLException
    {
        /*Date dt1 = null;
        Date dt = new Date();
        SimpleDateFormat dfdt = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            dt1 = dfdt.parse(dfdt.format(dt));
        }
        catch(java.text.ParseException e)
        { }
        Date dt2 = rs.getDate(3);
        
        if(dt1.compareTo(dt2)>0)
        {*/
            int sun = rs.getInt(11);
            int mon = rs.getInt(12);
            int tue = rs.getInt(13);
            int wed = rs.getInt(14);
            int thu = rs.getInt(15);
            int fri = rs.getInt(16);
            int sat = rs.getInt(17);
            int id = rs.getInt(1);
            
            SimpleDateFormat fsdf = new SimpleDateFormat("EEE");
            Calendar fcal = new GregorianCalendar();
            fcal.add(Calendar.DAY_OF_MONTH, 0);
            String fdbday = fsdf.format(fcal.getTime());
            if(null != fdbday)
            switch (fdbday) 
            {
                case "Sun":
                    if(sun!=1)
                    {
                        loop_check_weekly();
                    }
                    else
                    {
                        Date dtweek = new Date();
                        DateFormat dfdtweek = new SimpleDateFormat("yyyy-MM-dd");
                        String dbdtweek = dfdtweek.format(dtweek);
                        pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                        pstmtup.setString(1, dbdtweek);
                        pstmtup.setInt(2, id);
                        pstmtup.executeUpdate();
                    }
                    break;
                case "Mon":
                    if(mon!=1)
                    {
                        loop_check_weekly();
                    }
                    else
                    {
                        Date dtweek = new Date();
                        DateFormat dfdtweek = new SimpleDateFormat("yyyy-MM-dd");
                        String dbdtweek = dfdtweek.format(dtweek);
                        pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                        pstmtup.setString(1, dbdtweek);
                        pstmtup.setInt(2, id);
                        pstmtup.executeUpdate();
                    }
                    break;
                case "Tue":
                    if(tue!=1)
                    {
                        loop_check_weekly();
                    }
                    else
                    {
                        Date dtweek = new Date();
                        DateFormat dfdtweek = new SimpleDateFormat("yyyy-MM-dd");
                        String dbdtweek = dfdtweek.format(dtweek);
                        pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                        pstmtup.setString(1, dbdtweek);
                        pstmtup.setInt(2, id);
                        pstmtup.executeUpdate();
                    }
                    break;
                case "Wed":
                    if(wed!=1)
                    {
                        loop_check_weekly();
                    }
                    else
                    {
                        Date dtweek = new Date();
                        DateFormat dfdtweek = new SimpleDateFormat("yyyy-MM-dd");
                        String dbdtweek = dfdtweek.format(dtweek);
                        pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                        pstmtup.setString(1, dbdtweek);
                        pstmtup.setInt(2, id);
                        pstmtup.executeUpdate();
                    }
                    break;
                case "Thu":
                    if(thu!=1)
                    {
                        loop_check_weekly();
                    }
                    else
                    {
                        Date dtweek = new Date();
                        DateFormat dfdtweek = new SimpleDateFormat("yyyy-MM-dd");
                        String dbdtweek = dfdtweek.format(dtweek);
                        pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                        pstmtup.setString(1, dbdtweek);
                        pstmtup.setInt(2, id);
                        pstmtup.executeUpdate();
                    }
                    break;
                case "Fri":
                    if(fri!=1)
                    {
                        loop_check_weekly();
                    }
                    else
                    {
                        Date dtweek = new Date();
                        DateFormat dfdtweek = new SimpleDateFormat("yyyy-MM-dd");
                        String dbdtweek = dfdtweek.format(dtweek);
                        pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                        pstmtup.setString(1, dbdtweek);
                        pstmtup.setInt(2, id);
                        pstmtup.executeUpdate();
                    }
                    break;
                case "Sat":
                    if(sat!=1)
                    {
                        loop_check_weekly();
                    }
                    else
                    {
                        Date dtweek = new Date();
                        DateFormat dfdtweek = new SimpleDateFormat("yyyy-MM-dd");
                        String dbdtweek = dfdtweek.format(dtweek);
                        pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                        pstmtup.setString(1, dbdtweek);
                        pstmtup.setInt(2, id);
                        pstmtup.executeUpdate();
                    }
                    break;
            }
        //}
    }
    
    public static void date_update() throws SQLException, AWTException, IOException
    {
        int once = rs.getInt(6);
        int daily = rs.getInt(7);
        int weekly = rs.getInt(8);
        int monthly = rs.getInt(9);
        int annually = rs.getInt(10);
        
        if(once == 1 )
        {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String dt = rs.getString(3);
            int hh = rs.getInt(4);
            int mm = rs.getInt(5);
            String desc = rs.getString(18);
            String eid = rs.getString(19);
            String mobno = rs.getString(20);
            
            if(eid==null)
            {
                eid = "event.remainder159@gmail.com";
            }
            if(dt.equals(dtd) & hh==hhi & mm==mmi)
            {
                Eve_Rem se= new Eve_Rem();
                se.notification(title,desc);
                se.Send(eid,title,desc);
                se.smsSend(mobno,desc);
                pstmtup = conn.prepareStatement("Delete from eve_remi.set_remi where id=?");
                pstmtup.setInt(1, id);
                pstmtup.executeUpdate();
            }
        }
        else if(daily == 1)
        {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String ddt = rs.getString(3);
            int hh = rs.getInt(4);
            int mm = rs.getInt(5);
            String desc = rs.getString(18);
            String eid = rs.getString(19);
            String mobno = rs.getString(20);
            
            if(eid==null)
            {
                eid = "event.remainder159@gmail.com";
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            dbdt = sdf.format(cal.getTime());
            
            if(ddt.equals(dtd) & hh==hhi & mm==mmi)
            {
                Eve_Rem se= new Eve_Rem();
                se.notification(title,desc);
                se.Send(eid,title,desc);
                se.smsSend(mobno,desc);
                pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                pstmtup.setString(1, dbdt);
                pstmtup.setInt(2, id);
                pstmtup.executeUpdate();
            }
            
            if(dumdaily1==0)
            {
                Date dt1 = null;
                Date dt = new Date();
                SimpleDateFormat dfdt = new SimpleDateFormat("dd/MM/yyyy");
                try
                {
                    dt1 = dfdt.parse(dfdt.format(dt));
                }
                catch(java.text.ParseException e)
                { }
                Date dt2 = rs.getDate(3);
                
                if(dt1.compareTo(dt2)>0)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    Date dtdaily = new Date();
                    DateFormat dfdtdaily = new SimpleDateFormat("yyyy-MM-dd");
                    String dbdtdaily = dfdtdaily.format(dtdaily);
                    pstmtup.setString(1, dbdtdaily);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(dt1.compareTo(dt2)==0 & hhi>hh)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    Date dtdaily = new Date();
                    DateFormat dfdtdaily = new SimpleDateFormat("yyyy-MM-dd");
                    String dbdtdaily = dfdtdaily.format(dtdaily);
                    pstmtup.setString(1, dbdtdaily);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(dt1.compareTo(dt2)==0 & hhi==hh & mmi>mm)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    Date dtdaily = new Date();
                    DateFormat dfdtdaily = new SimpleDateFormat("yyyy-MM-dd");
                    String dbdtdaily = dfdtdaily.format(dtdaily);
                    pstmtup.setString(1, dbdtdaily);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                dumdaily1=1;
            }
        }
        else if (weekly == 1)
        {
            String title = rs.getString(2);
            String dt = rs.getString(3);
            int hh = rs.getInt(4);
            int mm = rs.getInt(5);
            String desc = rs.getString(18);
            String eid = rs.getString(19);
            String mobno = rs.getString(20);
            if(eid==null)
            {
                eid = "event.remainder159@gmail.com";
            }
            
            if(dt == null ? dtd == null : dt.equals(dtd))
            {
                if(hh==hhi & mm==mmi)
                {
                    Eve_Rem se= new Eve_Rem();
                    se.notification(title,desc);
                    se.Send(eid,title,desc);
                    se.smsSend(mobno,desc);
                    dum = 0;
                    loop_check_weekly();
                }
            }
            if(dumweekly==0)
            {
                Date dt1 = null;
                Date dtt = new Date();
                SimpleDateFormat dfdt = new SimpleDateFormat("dd/MM/yyyy");
                try
                {
                    dt1 = dfdt.parse(dfdt.format(dtt));
                }
                catch(java.text.ParseException e)
                { }
                Date dt2 = rs.getDate(3);

                if(dt1.compareTo(dt2)>0)
                {
                    check_weekly();
                }
                else if(dt1.compareTo(dt2)==0 & hhi>hh)
                {
                    check_weekly();
                }
                else if(dt1.compareTo(dt2)==0 & hhi==hh & mmi>mm)
                {
                    check_weekly();
                }
                dumweekly = 1;
            }
        }
        else if (monthly == 1)
        {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String dt = rs.getString(3);
            Date ddt = rs.getDate(3);
            int hh = rs.getInt(4);
            int mm = rs.getInt(5);
            String desc = rs.getString(18);
            String eid = rs.getString(19);
            String mobno = rs.getString(20);
            if(eid==null)
            {
                eid = "event.remainder159@gmail.com";
            }
            
            SimpleDateFormat sdfyyyy = new SimpleDateFormat("yyyy");
            String syyyy = sdfyyyy.format(ddt);
            int iyyyy = Integer.parseInt(syyyy);
            SimpleDateFormat sdfMM = new SimpleDateFormat("MM");
            String sMM = sdfMM.format(ddt);
            int iMM = Integer.parseInt(sMM);
            SimpleDateFormat sdfdd = new SimpleDateFormat("dd");
            String sdd = sdfdd.format(ddt);
            int idd = Integer.parseInt(sdd);

            if(dt == null ? dtd == null : dt.equals(dtd))
            {
                if(hh==hhi & mm==mmi)
                {
                    Eve_Rem se= new Eve_Rem();
                    se.notification(title,desc);
                    se.Send(eid,title,desc);
                    se.smsSend(mobno,desc);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = new GregorianCalendar();
                    cal.add(Calendar.MONTH, 1);
                    dbdt = sdf.format(cal.getTime());

                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, dbdt);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                    
                }
            }
            if(dummonthly == 0)
            {
                SimpleDateFormat dsdf = new SimpleDateFormat("yyyy-MM-");
                Calendar dcal = new GregorianCalendar();
                dcal.add(Calendar.MONTH, 1);
                String ddbdt = dsdf.format(dcal.getTime());
                if(yyyyi>iyyyy)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(yyyyi==iyyyy & MMi>iMM)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(yyyyi==iyyyy & MMi==iMM & dayi>idd)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(yyyyi==iyyyy & MMi==iMM & dayi==idd & hhi>hh)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(yyyyi==iyyyy & MMi==iMM & dayi==idd & hhi==hh & mmi>mm)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                dummonthly = 1;
            }
        }
        else if (annually == 1)
        {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String dt = rs.getString(3);
            Date ddt = rs.getDate(3);
            int hh = rs.getInt(4);
            int mm = rs.getInt(5);
            String desc = rs.getString(18);
            String eid = rs.getString(19);
            String mobno = rs.getString(20);
            if(eid==null)
            {
                eid = "event.remainder159@gmail.com";
            }
            
            SimpleDateFormat sdfyyyy = new SimpleDateFormat("yyyy");
            String syyyy = sdfyyyy.format(ddt);
            int iyyyy = Integer.parseInt(syyyy);
            SimpleDateFormat sdfdd = new SimpleDateFormat("dd");
            String sdd = sdfdd.format(ddt);
            int idd = Integer.parseInt(sdd);
            SimpleDateFormat sdfMM = new SimpleDateFormat("MM");
            String sMM = sdfMM.format(ddt);
            int iMM = Integer.parseInt(sMM);
            
            if(dt == null ? dtd == null : dt.equals(dtd))
            {
                if(hh==hhi & mm==mmi)
                {
                    Eve_Rem se= new Eve_Rem();
                    se.notification(title,desc);
                    se.Send(eid,title,desc);
                    se.smsSend(mobno,desc);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = new GregorianCalendar();
                    cal.add(Calendar.YEAR, 1);
                    dbdt = sdf.format(cal.getTime());

                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, dbdt);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                    
                }
            }
            if(dumannually == 0)
            {
                SimpleDateFormat dsdf = new SimpleDateFormat("yyyy-");
                Calendar dcal = new GregorianCalendar();
                dcal.add(Calendar.YEAR, 1);
                String ddbdt = dsdf.format(dcal.getTime());
                if(yyyyi>iyyyy)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sMM+"-"+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(yyyyi==iyyyy & MMi>iMM)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sMM+"-"+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(yyyyi==iyyyy & MMi==iMM & dayi>idd)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sMM+"-"+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(yyyyi==iyyyy & MMi==iMM & dayi==idd& hhi>hh)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sMM+"-"+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                else if(yyyyi==iyyyy & MMi==iMM & dayi==idd & hhi==hh & mmi>mm)
                {
                    pstmtup = conn.prepareStatement("update eve_remi.set_remi set db_dteChooser=? where id=?");
                    pstmtup.setString(1, ddbdt+sMM+"-"+sdd);
                    pstmtup.setInt(2, id);
                    pstmtup.executeUpdate();
                }
                dumannually = 1;
            }
        }
    }
    
    public static void check_reminder() throws SQLException, AWTException, IOException
    {
        try
        {
            if(dumrs==0)
            {
                while(rs.next())
                {
                    date_update();
                    dum = 0;
                    dumdaily1=0;
                    dumweekly=0;
                    dummonthly=0;
                    dumannually=0;
                    if(rs.isLast())
                    {
                        dum = 1;
                        dumdaily1=1;
                        dumweekly=1;
                        dummonthly=1;
                        dumannually=1;
                        dumrs=1;
                    }
                }
            }
            else
            {
                rs.beforeFirst();
                while(rs.next())
                    date_update();
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        } 
    }
    
    public static String pubSMSAPI = null;
    /**
     * @param args the command line arguments
     * @throws java.awt.AWTException
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws AWTException, SQLException, ClassNotFoundException {
        set_Task.main(args);
        
        ResultSet rs1 = null;
        Connection conn1 = null;
        int yesno = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn1 = DriverManager.getConnection("jdbc:mysql:///eve_remi", "root", "Vaibhav159");
            PreparedStatement stmtse = conn1.prepareStatement("Select * from eve_remi.smsapi");
            rs1 = stmtse.executeQuery();
            if(rs1.next())
            {
                yesno = rs1.getInt(2);
                pubSMSAPI = rs1.getString(1);
            }
            if(yesno==0)
                SMS_API.main(args);
        } catch (SQLException ex) { }
    
        Timer timer = new Timer();
        TimerTask task = new TimerTask() 
        {
            @Override
            public void run() 
            {
                Date dt2 = new Date();
                DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd");
                
                DateFormat dftmhh = new SimpleDateFormat("HH");
                DateFormat dftmmm = new SimpleDateFormat("mm");
                DateFormat dftday = new SimpleDateFormat("dd");
                DateFormat dftMM = new SimpleDateFormat("MM");
                DateFormat dftyyyy = new SimpleDateFormat("yyyy");
                String shh = dftmhh.format(dt2);
                String smm = dftmmm.format(dt2);
                String sday = dftday.format(dt2);
                String sMM = dftMM.format(dt2);
                MMi = Integer.parseInt(sMM);
                String syyyy = dftyyyy.format(dt2);
                yyyyi = Integer.parseInt(syyyy);
                
                dayi = Integer.parseInt(sday);
                if (daych != dayi)
                {
                    dtd = dfdt.format(dt2);
                    dumrs=0;
                }
                daych = dayi;
                hhi = Integer.parseInt(shh);
                if (hhch != hhi)
                {
                 
                }
                hhch = hhi;
                mmi = Integer.parseInt(smm);
                if (mmch != mmi)
                {
                    try 
                    {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection("jdbc:mysql:///eve_remi","root","Vaibhav159");
                        pstmtse = conn.prepareStatement("Select * from eve_remi.set_remi");
                        rs = pstmtse.executeQuery();
                        check_reminder();
                    }
                    catch (SQLException | AWTException | ClassNotFoundException | IOException ex) {
                        Logger.getLogger(Eve_Rem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                mmch = mmi;
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    
}