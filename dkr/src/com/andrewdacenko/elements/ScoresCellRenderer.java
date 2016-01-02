package com.andrewdacenko.elements;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ScoresCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        value = table.getModel().getValueAt(table.convertRowIndexToModel(row), 5);

        try {
            Double score = Double.parseDouble(value.toString().substring(0, 3));

            if (score < 2) {
                component.setBackground(Color.red);
            } else if (score < 4) {
                component.setBackground(Color.orange);
            } else {
                component.setBackground(Color.green);
            }

        } catch (Exception e) {
            component.setBackground(component.getBackground());
        }

        return component;
    }
}
