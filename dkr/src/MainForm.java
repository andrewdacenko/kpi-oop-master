import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
                new AddForm(this, db.studentsTableModel);
                break;
            case ButtonsCommands.BEST:
                showBestStudents();
                break;
            case ButtonsCommands.GOOD:
                showGoodStudents();
                break;

            case ButtonsCommands.REMOVE:
                if (studentsTable.getSelectedRow() >= 0) {
                    db.studentsTableModel.removeRow(studentsTable.getSelectedRow());
                }
                break;
        }
    }

    private void showBestStudents() {
        Object[][] bestStudents = db.studentsTableModel.getBestStudents();

        System.out.println(bestStudents);
        new SearchForm(this, "Only the Best", bestStudents);
    }

    private void showGoodStudents() {
        Object[][] goodStudents = db.studentsTableModel.getGoodStudents();

        System.out.println(goodStudents);
        new SearchForm(this, "Only Good", goodStudents);
    }

    private void createUIComponents() {
        db = new Database();

        studentsTable = new JTable(db.studentsTableModel);
        studentsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("male");
        comboBox.addItem("female");

        TableColumn genderColumn = studentsTable.getColumnModel().getColumn(3);
        genderColumn.setCellEditor(new DefaultCellEditor(comboBox));

        TableColumn scoresColumn = studentsTable.getColumnModel().getColumn(5);
        scoresColumn.setCellRenderer(new ScoresCellRenderer());
    }
}
