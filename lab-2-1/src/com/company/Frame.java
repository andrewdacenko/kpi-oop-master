package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Frame extends JFrame {
    JPanel panel;
    ArrayList<Student> tableDataList = new ArrayList<>();

    public Frame(String title) {
        this.setTitle(title);
        this.setBounds(300, 300, 800, 300);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);
        tableDataList = (ArrayList<Student>)MyClientThread.communicate(Actions.GET_ALL);
        createElements();
    }

    private void createElements() {
        TableModel tableModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return tableDataList.size();
            }

            @Override
            public int getColumnCount() {
                return Student.HEADER.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return tableDataList.get(rowIndex).getElementByIndex(columnIndex);
            }

            @Override
            public String getColumnName(int column) {
                return Student.HEADER[column];
            }
        };

        TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Double)
                    value = new DecimalFormat("#,###.##").format(value);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);


        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();

        PanelAdd panelAdd = new PanelAdd(this, tableDataList);
        final JButton buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(e -> panelAdd.setVisible(true));

        final JButton buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(e -> new PanelEdit(this, tableDataList, table.getSelectedRow()).setVisible(true));

        PanelSearch panelSearch = new PanelSearch(this, tableDataList);
        final JButton buttonSearch = new JButton("Search");
        buttonSearch.addActionListener(e -> panelSearch.setVisible(true));


        final JButton buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(
                e -> {
                    tableDataList.remove(table.getSelectedRow());
                    MyClientThread.communicate(tableDataList);
                    this.repaint();
                }
        );

        panelButtons.add(buttonAdd);
        panelButtons.add(buttonEdit);
        panelButtons.add(buttonSearch);
        panelButtons.add(buttonDelete);

        panel.add(panelButtons, BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                table.revalidate();
                table.repaint();
            }
        });
    }
}
