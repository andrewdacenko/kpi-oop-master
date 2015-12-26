import javax.swing.*;
import java.awt.event.*;

public class SearchResults extends JDialog {
    private JList studentsList;
    private JPanel contentPanel;

    DefaultListModel model;

    SearchResults (String caption, Object[] list) {
        setContentPane(contentPanel);
        setModal(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        contentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setTitle(caption);

        for(Object o : list) {
            model.addElement(o);
        }

        pack();
    }

    private void onOK() {
        dispose();
    }

    private void createUIComponents() {
        model = new DefaultListModel();
        studentsList = new JList(model);
    }
}
