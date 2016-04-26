package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class PanelEdit extends JDialog {

    public PanelEdit(JFrame owner, ArrayList<Student> list, int id) {
        super(owner, "Edit", true);

        JLabel labelFirstName = new JLabel(Student.HEADER[0] + ":");
        JLabel labelLastName = new JLabel(Student.HEADER[1] + ":");
        JLabel labelMiddleName = new JLabel(Student.HEADER[2] + ":");
        JLabel labelId = new JLabel(Student.HEADER[3] + ":");
        JLabel labelCourse = new JLabel(Student.HEADER[4] + ":");
        JLabel labelCountry = new JLabel(Student.HEADER[5] + ":");
        JLabel labelGender = new JLabel(Student.HEADER[6] + ":");
        JLabel labelMark = new JLabel(Student.HEADER[7] + ":");

        int size = 12;

        Student studentToEdit = list.get(id);

        JTextField textFieldFirstName = new JTextField(studentToEdit.getFirst_name(), size);
        JTextField textFieldLastName = new JTextField(studentToEdit.getLast_name(), size);
        JTextField textFieldMiddleName = new JTextField(studentToEdit.getMiddle_name(), size);
        JTextField textFieldId = new JTextField(studentToEdit.getId(), size);
        JComboBox courseCombo = new JComboBox(Student.COURSES);
        courseCombo.setSelectedItem(studentToEdit.getCourse());

        JComboBox countryCombo = new JComboBox(Student.COUNTRIES);
        countryCombo.setSelectedItem(studentToEdit.getCountry());

        JComboBox genderCombo = new JComboBox(Student.GENDERS);
        genderCombo.setSelectedItem(studentToEdit.getGender());

        JComboBox markCombo = new JComboBox(Student.MARKS);
        markCombo.setSelectedItem(studentToEdit.getMark());

        JPanel panelTop = new JPanel(new GridLayout(4, 2));
        panelTop.add(labelFirstName);
        panelTop.add(textFieldFirstName);

        panelTop.add(labelLastName);
        panelTop.add(textFieldLastName);

        panelTop.add(labelMiddleName);
        panelTop.add(textFieldMiddleName);

        panelTop.add(labelId);
        panelTop.add(textFieldId);

        panelTop.add(labelCourse);
        panelTop.add(courseCombo);

        panelTop.add(labelCountry);
        panelTop.add(countryCombo);

        panelTop.add(labelGender);
        panelTop.add(genderCombo);

        panelTop.add(labelMark);
        panelTop.add(markCombo);


        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            setVisible(false);

            Student student = new Student();
            student.setFirst_name(textFieldFirstName.getText());
            student.setLast_name(textFieldLastName.getText());
            student.setMiddle_name(textFieldMiddleName.getText());
            student.setId(textFieldId.getText());
            student.setCourse(courseCombo.getSelectedItem().toString());
            student.setCountry(countryCombo.getSelectedItem().toString());
            student.setGender(genderCombo.getSelectedItem().toString());
            student.setMark(Double.valueOf(markCombo.getSelectedItem().toString()));

            list.set(id, student);

            MyClientThread.communicate(list);
        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> setVisible(false));

        JPanel panelBottom = new JPanel();
        panelBottom.add(ok);
        panelBottom.add(cancel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(panelTop, BorderLayout.CENTER);
        panel.add(panelBottom, BorderLayout.SOUTH);
        add(panel);
        setBounds(300, 300, 500, 230);
        setResizable(false);
    }
}
