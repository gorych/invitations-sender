package by.gsu;

import by.gsu.forms.MainForm;
import by.gsu.mail.sender.impl.GmailMailSender;
import by.gsu.util.FormUtil;

public class Runner {

    public static void main(String[] args) {
       // FormUtil.openForm(new MainForm());

        new GmailMailSender().send("gorych911@gmail.com", "Приглашение на мероприятие", "");
    }

}