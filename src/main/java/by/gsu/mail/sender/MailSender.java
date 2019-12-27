package by.gsu.mail.sender;

import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class MailSender {

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

    public void send() {
        Session session = Session.getInstance(MAIL_PROPERTIES,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        String username = MAIL_PROPERTIES.getProperty("mail.username");
                        String password = MAIL_PROPERTIES.getProperty("mail.password");
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("app.invitation.sender@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("gorych911@gmail.com")
            );
            message.setSubject("yahor test 2");
            message.setText("Dear Yahor,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);
        } catch (MessagingException e) {
            log.error("Error sending email", e);
        }
    }
}