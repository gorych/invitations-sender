package by.gsu.forms;

import by.gsu.dao.impl.PersonDaoImpl;
import by.gsu.dao.impl.TemplateDaoImpl;
import by.gsu.model.Event;
import by.gsu.model.Person;
import by.gsu.model.Template;
import by.gsu.service.PersonService;
import by.gsu.service.TemplateService;
import by.gsu.service.impl.PersonServiceImpl;
import by.gsu.service.impl.TemplateServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class InvitationForm extends AbstractForm {

    private static final int PREF_WIDTH = 400;
    private static final int PREF_HEIGHT = 400;
    private static final String TITLE = "Приглашение на мероприятие";

    private JPanel mainPanel;
    private JComboBox<Template> templatesComboBox;
    private JButton sendInvite;
    private JTable peopleTable;

    private Event event;

    private PersonService personService;
    private TemplateService templateService;

    public InvitationForm(Event event) {
        this.event = event;

        this.personService = new PersonServiceImpl(new PersonDaoImpl());
        this.templateService = new TemplateServiceImpl(new TemplateDaoImpl());

        List<Template> templates = templateService.getAll();
        templates.forEach(templatesComboBox::addItem);

        initPersonTable();
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

        this.peopleTable.setModel(new DefaultTableModel(data, columnNames));
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
