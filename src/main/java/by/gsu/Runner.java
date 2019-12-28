package by.gsu;

import by.gsu.mail.sender.impl.GmailMailSender;

import java.util.HashMap;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        // FormUtil.openForm(new MainForm());

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("day", 15);
        dataModel.put("month", "ЯНВАРЬ");
        dataModel.put("firstName", "Максим");
        dataModel.put("lastName", "Сергеевич");

        new GmailMailSender().send("gorych911@gmail.com", "Приглашение на мероприятие", dataModel);
    }

}