package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class Mail {
    public static void outMail(String msg, String to, String subject) throws MessagingException, javax.mail.MessagingException {


        String from = "gajimax12345@gmail.com";
        String host = "localhost";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gajimax12345@gmail.com", "xxxxxxxxxx");  // have to change some settings in SMTP
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(msg);
        Transport.send(mimeMessage);

        System.out.println("Sent... " + to);
    }

    public static void outMail(String msg, ArrayList<String> to, String subject) throws MessagingException, javax.mail.MessagingException {
        for (String ele : to) {
            outMail(msg, ele, subject);
        }
    }

}
