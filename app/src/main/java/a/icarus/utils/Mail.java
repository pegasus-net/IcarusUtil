package a.icarus.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("unused")
public class Mail {
    private static final String userName = "1573539596@qq.com";
    private static final String userPassword = "quvwqzhuwwakiiec";


    public static boolean send(String title, String text) {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        };
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.qq.com");
            properties.put("mail.smtp.port", 25);
            properties.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(properties, authenticator);
            Message message = new MimeMessage(session);
            Address from = new InternetAddress(userName);
            message.setFrom(from);
            Address to = new InternetAddress(userName);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject(title);
            message.setSentDate(new Date());
            message.setText(text);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
