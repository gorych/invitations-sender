package by.gsu.mail.sender;

public interface MailSender {

    boolean send(String recipients, String subject, String text);

}