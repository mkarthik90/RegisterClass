package main;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by anirudh on 28/10/14.
 */
public class SendEmail {

    public static void main(String args[]) {

          final String username = "mkarthik90@gmail.com";
          final String password = "motherBHUVANA1990";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        
        props.put("mail.smtp.starttls.enable","false");
        props.put("mail.smtp.starttls.required", "false");
        props.put("mail.smtp.sasl.enable", "false");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mkarthik90@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("mkarthik90@gmail.com"));//To change to receipeint
            message.setSubject("Test JCG Example");
            message.setText("Hi," +
                    "This is a Test mail for JCG Example!");

            Transport.send(message);

            System.out.println("Mail sent succesfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
