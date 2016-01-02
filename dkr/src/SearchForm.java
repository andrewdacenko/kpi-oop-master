import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchForm extends JDialog {
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTable resultsTable;
    private Dimension dimension = new Dimension(600, 400);

    StudentsTableModel studentsTableModel;

    SearchForm(JFrame owner, String caption, Object[][] students) {
        super(owner, caption, true);
        setSize(dimension);
        setLocationRelativeTo(null);
        scrollPane.setPreferredSize(dimension);

        setContentPane(contentPanel);
        setModal(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPanel.registerKeyboardAction(
                e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );

        for (Object[] student : students) {
            studentsTableModel.addRow(student);
        }

        pack();
        setVisible(true);
    }

    private void createUIComponents() {
        studentsTableModel = new StudentsTableModel(null, Database.columns);
        resultsTable = new JTable(studentsTableModel);
    }
}
