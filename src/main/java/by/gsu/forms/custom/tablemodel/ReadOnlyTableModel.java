package by.gsu.forms.custom.tablemodel;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ReadOnlyTableModel extends DefaultTableModel {

    private List<Integer> editableColumns;

    public ReadOnlyTableModel(Object[][] data, Object[] columnNames, int... editableColumns) {
        super(data, columnNames);
        this.editableColumns = new ArrayList<>();
        for (int editableColumn : editableColumns) {
            this.editableColumns.add(editableColumn);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return editableColumns.contains(column);
    }

}