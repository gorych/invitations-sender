package by.gsu.forms;

import by.gsu.dao.impl.PersonDaoImpl;
import by.gsu.forms.custom.tablemodel.InvitationFormTableModel;
import by.gsu.mail.sender.MailSender;
import by.gsu.mail.sender.impl.GmailMailSender;
import by.gsu.model.Event;
import by.gsu.model.Person;
import by.gsu.service.PersonService;
import by.gsu.service.impl.PersonServiceImpl;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class InvitationForm extends AbstractForm {

    private static final MailSender MAIL_SENDER = new GmailMailSender();
    private static final String MAIL_SUBJECT = "Приглашение на мероприятие";

    private static final int PREF_WIDTH = 700;
    private static final int PREF_HEIGHT = 500;
    private static final String TITLE = "Отправка приглашения на мероприятие: ";

    private JPanel mainPanel;
    private JButton sendInvite;
    private JTable peopleTable;

    private PersonService personService;

    private Event event;

    public InvitationForm(Event event) {
        this.event = event;
        this.personService = new PersonServiceImpl(new PersonDaoImpl());
        initPersonTable();

        sendInvite.addActionListener(e -> sendInvitations(event));
    }

    private void sendInvitations(Event event) {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("eventDay", event.getDay());
        dataModel.put("eventMonth", event.getMonth());
        dataModel.put("eventName", event.getName().toUpperCase());
        dataModel.put("eventDescription", event.getDescription());

        TableModel tableModel = peopleTable.getModel();
        int rowCount = tableModel.getRowCount();

        int checkedRowCount = 0;
        for (int i = 0; i < rowCount; i++) {
            boolean isCheckedRow = Boolean.parseBoolean(String.valueOf(tableModel.getValueAt(i, 0)));
            if (isCheckedRow) {
                checkedRowCount++;

                String recipientFirstName = String.valueOf(tableModel.getValueAt(i, 2));
                String recipientMiddleName = String.valueOf(tableModel.getValueAt(i, 3));
                String recipientEmail = String.valueOf(tableModel.getValueAt(i, 4));

                dataModel.put("recipientFirstName", recipientFirstName);
                dataModel.put("recipientMiddleName", recipientMiddleName);

                CompletableFuture.runAsync(() -> MAIL_SENDER.send(recipientEmail, MAIL_SUBJECT, dataModel));
            }
        }

        if (checkedRowCount == 0) {
            JOptionPane.showMessageDialog(mainPanel, "Не выбраны люди для отправки приглашения!", "Предупреждение", WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(mainPanel, "Приглашения успешно отправлены", "Информация", INFORMATION_MESSAGE);
    }

    private void initPersonTable() {
        String[] columnNames = new String[]{"Выбор", "Фамилия", "Имя", "Отчество", "Электронная почта"};
        List<Person> people = personService.getAll();

        Object[][] data = new Object[people.size()][columnNames.length];
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            data[i][0] = false;
            data[i][1] = person.getLastName();
            data[i][2] = person.getFirstName();
            data[i][3] = person.getMiddleName();
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
        return TITLE + event.getName();
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
    public int getDefaultCloseOperation() {
        return EXIT_ON_CLOSE;
    }

    @Override
    public void onCloseAction() {
        //do nothing
    }
}
