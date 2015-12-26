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
                Object[] result = new AddForm().showDialog();
                if (result != null)
                    db.defaultTableModel.addRow(result);
                break;
            case ButtonsCommands.EDIT:
//                ArrayList<String> students = new ArrayList<String>();
//                for(Object v : db.databaseResults){//defaultTableModel.getDataVector().toArray()){
//                    Object[] row = (Object[])v;
//                    if( Float.parseFloat((String)row[5]) > 4.5) {
//                        System.out.println(row[0]+" "+row[5]);
//                        students.add((String)row[0]);
//                    }
//                }
//                new FindDialog("Average > 4.5", students.toArray()).show();
                break;
            case ButtonsCommands.BEST:
                ArrayList<String> students = new ArrayList<String>();
                for (Object v : db.databaseResults) {
                    Object[] row = (Object[]) v;
                    Object[][] scores = (Object[][]) row[5];

                    boolean onlyBest = true;

                    for(int score = 0; score < scores.length; score++) {
                        if (Integer.parseInt((String) scores[score][1]) != 5) {
                            onlyBest = false;
                            break;
                        }
                    }

                    if(onlyBest) {
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
                    db.defaultTableModel.removeRow(studentsTable.getSelectedRow());
                }
                break;
        }
    }

    private void createUIComponents() {
        db = new Database();

        studentsTable = new JTable(db.defaultTableModel) {
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == getColumnCount()) {
//                    return scoresRenderer;
                }

                return super.getCellRenderer(row, column);
            }
        };

        TableColumn genderColumn = studentsTable.getColumnModel().getColumn(3);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("male");
        comboBox.addItem("female");
        genderColumn.setCellEditor(new DefaultCellEditor(comboBox));

        TableColumn scoresColumn = studentsTable.getColumnModel().getColumn(5);
//
//        JTable scoresTable = new JTable();
//        scoresColumn.setCellEditor(new DefaultCellEditor(scoresTable));
    }
}
