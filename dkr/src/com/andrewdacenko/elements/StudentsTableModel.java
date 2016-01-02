package com.andrewdacenko.elements;

import com.andrewdacenko.structures.Student;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class StudentsTableModel extends DefaultTableModel {

    public StudentsTableModel(Object[][] data, Object[] columns) {
        super(data, columns);
    }

    public Class getColumnClass(int col) {
        Vector v = (Vector) dataVector.elementAt(0);
        return v.elementAt(col).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public Object[][] getData() {
        Object[][] data = new Object[getRowCount()][getColumnCount()];
        int rowCount = getRowCount();
        int columnCount = getColumnCount();

        for (int i = 0; i < rowCount; i++) {
            Object[] row = new Object[columnCount];

            for (int j = 0; j < columnCount; j++) {
                row[j] = getValueAt(i, j);
            }

            data[i] = row;
        }

        return data;
    }

    public Object[][] getBestStudents() {
        ArrayList<Object[]> students = new ArrayList<>();

        for (Object[] student : getData()) {
            Student s = new Student(student);

            if (s.isBest()) {
                students.add(student);
            }
        }

        return arrToObj(students);
    }

    public Object[][] getGoodStudents() {
        ArrayList<Object[]> students = new ArrayList<>();

        for (Object[] student : getData()) {
            Student s = new Student(student);

            if (s.isGood()) {
                students.add(student);
            }
        }

        return arrToObj(students);
    }

    private Object[][] arrToObj(ArrayList<Object[]> arrayList) {
        Object[][] result = new Object[arrayList.size()][];

        for (int i = 0; i < arrayList.size(); i++) {
            result[i] = arrayList.get(i);
        }

        return result;
    }
}
