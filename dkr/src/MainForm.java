import javax.swing.*;
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
                for (Object v : db.databaseResults) {//defaultTableModel.getDataVector().toArray()){
                    Object[] row = (Object[]) v;
                    if (Float.parseFloat((String) ((Object[]) row[5])[1]) > 4.5) {
                        System.out.println(row[0] + " " + row[0] + ": " + row[1]);
                        students.add((String) row[0]);
                    }
                }
//                new FindDialog("Average > 4.5", students.toArray()).show();
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
        studentsTable = new JTable(db.defaultTableModel);
    }
}
