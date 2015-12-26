import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;

public class AddForm extends JDialog {
    private JPanel addPanel;
    private JTextField nameField;
    private JTextField idField;
    private JTextField courseField;
    private JLabel fullNameLabel;
    private JLabel studentIdLabel;
    private JLabel courseLabel;
    private JLabel genderLabel;
    private JComboBox genderBox;
    private JTable scoresTable;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField countryField;
    private JLabel countryLabel;

    AbstractTableModel scoresModel;
    Scores scores;
    Object[][] oScores;
    JDialog self;

    private boolean isOK;

    public AddForm() {
        self = this;
        setContentPane(addPanel);
        setModal(true);
        getRootPane().setDefaultButton(okButton);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
    }

    private void createUIComponents() {
        String[] genders = new String[]{"male", "female"};
        genderBox = new JComboBox(genders);
        genderBox.setSelectedIndex(0);

        Object[] columns = new Object[]{
                "Discipline",
                "Score"
        };

        scores = new Scores();

        Object[] defaultObj = {"discipline", "5"};

        scores.add(new Score(defaultObj));

        oScores = new Object[][]{{"math", "2"}};

        scoresModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return scores.size();
            }

            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Score s = scores.get(rowIndex);

                return columnIndex == 0 ? s.discipline : s.mark;
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                Score s = scores.get(rowIndex);
                if (columnIndex == 0) {
                    s.discipline = (String) aValue;
                } else {
                    s.mark = Integer.parseInt((String) aValue);
                }
            }
        };

        scoresTable = new JTable(scoresModel);
        scoresTable.setAutoCreateColumnsFromModel(true);

        scoresTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getClickCount() == 2) {
                    scores.add(new Score(new Object[]{"new", "0"}));
                    self.repaint();
                }
            }
        });
    }

    private void onOK() {
        isOK = true;
        dispose();
    }

    private void onCancel() {
        isOK = false;
        dispose();
    }

    public Student showDialog() {
        pack();
        setVisible(true);

        if (isOK) {
            Student s = new Student(new Object[]{
                    nameField.getText(),
                    idField.getText(),
                    courseField.getText(),
                    genderBox.getSelectedItem(),
                    countryField.getText(),
                    scores
            });

            return s;
        } else
            return null;
    }
}
