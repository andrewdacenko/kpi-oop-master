package com.andrewdacenko.elements;

import com.andrewdacenko.structures.Score;
import com.andrewdacenko.structures.Scores;

import javax.swing.*;

public class StudentDetails {
    public static void showDetails(JDialog owner, StudentsTableModel studentsTableModel, int modelRow) {
        String details = getDetails(studentsTableModel, modelRow);
        JOptionPane.showMessageDialog(owner, details, "Details", JOptionPane.PLAIN_MESSAGE);
    }

    public static void showDetails(JFrame owner, StudentsTableModel studentsTableModel, int modelRow) {
        String details = getDetails(studentsTableModel, modelRow);
        JOptionPane.showMessageDialog(owner, details, "Details", JOptionPane.PLAIN_MESSAGE);
    }

    private static String getDetails(StudentsTableModel studentsTableModel, int modelRow) {
        String details = "<html><body><ul>";

        Object[] row = studentsTableModel.getData()[modelRow];

        String scores = "<ol>";

        for (Score score : (Scores) row[5]) {
            scores += "<li>" + score.discipline + ": " + score.mark + "</li>";
        }

        scores += "</ol>";

        row[5] = (Object) scores;

        for (int i = 0; i < Database.columns.length; i++) {
            details += generateDetail(i, row);
        }

        details += "</ul></body></html>";
        return details;
    }

    private static String generateDetail(int index, Object[] row) {
        return "<li>" + Database.columns[index] + ": " + row[index] + "</li>";
    }

}
