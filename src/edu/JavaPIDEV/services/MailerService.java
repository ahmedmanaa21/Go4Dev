package edu.JavaPIDEV.services;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author majdi
 */
public class MailerService {
//    /**public static void sendEmail(String address, String subject, String message) throws Exception{
//                        
//            String from="testahmedahmed210@gmail.com";
//            String pass = "CampersDen210";
//            String[] to = {address};
//            String host="smtp.gmail.com";
//            
//            Properties prop = System.getProperties();
//            prop.put("mail.smtp.starttls.enable","true");
//            prop.put("mail.smtp.host",host);
//            prop.put("mail.smtp.user",from);
//            prop.put("mail.smtp.password",pass);
//            prop.put("mail.smtp.port", "587");
//            prop.put("mail.smtp.auth","true");
//            
//            Session session = Session.getDefaultInstance(prop);
//            MimeMessage msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(from));
//            
//            InternetAddress[] toaddress = new InternetAddress[to.length];
//            for(int i=0;i<to.length;i++){
//                toaddress[i] = new InternetAddress(to[i]);
//            }
//            for(int i=0;i<toaddress.length;i++){
//                msg.setRecipient(Message.RecipientType.TO, toaddress[i]);
//            }
//            msg.setSubject(subject);
//            msg.setContent(message,"test/html ; charset=utf-8");
//            msg.setText(message,"UTF-8");
//            Transport transport=session.getTransport("smtp");
//            transport.connect(host,from,pass);
//            transport.sendMessage(msg,msg.getAllRecipients());
//            transport.close();
//     * @param toEmailAddress;*
//     * @param emailMessage
//     * @return
//     * @throws java.lang.Exception */
//                
//           //  private static final Logger logger=LoggerFactory.getLogger(Forgetpassword.class);
//    
//            public String sendEmail(String toEmailAddress, String emailMessage) throws Exception {
//            String userName = "testahmedahmed210@gmail.com";
//            String password = "CampersDen210";
//            String emailSubject = "Default Password";
////            String emailMessage = getPassword();
//            try {
//            // Use javamail api, set parameters from registration.properties file
//            // set the session properties
//            Properties props = System.getProperties();
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.port", "587");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.required", "true");
//            Session session = Session.getDefaultInstance(props, null);
//            // Create email message
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(userName));
//            String[] recipientList = toEmailAddress.split(",");
//            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
//            int counter = 0;
//            for (String recipient: recipientList) {
//                recipientAddress[counter] = new InternetAddress(recipient.trim());
//                counter++;
//            }
//            message.setRecipients(Message.RecipientType.TO, recipientAddress);
//            message.setSubject(emailSubject);
//            message.setContent(emailMessage, "test/html");
//            // Send smtp message
//            Transport tr = session.getTransport("smtp");
//            tr.connect("smtp.gmail.com", 587, userName, password);
//            message.saveChanges();
//            tr.sendMessage(message, message.getAllRecipients());
//            tr.close();
//            return emailMessage;
//            } catch (MessagingException e) {
//                return "Error in method sendEmailNotification: " + e;
//            }
//            
//        }
        public void replyMail(String mail ,String Username , String Description) {
        String from = "testahmedahmed210@gmail.com";
        String pass = "CampersDen210";
        String[] to = {""+ mail}; // list of recipient email addresses
        String subject = "AIDE REPLY";
        String body = Description ;
        MailService.sendFromGMail(from,pass,to,subject,body);
    }
    public static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (InternetAddress toAddres : toAddress) {
                message.addRecipient(Message.RecipientType.TO, toAddres);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        }
        catch (AddressException ae) {
        }
        catch (MessagingException me) {
        }
    }

public String getPassword()
    {
        String temp = Long.toHexString(Double.doubleToLongBits(Math.random()));
        return temp;
    }


//new 
//public static void mailingValider(String recipient, int nombre) throws Exception {
//
//        Properties prop = new Properties();
//        final String moncompteEmail = "testahmedahmed210@gmail.com";
//        String password = "CampersDen210";
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true");
//
//        Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {
//
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(moncompteEmail, password);
//            }
//        });
//
//        try {
//
//            Message msg = new MimeMessage(ses);
//            msg.setFrom(new InternetAddress(moncompteEmail));
//            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//            msg.setSubject("Code de confirmation");
//            msg.setText("Merci pour votre Interet a CamperDen , voici votre code de confirmation:   "+String.valueOf(nombre));
//
//            Transport.send(msg);
//
//        } catch (MessagingException ex) {
//            Logger.getLogger(MailerService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}

