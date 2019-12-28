package by.gsu.mail.sender.impl;

import by.gsu.mail.sender.MailSender;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static freemarker.template.Configuration.VERSION_2_3_28;

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
        try {
            Configuration cfg = new Configuration(VERSION_2_3_28);
            cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/"));
            cfg.setDefaultEncoding("UTF-8");
            Template temp = cfg.getTemplate("templates/template.ftl");

            Map<String, Object> root = new HashMap<>();
            root.put("firstName", "Андрей");
            root.put("lastName", "Иванов");

            StringWriter stringWriter = new StringWriter();
            temp.process(root, stringWriter);

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

            MimeMultipart multipart = new MimeMultipart("related");

            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(stringWriter.toString(), "text/html;charset=UTF-8");

            multipart.addBodyPart(bodyPart);

            bodyPart = new MimeBodyPart();
            URL resource = this.getClass().getClassLoader().getResource("templates/img/background.jpg");
            Objects.requireNonNull(resource);

            File file = new File(resource.getFile());
            DataSource fds = new FileDataSource(file);

            bodyPart.setDataHandler(new DataHandler(fds));
            bodyPart.setHeader("Content-ID", "<cid:background>");

            multipart.addBodyPart(bodyPart);

            bodyPart = new MimeBodyPart();
            resource = this.getClass().getClassLoader().getResource("templates/img/wheel.png");
            Objects.requireNonNull(resource);

            file = new File(resource.getFile());
            fds = new FileDataSource(file);

            bodyPart.setDataHandler(new DataHandler(fds));
            bodyPart.setHeader("Content-ID", "<cid:wheel>");

            multipart.addBodyPart(bodyPart);

            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            log.error("Error sending email", e);
            return false;
        }
    }
}