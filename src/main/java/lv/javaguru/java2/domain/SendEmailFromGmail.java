package lv.javaguru.java2.domain;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailFromGmail {

    private String recipient;
    private String password;

    String from = "gamblingloginfo@gmail.com";
    private final String gmailUsername = "gamblingloginfo";
    private final String gmailPassword = "Qwerty123$";

    Properties props = new Properties();

    public SendEmailFromGmail(String recipient, String password){
        this.recipient = recipient;
        this.password = password;
    }

    private void setProperties(){
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    private Session getSession() {
        return Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(gmailUsername, gmailPassword);
                    }
                });
    }

    public void composeAndSendEmail() throws MessagingException {
        setProperties();
        try{
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Your new password to GamblingLog!");
            message.setText("Your new password: " + password);
            Transport.send(message);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
