package by.gsu.forms.custom;

import javax.swing.*;
import java.awt.*;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String label;
    private boolean isPressed;
    private transient Runnable onPressAction;

    public ButtonEditor(Runnable onPressAction) {
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
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPressed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPressed) {
            onPressAction.run();
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

