package by.gsu.mail.sender.impl;

import by.gsu.mail.sender.MailSender;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class GmailMailSender implements MailSender {

    private static final Properties MAIL_PROPERTIES;

    static {
        MAIL_PROPERTIES = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("mail.properties")) {
            MAIL_PROPERTIES.load(resourceStream);
        } catch (IOException e) {
            log.error("Error loading mail properties", e);
        }
    }

    @Override
    public boolean send(String recipients, String subject, String text) {
        Session session = Session.getInstance(MAIL_PROPERTIES,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        String username = MAIL_PROPERTIES.getProperty("mail.username");
                        String password = MAIL_PROPERTIES.getProperty("mail.password");
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL_PROPERTIES.getProperty("mail.username")));
            message.setRecipients(RecipientType.TO, InternetAddress.parse(recipients));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            log.error("Error sending email", e);
            return false;
        }
    }
}