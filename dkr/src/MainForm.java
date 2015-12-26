import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MainForm extends JFrame implements ActionListener {
    private JTable studentsTable;
    private JButton goodButton;
    private JButton addButton;
    private JButton editButton;
    private JButton bestButton;
    private JButton removeButton;
    private JPanel mainPanel;

    private Database db;

    public MainForm() {
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

        add(mainPanel);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ButtonsCommands.ADD:
                Student result = new AddForm().showDialog();
                if (result != null) {
                    db.students.add(result);
                    this.repaint();
                }
                break;
            case ButtonsCommands.BEST:
                ArrayList<String> students = new ArrayList<String>();
                for (Object v : db.databaseResults) {
                    Object[] row = (Object[]) v;
                    Scores scores = (Scores) row[5];

                    if (scores.getAverage() >= 5) {
                        students.add((String) row[0]);
                    }
                }
                System.out.println(students);
                SearchResults sr = new SearchResults("Only the Best", students.toArray());
                sr.setVisible(true);
                break;

            case ButtonsCommands.GOOD:
                break;

            case ButtonsCommands.REMOVE:
                if (studentsTable.getSelectedRow() >= 0) {
                    db.students.remove(studentsTable.getSelectedRow());
                    this.repaint();
                }
                break;
        }
    }

    private void createUIComponents() {
        db = new Database();

        studentsTable = new JTable(db.defaultTableModel);

        TableColumn genderColumn = studentsTable.getColumnModel().getColumn(3);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("male");
        comboBox.addItem("female");
        genderColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }
}
