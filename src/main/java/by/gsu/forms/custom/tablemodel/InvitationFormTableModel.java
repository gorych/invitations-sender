package by.gsu.forms.custom.tablemodel;

public class InvitationFormTableModel extends ReadOnlyTableModel {

    public InvitationFormTableModel(Object[][] data, Object[] columnNames, int... editableColumns) {
        super(data, columnNames, editableColumns);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }


}