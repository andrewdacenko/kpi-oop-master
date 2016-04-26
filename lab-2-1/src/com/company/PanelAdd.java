package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class PanelAdd extends JDialog {

    public PanelAdd(JFrame owner, ArrayList<Student> list) {
        super(owner, "Add", true);

        JLabel labelFirstName = new JLabel(Student.HEADER[0] + ":");
        JLabel labelLastName = new JLabel(Student.HEADER[1] + ":");
        JLabel labelMiddleName = new JLabel(Student.HEADER[2] + ":");
        JLabel labelId = new JLabel(Student.HEADER[3] + ":");
        JLabel labelCourse = new JLabel(Student.HEADER[4] + ":");
        JLabel labelCountry = new JLabel(Student.HEADER[5] + ":");
        JLabel labelGender = new JLabel(Student.HEADER[6] + ":");
        JLabel labelMark = new JLabel(Student.HEADER[7] + ":");
        int size = 12;
        JTextField textFieldFirstName = new JTextField(size);
        JTextField textFieldLastName = new JTextField(size);
        JTextField textFieldMiddleName = new JTextField(size);
        JTextField textFieldId = new JTextField(size);
        JComboBox courseCombo = new JComboBox(Student.COURSES);
        JComboBox countryCombo = new JComboBox(Student.COUNTRIES);
        JComboBox genderCombo = new JComboBox(Student.GENDERS);
        JComboBox markCombo = new JComboBox(Student.MARKS);

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
            student.setLast_name(textFieldFirstName.getText());
            student.setFirst_name(textFieldLastName.getText());
            student.setMiddle_name(textFieldMiddleName.getText());
            student.setId(textFieldId.getText());
            student.setCourse(courseCombo.getSelectedItem().toString());
            student.setCountry(countryCombo.getSelectedItem().toString());
            student.setGender(genderCombo.getSelectedItem().toString());
            student.setMark(Double.valueOf(markCombo.getSelectedItem().toString()));

            textFieldFirstName.setText("");
            textFieldLastName.setText("");
            textFieldMiddleName.setText("");
            textFieldId.setText("");
            courseCombo.setSelectedIndex(0);
            countryCombo.setSelectedIndex(0);
            genderCombo.setSelectedIndex(0);
            markCombo.setSelectedIndex(0);

            list.add(student);
            MyClientThread.communicate(student);
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
        setBounds(500, 300, 475, 230);
        setResizable(false);
    }
}
