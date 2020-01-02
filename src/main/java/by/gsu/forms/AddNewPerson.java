package by.gsu.forms;

import by.gsu.dao.impl.PersonDaoImpl;
import by.gsu.model.Person;
import by.gsu.service.PersonService;
import by.gsu.service.impl.PersonServiceImpl;
import org.jooq.tools.StringUtils;

import javax.swing.*;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static javax.swing.JOptionPane.*;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class AddNewPerson extends AbstractForm {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final String TITLE = "Добавление нового адресата";
    private static final int PREF_WIDTH = 350;
    private static final int PREF_HEIGHT = 300;

    private JPanel mainPanel;

    private JTextField textFieldLastName;
    private JTextField textFieldFirstName;
    private JTextField textFieldMiddleName;
    private JTextField textFieldEmail;

    private JButton buttonAdd;

    private PersonService personService;

    private MainForm mainForm;

    public AddNewPerson(MainForm mainForm) {
        this.mainForm = mainForm;
        this.personService = new PersonServiceImpl(new PersonDaoImpl());

        Supplier<Stream<JTextField>> textFieldsStream = () -> Arrays.stream(mainPanel.getComponents())
                .filter(component -> component instanceof JTextField)
                .map(JTextField.class::cast);

        textFieldsStream.get().forEach(textField -> textField.setColumns(15));

        buttonAdd.addActionListener(e -> {
            long notFilledFieldsCount = textFieldsStream.get()
                    .filter(textField -> StringUtils.isEmpty(textField.getText()))
                    .count();

            if (notFilledFieldsCount > 0) {
                JOptionPane.showMessageDialog(mainPanel, "Заполните все поля!", "Ошибка", ERROR_MESSAGE);
                return;
            }

            boolean isValidEmail = EMAIL_REGEX.matcher(textFieldEmail.getText()).matches();
            if (!isValidEmail) {
                JOptionPane.showMessageDialog(mainPanel, "Электронный адрес заполнен неверно!", "Ошибка", ERROR_MESSAGE);
                return;
            }

            personService.add(new Person.Builder()
                    .setFirstName(textFieldLastName.getText())
                    .setMiddleName(textFieldFirstName.getText())
                    .setLastName(textFieldMiddleName.getText())
                    .setEmail(textFieldEmail.getText())
                    .build());

            JOptionPane.showMessageDialog(mainPanel, "Запись успешно добавлена", "Информация", INFORMATION_MESSAGE);
            mainForm.initPeopleTable();
        });
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
        mainForm.initPeopleTable();
    }
}
