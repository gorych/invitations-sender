package by.gsu.forms;

import by.gsu.dao.impl.EventDaoImpl;
import by.gsu.dao.impl.PersonDaoImpl;
import by.gsu.dao.util.DbManager;
import by.gsu.forms.custom.ButtonEditor;
import by.gsu.forms.custom.ButtonRender;
import by.gsu.model.Event;
import by.gsu.model.Person;
import by.gsu.service.EventService;
import by.gsu.service.PersonService;
import by.gsu.service.impl.EventServiceImpl;
import by.gsu.service.impl.PersonServiceImpl;
import by.gsu.util.FormUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.List;

public class MainForm extends AbstractForm {

    private static final int PREF_WIDTH = 700;
    private static final int PREF_HEIGHT = 500;
    private static final String TITLE = "Рассыльщик приложений на мероприятия";

    private static final String ACTION_COLUMN_NAME = "Действие";

    private JPanel mainPanel;
    private JTabbedPane tabs;
    private JTable peopleTable;
    private JTable eventsTable;

    private EventService eventService;
    private PersonService personService;

    public MainForm() {
        this.eventService = new EventServiceImpl(new EventDaoImpl());
        this.personService = new PersonServiceImpl(new PersonDaoImpl());

        initPersonTable();
        initEventTable();

        tabs.addChangeListener((event) -> {
            initPersonTable();
            initEventTable();
            //TODO MAP functions by index or FactoryMethod
        });
    }

    private void initPersonTable() {
        String[] columnNames = new String[]{"Идентификатор", "Имя", "Фамилия", "Электронная почта"};
        List<Person> people = personService.getAll();

        Object[][] data = new Object[people.size()][columnNames.length];
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            data[i][0] = person.getId();
            data[i][1] = person.getFirstName();
            data[i][2] = person.getLastName();
            data[i][3] = person.getEmail();
        }

        this.peopleTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private void initEventTable() {
        String[] columnNames = new String[]{"Идентификатор", "Название", "Описание", ACTION_COLUMN_NAME};
        List<Event> events = eventService.getAll();

        Object[][] data = new Object[events.size()][columnNames.length];
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            data[i][0] = event.getId();
            data[i][1] = event.getName();
            data[i][2] = event.getDescription();
            data[i][3] = "Отправить приглашение";
        }

        this.eventsTable.setModel(new DefaultTableModel(data, columnNames));

        TableColumn actionColumn = this.eventsTable.getColumn(ACTION_COLUMN_NAME);
        actionColumn.setCellRenderer(new ButtonRender());
        actionColumn.setCellEditor(new ButtonEditor(
                () -> FormUtil.openForm(new InvitationForm(new Event()))));
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
        DbManager.closeConnection();
    }

}