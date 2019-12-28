package by.gsu.mail.sender.impl;

import by.gsu.mail.sender.MailSender;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

import static freemarker.template.Configuration.VERSION_2_3_28;

@Slf4j
public class GmailMailSender implements MailSender {

    private static final Properties MAIL_PROPERTIES;
    private static final Template TEMPLATE;

    static {
        MAIL_PROPERTIES = loadProperties();
        TEMPLATE = configureTemplate();
    }

    private static Template configureTemplate() {
        Configuration cfg = new Configuration(VERSION_2_3_28);
        cfg.setTemplateLoader(new ClassTemplateLoader(GmailMailSender.class, "/"));
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        try {
            return cfg.getTemplate(MAIL_PROPERTIES.getProperty("mail.template.name"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("mail.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return properties;
    }

    @Override
    public boolean send(String recipients, String subject, Map<String, Object> dataModel) {
        try {
            StringWriter stringWriter = new StringWriter();
            TEMPLATE.process(dataModel, stringWriter);

            Session session = Session.getInstance(MAIL_PROPERTIES,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            String username = MAIL_PROPERTIES.getProperty("mail.username");
                            String password = MAIL_PROPERTIES.getProperty("mail.password");
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL_PROPERTIES.getProperty("mail.username")));
            message.setRecipients(RecipientType.TO, InternetAddress.parse(recipients));
            message.setSubject(subject);
            message.setContent(stringWriter.toString(), "text/html;charset=UTF-8");

            Transport.send(message);

            return true;
        } catch (Exception e) {
            log.error("Error sending email", e);
            return false;
        }
    }
}