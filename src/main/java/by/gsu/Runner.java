package by.gsu;

import by.gsu.views.MainForm;

import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Runner {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainForm frame = new MainForm("Отправитель приглашений на мероприятия");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            frame.pack();
        });
    }

}