package by.gsu.mail.sender;

import java.util.Map;

public interface MailSender {

    boolean send(String recipients, String subject, Map<String, Object> dataModel);

}