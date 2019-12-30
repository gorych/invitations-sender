package by.gsu.forms;

import by.gsu.dao.impl.EventDaoImpl;
import by.gsu.model.Event;
import by.gsu.service.EventService;
import by.gsu.service.impl.EventServiceImpl;
import org.jooq.tools.StringUtils;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static by.gsu.util.DateTimeUtil.DD_MM_YYYY_FORMATTER;
import static javax.swing.JOptionPane.*;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class AddNewEvent extends AbstractForm {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}.\\d{2}.\\d{4}");
    private static final int TEXT_FIELD_WIDTH = 15;

    private static final String TITLE = "Добавление нового мероприятия";
    private static final int PREF_WIDTH = 370;
    private static final int PREF_HEIGHT = 300;

    private JPanel mainPanel;

    private JTextField textFieldName;
    private JTextField textFieldDescription;
    private JTextField textFieldDate;

    private JButton buttonAdd;

    private EventService eventService;

    private MainForm mainForm;

    public AddNewEvent(MainForm mainForm) {
        this.mainForm = mainForm;
        this.eventService = new EventServiceImpl(new EventDaoImpl());
        this.textFieldDate.setText(LocalDate.now().format(DD_MM_YYYY_FORMATTER));

        Supplier<Stream<JTextField>> textFieldsStream = () -> Arrays.stream(mainPanel.getComponents())
                .filter(component -> component instanceof JTextField)
                .map(JTextField.class::cast);

        textFieldsStream.get().forEach(textField -> textField.setColumns(TEXT_FIELD_WIDTH));

        buttonAdd.addActionListener(e -> {
            long notFilledFieldsCount = textFieldsStream.get()
                    .filter(textField -> StringUtils.isEmpty(textField.getText()))
                    .count();

            if (notFilledFieldsCount > 0) {
                JOptionPane.showMessageDialog(mainPanel, "Заполните все поля!", "Ошибка", ERROR_MESSAGE);
                return;
            }

            if (!isDateCorrect()) {
                JOptionPane.showMessageDialog(mainPanel, "Дата введена некорректно! Введите дату в формате 31.12.2019", "Ошибка", ERROR_MESSAGE);
                return;
            }

            eventService.add(new Event.Builder()
                    .setName(textFieldName.getText())
                    .setDescription(textFieldDescription.getText())
                    .setDate(textFieldDate.getText())
                    .build());

            JOptionPane.showMessageDialog(mainPanel, "Запись успешно добавлена", "Информация", INFORMATION_MESSAGE);
            mainForm.initEventTable();
        });
    }

    private boolean isDateCorrect() {
        boolean isValidDate = DATE_PATTERN.matcher(textFieldDate.getText()).matches();
        if (!isValidDate) {
            return false;
        }

        try {
            LocalDate.parse(textFieldDate.getText(), DD_MM_YYYY_FORMATTER);
        } catch (Exception ex) {
            return false;
        }

        return true;
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
        return DO_NOTHING_ON_CLOSE;
    }

    @Override
    public void onCloseAction() {
        mainForm.initEventTable();
    }
}
