import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    DefaultTableModel scoresModel;

    private boolean isOK;

    public AddForm() {
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

        scoresModel = new DefaultTableModel(null, columns);
        scoresTable = new JTable(scoresModel);
        scoresTable.setAutoCreateColumnsFromModel(true);

        scoresTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getClickCount() == 2) {
                    scoresModel.addRow(new Object[]{"", ""});
                    int row = scoresModel.getRowCount() - 1;
                    scoresTable.setRowSelectionInterval(row, row);
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

    public Object[] showDialog() {
        pack();
        setVisible(true);

        if (isOK)
            return new Object[]{
                    nameField.getText(),
                    idField.getText(),
                    courseField.getText(),
                    genderBox.getSelectedItem(),
                    countryField.getText(),
                    scoresModel.getDataVector()
            };
        else
            return null;
    }
}
