package com.andrewdacenko.elements;

import javax.swing.*;

public class StudentsTable extends JTable {
    private ScoresCellRenderer cellRenderer;

    public StudentsTable(StudentsTableModel stm) {
        super(stm);

        setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        cellRenderer = new ScoresCellRenderer();
        getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
    }
}
