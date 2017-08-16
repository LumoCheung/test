package cn.paypalm.automailstat;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年5月19日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年5月19日	新建文件.
 * 
 * </pre>
 */
public class Sendmail {

    /**
     * @param args
     * @throws Exception 
     */
    public static void send(String date,String flag) throws Exception {    
    	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties prop = System.getProperties();
        prop.setProperty("mail.host", "smtp.exmail.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.port", "465");
        prop.setProperty("mail.smtp.auth", "true");
//        prop.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);  
//        prop.setProperty("mail.imap.socketFactory.port", "993");  
//        prop.setProperty("mail.store.protocol","imap");    
//        prop.setProperty("mail.imap.host", "imap.exmail.qq.com");    
//        prop.setProperty("mail.imap.port", "993");    
//        prop.setProperty("mail.imap.auth.login.disable", "true");        
        prop.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);  
//        prop.setProperty("mail.pop3.socketFactory.port", "995");  
        prop.setProperty("mail.store.protocol","pop3s");
        prop.setProperty("mail.pop3s.host", "pop.exmail.qq.com");    
        prop.setProperty("mail.pop3s.port", "995");
        prop.setProperty("mail.pop3s.auth.login.disable", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        //Session session = Session.getInstance(prop);
        Session session = Session.getDefaultInstance(prop, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MerConfig.getValueAt("username"), MerConfig.getValueAt("password"));
            }});
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(false);
	

	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	   if("".equals(date)){
		   Calendar calendar = Calendar.getInstance();
		   calendar.setTime(new Date());
		   calendar.add(Calendar.DATE, -1);
		   date=sdf.format(calendar.getTime());
	   }
	   String text=ReciveOneMail.receive(date,session,flag);
       // -- Create a new message --
	   Message msg = new MimeMessage(session);
	   // -- Set the FROM and TO fields --
	   msg.setFrom(new InternetAddress(MerConfig.getValueAt("username")));
	   msg.setSubject(date+"预警邮件汇总");
	   if(text==null){
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MerConfig.getValueAt("username"),false));
		   msg.setText("获取的预警邮件数为0，请检查预警邮件，如有误，使用手动发送方式发送。");
	   }
	   else{
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MerConfig.getValueAt("senders"),false));
		   msg.setText(text);
	   }
	   System.out.println(text);
	   msg.setSentDate(new Date());
	   Transport.send(msg);

	   System.out.println("Message sent.");
    }
}