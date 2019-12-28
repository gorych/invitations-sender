package by.gsu.forms;

import by.gsu.dao.impl.PersonDaoImpl;
import by.gsu.forms.custom.tablemodel.InvitationFormTableModel;
import by.gsu.mail.sender.impl.GmailMailSender;
import by.gsu.model.Event;
import by.gsu.model.Person;
import by.gsu.service.PersonService;
import by.gsu.service.impl.PersonServiceImpl;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvitationForm extends AbstractForm {

    private static final int PREF_WIDTH = 400;
    private static final int PREF_HEIGHT = 400;
    private static final String TITLE = "Отправка приглашения";

    private JPanel mainPanel;
    private JButton sendInvite;
    private JTable peopleTable;

    private Event event;

    private PersonService personService;

    public InvitationForm(Event event) {
        this.event = event;

        this.personService = new PersonServiceImpl(new PersonDaoImpl());

        initPersonTable();
        sendInvite.addActionListener(e -> {
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("day", 15);
            dataModel.put("month", "ЯНВАРЬ");
            dataModel.put("firstName", "Максим");
            dataModel.put("lastName", "Сергеевич");

            new GmailMailSender().send("gorych911@gmail.com", "Приглашение на мероприятие", dataModel);
        });
    }

    private void initPersonTable() {
        String[] columnNames = new String[]{"Выбор", "Идентификатор", "Имя", "Фамилия", "Электронная почта"};
        List<Person> people = personService.getAll();

        Object[][] data = new Object[people.size()][columnNames.length];
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            data[i][0] = false;
            data[i][1] = person.getId();
            data[i][2] = person.getFirstName();
            data[i][3] = person.getLastName();
            data[i][4] = person.getEmail();
        }

        this.peopleTable.setModel(new InvitationFormTableModel(data, columnNames, 0));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public int getWidth() {
        return PREF_WIDTH;
    }

    @Override
    public int getHeight() {
        return PREF_HEIGHT;
    }

    @Override
    public void onCloseAction() {
        //do nothing
    }
}
