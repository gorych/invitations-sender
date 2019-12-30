package by.gsu.forms;

import by.gsu.dao.impl.EventDaoImpl;
import by.gsu.dao.impl.PersonDaoImpl;
import by.gsu.dao.util.DbManager;
import by.gsu.forms.custom.ButtonRender;
import by.gsu.forms.custom.InvitationButtonEditor;
import by.gsu.forms.custom.tablemodel.CheckBoxColumnTableModel;
import by.gsu.model.Event;
import by.gsu.model.Person;
import by.gsu.service.EventService;
import by.gsu.service.PersonService;
import by.gsu.service.impl.EventServiceImpl;
import by.gsu.service.impl.PersonServiceImpl;
import by.gsu.util.FormUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ObjIntConsumer;

import static by.gsu.util.DateTimeUtil.DD_MM_YYYY_FORMATTER;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainForm extends AbstractForm {

    private static final int PREF_WIDTH = 700;
    private static final int PREF_HEIGHT = 500;
    private static final String TITLE = "Отправитель приглашений на мероприятия";

    private static final String SELECTION_COLUMN_NAME = "Выбор";
    private static final String NUMBER_COLUMN_NAME = "№";
    private static final String ACTION_COLUMN_NAME = "Действие";
    private static final String HIDDEN_COLUMN_NAME = "Hidden";

    private JPanel mainPanel;
    private JTabbedPane tabs;

    private JPanel peopleTab;
    private JPanel eventsTab;

    private JTable peopleTable;
    private JTable eventsTable;

    private JButton addPersonButton;
    private JButton addEventButton;

    private JButton deletePeopleButton;
    private JButton deleteEventsButton;

    private EventService eventService;
    private PersonService personService;

    private final Map<Component, Runnable> onSelectTabActions;

    public MainForm() {
        peopleTable.setRowSelectionAllowed(true);
        eventsTable.setRowSelectionAllowed(true);

        onSelectTabActions = new HashMap<>();
        onSelectTabActions.put(peopleTab, this::initPeopleTable);
        onSelectTabActions.put(eventsTab, this::initEventTable);

        eventService = new EventServiceImpl(new EventDaoImpl());
        personService = new PersonServiceImpl(new PersonDaoImpl());

        initPeopleTable();
        initEventTable();

        tabs.addChangeListener(e -> {
            Component selectedTab = tabs.getSelectedComponent();
            Runnable action = onSelectTabActions.get(selectedTab);
            action.run();
        });

        addPersonButton.addActionListener(e -> FormUtil.openForm(new AddNewPerson(this)));
        addEventButton.addActionListener(e -> FormUtil.openForm(new AddNewEvent(this)));

        deletePeopleButton.addActionListener(e -> {
            deletePeople();
            initPeopleTable();
        });
        deleteEventsButton.addActionListener(e -> {
            deleteEvents();
            initEventTable();
        });
    }

    private void deletePeople() {
        deleteEntity(peopleTable, "Не выбераны адресаты для удаления!", (tableModel, rowIndex) -> {
            Person person = (Person) tableModel.getValueAt(rowIndex, 6);
            personService.delete(person);
        });
    }

    private void deleteEvents() {
        deleteEntity(eventsTable, "Не выбераны мероприятия для удаления!", (tableModel, rowIndex) -> {
            Event event = (Event) tableModel.getValueAt(rowIndex, 6);
            eventService.delete(event);
        });

    }

    private void deleteEntity(JTable table, String errorMessage, ObjIntConsumer<TableModel> deleteAction) {
        TableModel tableModel = table.getModel();

        int checkedRowCount = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            boolean isCheckedRow = Boolean.parseBoolean(String.valueOf(tableModel.getValueAt(i, 0)));
            if (isCheckedRow) {
                checkedRowCount++;
                deleteAction.accept(tableModel, i);
            }
        }

        if (checkedRowCount == 0) {
            JOptionPane.showMessageDialog(mainPanel, errorMessage, "Предупреждение", WARNING_MESSAGE);
        }
    }

    public void initPeopleTable() {
        String[] columnNames = new String[]{SELECTION_COLUMN_NAME, NUMBER_COLUMN_NAME, "Фамилия", "Имя", "Отчество", "Электронная почта", HIDDEN_COLUMN_NAME};

        List<Person> people = personService.getAll();
        Object[][] data = new Object[people.size()][columnNames.length];
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            data[i][0] = false;
            data[i][1] = i + 1;
            data[i][2] = person.getLastName();
            data[i][3] = person.getFirstName();
            data[i][4] = person.getMiddleName();
            data[i][5] = person.getEmail();
            data[i][6] = person;
        }

        this.peopleTable.setModel(new CheckBoxColumnTableModel(data, columnNames, 0));
        setColumnSizes(this.peopleTable);
    }

    public void initEventTable() {
        String[] columnNames = new String[]{SELECTION_COLUMN_NAME, NUMBER_COLUMN_NAME, "Название", "Описание", "Дата проведения", ACTION_COLUMN_NAME, HIDDEN_COLUMN_NAME};
        List<Event> events = eventService.getAll();

        Object[][] data = new Object[events.size()][columnNames.length];
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            data[i][0] = false;
            data[i][1] = i + 1;
            data[i][2] = event.getName();
            data[i][3] = event.getDescription();
            data[i][4] = event.getDate().format(DD_MM_YYYY_FORMATTER);
            data[i][5] = "Отправить приглашение";
            data[i][6] = event;
        }

        this.eventsTable.setModel(new CheckBoxColumnTableModel(data, columnNames, 0, 5));
        setColumnSizes(this.eventsTable);

        TableColumn actionColumn = this.eventsTable.getColumn(ACTION_COLUMN_NAME);
        actionColumn.setCellRenderer(new ButtonRender());
        actionColumn.setCellEditor(new InvitationButtonEditor(
                event -> FormUtil.openForm(new InvitationForm(event))));
    }

    private void setColumnSizes(JTable peopleTable) {
        TableColumn selectionColumn = peopleTable.getColumn(SELECTION_COLUMN_NAME);
        selectionColumn.setWidth(70);
        selectionColumn.setMaxWidth(70);

        TableColumn numberColumn = peopleTable.getColumn(NUMBER_COLUMN_NAME);
        numberColumn.setWidth(30);
        numberColumn.setMaxWidth(30);

        TableColumn hiddenColumn = peopleTable.getColumn(HIDDEN_COLUMN_NAME);
        hiddenColumn.setWidth(0);
        hiddenColumn.setMinWidth(0);
        hiddenColumn.setMaxWidth(0);
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
    public int getDefaultCloseOperation() {
        return EXIT_ON_CLOSE;
    }

    @Override
    public void onCloseAction() {
        DbManager.closeConnection();
    }

}