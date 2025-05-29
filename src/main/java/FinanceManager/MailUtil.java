package FinanceManager;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sumingfei
 */
public class MailUtil {
    private static final String SMTP_HOST = "smtp.example.com";
    private static final String SMTP_USER = "your@address.com";
    private static final String SMTP_PASS = "yourPassword";

    private static Session makeSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", "587");
        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASS);
            }
        });
    }

    public static void send(String to, String subject, String body) throws MessagingException {
        Message msg = new MimeMessage(makeSession());
        msg.setFrom(new InternetAddress(SMTP_USER));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(body);
        Transport.send(msg);
    }
    
}
