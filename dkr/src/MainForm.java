import com.andrewdacenko.elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainForm extends JFrame implements ActionListener {
    private JTable studentsTable;
    private JButton goodButton;
    private JButton addButton;
    private JButton editButton;
    private JButton bestButton;
    private JButton removeButton;
    private JPanel mainPanel;
    private JScrollPane tableScroll;
    private Dimension dimension = new Dimension(1200, 800);

    private Database db;

    public MainForm() {
        setSize(dimension);
        setLocationRelativeTo(null);
        tableScroll.setPreferredSize(dimension);
        setupGUI();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void setupGUI() {
        addButton.addActionListener(this);
        addButton.setActionCommand(ButtonsCommands.ADD);

        editButton.addActionListener(this);
        editButton.setActionCommand(ButtonsCommands.EDIT);

        removeButton.addActionListener(this);
        removeButton.setActionCommand(ButtonsCommands.REMOVE);

        bestButton.addActionListener(this);
        bestButton.setActionCommand(ButtonsCommands.BEST);

        goodButton.addActionListener(this);
        goodButton.setActionCommand(ButtonsCommands.GOOD);

        mainPanel.setPreferredSize(dimension);
        add(mainPanel);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ButtonsCommands.ADD:
                new StudentForm(this, db.studentsTableModel);
                break;
            case ButtonsCommands.BEST:
                showBestStudents();
                break;
            case ButtonsCommands.GOOD:
                showGoodStudents();
                break;
            case ButtonsCommands.REMOVE:
                int selectedRow = studentsTable.getSelectedRow();
                int modelRow = studentsTable.convertRowIndexToModel(selectedRow);

                if (modelRow >= 0) {
                    db.studentsTableModel.removeRow(modelRow);
                }
                break;
        }
    }

    private void showBestStudents() {
        Object[][] bestStudents = db.studentsTableModel.getBestStudents();
        new SearchForm(this, "Only the Best", bestStudents);
    }

    private void showGoodStudents() {
        Object[][] goodStudents = db.studentsTableModel.getGoodStudents();
        new SearchForm(this, "Only Good", goodStudents);
    }

    private void createUIComponents() {
        JFrame owner = this;

        db = new Database();

        studentsTable = new StudentsTable(db.studentsTableModel);
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
                    StudentDetails.showDetails(owner, db.studentsTableModel, modelRow);
                    return;
                }

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = createRowPopup(selectedRow, modelRow);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    private void editRow(int selectedRow, int modelRow) {
        JDialog form = new StudentForm(this, db.studentsTableModel, modelRow);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                studentsTable.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
    }

    private JPopupMenu createRowPopup(int selectedRow, int modelRow) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem editItem = new JMenuItem("Edit");
        editItem.addActionListener(e -> editRow(selectedRow, modelRow));
        menu.add(editItem);

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(e -> db.studentsTableModel.removeRow(modelRow));
        menu.add(deleteItem);

        return menu;
    }
}
