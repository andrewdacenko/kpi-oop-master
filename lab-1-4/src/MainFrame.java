import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel rootPanel;
    private DrawField drawField;

    Timer timer;

    public MainFrame() {
        super("Lab 4");

        initializeComponents();

        this.setContentPane(rootPanel);
        this.addWindowListener(new Terminator());

        this.setLocation(10, 200);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initializeComponents() {

        drawField = new DrawField();
        GridLayout layout = new GridLayout(1, 1, 0, 0);

        rootPanel = new JPanel(layout);
        rootPanel.add(drawField);

        timer = new Timer(50, this);
        timer.setActionCommand("tick");
        timer.setRepeats(true);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        drawField.tick(this);
        drawField.repaint();
    }

    class Terminator extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}