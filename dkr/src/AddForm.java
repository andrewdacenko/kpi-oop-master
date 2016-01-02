import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddForm extends JDialog {
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

    StudentsTableModel scoresTableModel;
    JDialog self;

    public AddForm(JFrame owner, StudentsTableModel studentsTableModel) {
        super(owner, "Add", true);

        this.studentsTableModel = studentsTableModel;
        self = this;

        setSize(dimension);
        setLocationRelativeTo(null);
        tableScroll.setPreferredSize(dimension);

        setContentPane(addPanel);
        setModal(true);
        getRootPane().setDefaultButton(okButton);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        addPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void createUIComponents() {
        boolean isMale = DataGenerator.random.nextBoolean();
        setRandomName(isMale);
        createGenderComboBox(isMale);
        createCountryComboBox();
        createCourseSpinner();
        createStudentIdSpinner();
        createScoresTable();
    }

    private void setRandomName(boolean isMale) {
        nameField = new JTextField(DataGenerator.generateName(isMale));
    }

    private void createScoresTable() {
        Object[] columns = new Object[]{
                "Discipline",
                "Score"
        };

        scoresTableModel = new StudentsTableModel(new Object[][]{
                DataGenerator.generateScore()
        }, columns);
        scoresTable = new JTable(scoresTableModel);
        scoresTable.setAutoCreateColumnsFromModel(true);

        scoresTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getClickCount() == 2) {
                    scoresTableModel.addRow(DataGenerator.generateScore());
                }
            }
        });
    }

    private void createGenderComboBox(boolean isMale) {
        String[] genders = new String[]{"male", "female"};
        genderBox = new JComboBox<>(genders);
        genderBox.setSelectedIndex(isMale ? 0: 1);
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

    private void onCancel() {
        dispose();
    }

    private void addStudent() {
        Object[] oStudent = new Object[]{
                nameField.getText(),
                studentId.getValue().toString(),
                course.getValue(),
                genderBox.getSelectedItem(),
                countryBox.getSelectedItem(),
                getScores()
        };

        try {
            Student s = new Student(oStudent);
            studentsTableModel.addRow(oStudent);

            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "У полі " + e.getMessage() + " необхідно ввести числові дані");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private Scores getScores() {
        Scores scores = new Scores();

        for (Object[] score : scoresTableModel.getData()) {
            scores.add(new Score(score));
        }

        return scores;
    }
}
