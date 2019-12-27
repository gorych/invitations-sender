package by.gsu.views;

import by.gsu.dao.TemplateDaoImpl;
import by.gsu.service.TemplateService;
import by.gsu.service.impl.TemplateServiceImpl;

import javax.swing.*;

public class MainForm {

    private JPanel mainPanel;

    private JTabbedPane tabs;
    private JPanel usersTab;
    private JPanel eventsTab;
    private JPanel templatesTab;
    private JPanel userEventsTab;
    private JTable usersTable;
    private JTable templatesTable;

    private TemplateService templateService;

    public MainForm() {
        this.templateService = new TemplateServiceImpl(new TemplateDaoImpl());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}