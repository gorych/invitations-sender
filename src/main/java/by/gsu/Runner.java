package by.gsu;

import by.gsu.dao.DbManager;
import by.gsu.views.MainForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Runner {

    private static final int PREF_WIDTH = 700;
    private static final int PREF_HEIGHT = 500;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Рассыльщик приложений на мероприятия");
            frame.setMinimumSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
            frame.setContentPane(new MainForm().getMainPanel());
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    DbManager.closeConnection();
                    e.getWindow().dispose();
                }
            });
        });
    }

}