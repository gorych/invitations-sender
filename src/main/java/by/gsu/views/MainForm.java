package by.gsu.views;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    private static final int PREF_WIDTH = 700;
    private static final int PREF_HEIGHT = 500;

    private JPanel mainPanel;
    private JTabbedPane tabs;
    private JPanel usersTab;
    private JPanel eventsTab;
    private JPanel templatesTab;
    private JPanel userEventsTab;

    public MainForm(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_WIDTH, PREF_HEIGHT);
    }

}
