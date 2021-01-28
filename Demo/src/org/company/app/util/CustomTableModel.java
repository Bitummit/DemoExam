package org.company.app.util;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class CustomTableModel<T> extends AbstractTableModel
{
    private Class<T> cls;
    private List<T> values;//наши данные

    public CustomTableModel(Class<T> cls, List<T> values) {
        this.cls = cls;
        this.values = values;
    }

    @Override
    public int getRowCount() {
        return this.values.size();
    }

    @Override
    public int getColumnCount() {
        return this.cls.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Field field = this.cls.getDeclaredFields()[columnIndex];
            field.setAccessible(true);
            return field.get(values.get(rowIndex));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.cls.getDeclaredFields()[column].getName();

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.cls. getDeclaredFields()[columnIndex].getType();
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
