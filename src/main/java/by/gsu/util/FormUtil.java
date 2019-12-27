package by.gsu.util;

import by.gsu.forms.AbstractForm;
import lombok.experimental.UtilityClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

@UtilityClass
public class FormUtil {

    public static void openForm(AbstractForm form) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame(form.getTitle());
            frame.setMinimumSize(new Dimension(form.getWidth(), form.getHeight()));
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    form.onCloseAction();
                    e.getWindow().dispose();
                }
            });
        });
    }

}