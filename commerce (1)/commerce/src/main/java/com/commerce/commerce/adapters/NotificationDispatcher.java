package com.commerce.commerce.adapters;

import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service("Notifier")
public class NotificationDispatcher implements  Dispatcher{
    public void sendNotification(String destinationEmail, String destinationName, Double transferedTotal) throws Exception {
        try {
        final String username = "< your fromEmail here >";
        final String password = "< your application key here >";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, auth);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("< from email here>", "Adminstração do W-commerce"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationEmail, destinationName));
            msg.setSubject("Transferência de créditos.");
            msg.setText("Você recebeu uma transferencia no valor de: " + transferedTotal);
            Transport.send(msg);
        }catch(Exception e){
            throw new Exception("Houve um problema no envio da notificação: " + e.getMessage());
        }
    }
}
