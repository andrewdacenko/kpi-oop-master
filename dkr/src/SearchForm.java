import com.andrewdacenko.elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchForm extends JDialog {
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTable studentsTable;

    StudentsTableModel studentsModel;

    SearchForm(String caption, Object[][] students) {
        Dimension dimension = new Dimension(600, 400);
        setSize(dimension);
        setTitle(caption);
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
            studentsModel.addRow(student);
        }

        pack();
        setVisible(true);
    }

    private void createUIComponents() {
        JDialog owner = this;
        studentsModel = new StudentsTableModel(null, Database.columns);
        studentsTable = new StudentsTable(studentsModel);
        studentsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                int r = studentsTable.rowAtPoint(e.getPoint());
                if (r >= 0 && r < studentsTable.getRowCount()) {
                    studentsTable.setRowSelectionInterval(r, r);
                } else {
                    studentsTable.clearSelection();
                }

                int selectedRow = studentsTable.getSelectedRow();
                int modelRow = studentsTable.convertRowIndexToModel(selectedRow);

                if (modelRow < 0) {
                    return;
                }

                if (e.getClickCount() == 2) {
                    StudentDetails.showDetails(owner, studentsModel, modelRow);
                }
            }
        });
    }
}
