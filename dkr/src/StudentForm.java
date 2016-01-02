import com.andrewdacenko.elements.*;
import com.andrewdacenko.exceptions.*;
import com.andrewdacenko.structures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentForm extends JDialog {
    private JPanel addPanel;
    private JTextField nameField;
    private JComboBox<String> genderBox;
    private JTable scoresTable;
    private JButton okButton;
    private JButton cancelButton;
    private JComboBox<String> countryBox;
    private JScrollPane tableScroll;
    private JSpinner course;
    private JSpinner studentId;
    private JLabel fullNameLabel;
    private JLabel studentIdLabel;
    private JLabel genderLabel;
    private JLabel courseLabel;
    private JLabel countryLabel;
    private Dimension dimension = new Dimension(600, 400);
    private StudentsTableModel studentsTableModel;
    private Object[] columns;
    private int row = -1;

    StudentsTableModel scoresTableModel;

    public StudentForm(JFrame owner, StudentsTableModel studentsTableModel, int rowIndex) {
        super(owner, "Edit", true);
        row = rowIndex;

        Object[] student = studentsTableModel.getData()[rowIndex];
        nameField.setText(student[0].toString());
        studentId.setValue(Integer.parseInt((String) student[1]));
        course.setValue(student[2]);
        genderBox.setSelectedItem(student[3]);
        countryBox.setSelectedItem(student[4]);

        int rowCount = scoresTableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            scoresTableModel.removeRow(i);
        }

        for (Score score : (Scores) student[5]) {
            Object[] row = new Object[]{
                    score.discipline,
                    score.mark.toString()
            };

            scoresTableModel.addRow(row);
        }

        setupForm(studentsTableModel);
    }

    public StudentForm(JFrame owner, StudentsTableModel studentsTableModel) {
        super(owner, "Add", true);
        setupForm(studentsTableModel);
    }

    private void setupForm(StudentsTableModel students) {
        studentsTableModel = students;

        setSize(dimension);
        setLocationRelativeTo(null);
        tableScroll.setPreferredSize(dimension);

        setContentPane(addPanel);
        setModal(true);
        getRootPane().setDefaultButton(okButton);

        okButton.addActionListener(e -> addStudent());

        cancelButton.addActionListener(e -> dispose());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        addPanel.registerKeyboardAction(
                e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );

        tableScroll.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getClickCount() == 2) {
                    scoresTableModel.addRow(DataGenerator.generateScore());
                }
            }
        });

        pack();
        setVisible(true);
    }

    private void createUIComponents() {
        boolean isMale = DataGenerator.random.nextBoolean();
        setRandomName(isMale);
        createStudentIdSpinner();
        createCourseSpinner();
        createGenderComboBox(isMale);
        createCountryComboBox();
        createScoresTable();
    }

    private void setRandomName(boolean isMale) {
        nameField = new JTextField(DataGenerator.generateName(isMale));
    }

    private void createScoresTable() {
        columns = new Object[]{
                "Discipline",
                "Score"
        };

        scoresTableModel = new StudentsTableModel(new Object[][]{
                DataGenerator.generateScore()
        }, columns);
        scoresTable = new JTable(scoresTableModel);
        scoresTable.setAutoCreateColumnsFromModel(true);
    }

    private void createGenderComboBox(boolean isMale) {
        String[] genders = new String[]{"male", "female"};
        genderBox = new JComboBox<>(genders);
        genderBox.setSelectedIndex(isMale ? 0 : 1);
    }

    private void createCountryComboBox() {
        countryBox = new JComboBox<>(DataGenerator.countries);
        countryBox.setSelectedIndex(DataGenerator.random.nextInt(DataGenerator.countries.length));
    }

    private void createCourseSpinner() {
        String[] courses = {"1", "2", "3", "4", "5"};
        SpinnerListModel courseModel = new SpinnerListModel(courses);
        course = new JSpinner(courseModel);
        courseModel.setValue(courses[DataGenerator.random.nextInt(courses.length)]);
    }

    private void createStudentIdSpinner() {
        SpinnerModel model =
                new SpinnerNumberModel(
                        DataGenerator.random.nextInt(999999), //initial value
                        0, //min
                        999999, //max
                        1 // step
                );

        studentId = new JSpinner(model);
    }

    private void addStudent() {
        try {
            Object[] student = new Object[]{
                    validateField(nameField.getText(), fullNameLabel.getText()),
                    studentId.getValue().toString(),
                    course.getValue(),
                    genderBox.getSelectedItem(),
                    countryBox.getSelectedItem(),
                    getScores()
            };

            Student s = new Student(student);

            if (row >= 0) {
                studentsTableModel.removeRow(row);
                studentsTableModel.insertRow(row, student);
            } else {
                studentsTableModel.addRow(student);
            }

            dispose();

        } catch (NumberFormatException e) {
            final String message = "<html><body>Field <b>"
                    + e.getMessage()
                    + "</b> must be numeric</body></html>";

            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException iae) {
            final String message = "<html><body>Field <b>"
                    + iae.getMessage()
                    + "</b> is <u>required</u></body></html>";

            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (MinNumberException mne) {
            final String message = "<html><body>Field <b>"
                    + mne.getMessage()
                    + "</b> should be not less than <u>"
                    + mne.getValue() + "</u></body></html>";
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (MaxNumberException mne) {
            final String message = "<html><body>Field <b>"
                    + mne.getMessage()
                    + "</b> should be not greater than <u>"
                    + mne.getValue() + "</u></body></html>";
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private Scores getScores() throws MinNumberException, MaxNumberException {
        Scores scores = new Scores();

        Object[][] data = scoresTableModel.getData();

        for (int i = 0; i < data.length; i++) {
            Object[] score = data[i];

            int row = scoresTable.convertRowIndexToView(i);
            String prefix = " in " + (row + 1) + " row";

            score[0] = validateField((String) score[0], columns[0] + prefix);
            score[1] = validateMinMax((String) score[1], columns[1] + prefix, 1, 5);
            scores.add(new Score(score));
        }

        return scores;
    }

    private String validateField(String input, String field) throws IllegalArgumentException {
        if (input.equals("")) {
            throw new IllegalArgumentException(field);
        }

        return input;
    }

    private String validateNumberField(String input, String field) throws NumberFormatException {
        input = validateField(input, field);

        try {
            return String.valueOf(Integer.parseInt(input));
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(field);
        }
    }

    private String validateMinMax(String input, String field, int min, int max) throws MinNumberException, MaxNumberException {
        input = validateNumberField(input, field);

        int value = Integer.parseInt(input);

        if (value < min) {
            throw new MinNumberException(field, min);
        }
        if (value > max) {
            throw new MaxNumberException(field, max);
        }

        return input;
    }

    ;
}
