package com.postop.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 *
 */
public class MailUtil {

    private String toEmail;
    private String newPass;
    private String name;

    /**
     * @param toEmail
     * @param newPass
     * @param name
     */
    public MailUtil(String toEmail, String newPass, String name) {
        this.toEmail = toEmail;
        this.newPass = newPass;
        this.name = name;
    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    public boolean sendEmail() throws UnsupportedEncodingException {

        final String username = "oosegroup19@gmail.com";
        final String password = "oose2017group19";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "PostOp"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Welcome to Postop");
            message.setText("Dear " + name + ","
                    + "\n\n Your account has been created. Use the following credentials to login to your account"
                    + "\n Email: " + toEmail
                    + "\n Password: " + newPass
                    + "\n\n Regards,"
                    + "\n PostOp Team");

            Transport.send(message);

            System.out.println("Mail sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}