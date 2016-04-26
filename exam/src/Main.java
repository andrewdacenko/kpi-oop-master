import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

public class Main extends Frame implements ActionListener {

    MenuBar menuBar;
    Menu menu;
    MenuItem sqrt2, sqrt3, sqrt4, sqrt5;
    Panel controlPanel;

    Label label;

    CanvasArea canvasArea;

    public Main() throws Exception {
        super("Real Numbers");
        setTitle("Real Numbers");
        setSize(300, 100);
        addWindowListener(new BasicWindowMonitor());
        setLayout(new GridLayout());
        setLocationRelativeTo(null);

        canvasArea = new CanvasArea();

        menuBar = new MenuBar();

        menu = new Menu("Real Numbers");

        sqrt2 = new MenuItem("2");
        sqrt2.addActionListener(this::sqrt2);

        sqrt3 = new MenuItem("-23");
        sqrt3.addActionListener(this::sqrt3);

        sqrt4 = new MenuItem("4");
        sqrt4.addActionListener(this::sqrt4);

        sqrt5 = new MenuItem("-175");
        sqrt5.addActionListener(this::sqrt5);

        menu.add(sqrt2);
        menu.add(sqrt3);
        menu.add(sqrt4);
        menu.add(sqrt5);

        menuBar.add(menu);

        setMenuBar(menuBar);

        controlPanel = new Panel();
        controlPanel.setLayout(new GridLayout());

        add(controlPanel);

        label = new Label();
        label.setAlignment(Label.CENTER);

        controlPanel.add(label);
        label.setText("Pick any number");
    }

    private void calc(float f) {
        f = (-1) * (f / 2);
        label.setText("" + f);
    }

    private void sqrt2(ActionEvent ae) {
        calc(2);
    }

    private void sqrt3(ActionEvent ae) {
        calc(-23);
    }

    private void sqrt4(ActionEvent ae) {
        calc(4);
    }

    private void sqrt5(ActionEvent ae) {
        calc(-175);
    }

    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
    }

    public static void main(String args[]) {
        try {
            Main mw = new Main();
            mw.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}