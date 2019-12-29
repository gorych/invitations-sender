package by.gsu.forms.custom;

import by.gsu.model.Event;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class InvitationButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String label;
    private boolean isPressed;

    private transient Consumer<Event> onPressAction;
    private transient Event event;

    public InvitationButtonEditor(Consumer<Event> onPressAction) {
        super(new JCheckBox());
        this.button = new JButton();
        this.button.setOpaque(true);
        this.button.addActionListener(e -> fireEditingStopped());
        this.onPressAction = onPressAction;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }

        event = (Event) table.getModel().getValueAt(row, 4);

        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPressed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPressed) {
            onPressAction.accept(event);
        }
        isPressed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPressed = false;
        return super.stopCellEditing();
    }
}

