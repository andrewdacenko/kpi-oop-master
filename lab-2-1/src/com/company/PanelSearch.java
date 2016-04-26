package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

class PanelSearch extends JDialog {

    ArrayList<Student> tableDataList = new ArrayList<>();

    public PanelSearch(JFrame owner, ArrayList<Student> list) {
        super(owner, "Search", true);

        JButton buttonSearch1 = new JButton("Best students of second course");
        JButton buttonSearch2 = new JButton("Good students from foreign countries");

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

        buttonSearch1.addActionListener(e -> {
            tableDataList = (ArrayList<Student>) MyClientThread.communicate(Actions.GET_SECOND_BEST);
            table.revalidate();
            table.repaint();
        });

        buttonSearch2.addActionListener(e -> {
            tableDataList = (ArrayList<Student>) MyClientThread.communicate(Actions.GET_GOOD_FOREIGN);
            table.revalidate();
            table.repaint();
        });


        JPanel panelTop1 = new JPanel();
        panelTop1.add(buttonSearch1);
        JPanel panelTop2 = new JPanel();
        panelTop2.add(buttonSearch2);

        JPanel panel = new JPanel();
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(panelTop1, BorderLayout.NORTH);
        panelTop.add(panelTop2, BorderLayout.SOUTH);
        panel.add(panelTop);
        panel.add(scrollPane);
        add(panel);
        setBounds(300, 300, 566, 310);
        setResizable(false);
    }
}
