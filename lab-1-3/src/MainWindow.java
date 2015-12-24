import java.awt.*;
import java.awt.event.*;

public class MainWindow extends Frame implements ActionListener {
    // A⋅x2 +A⋅y2 +B⋅x+C⋅y+D=0
    MenuBar menuBar;
    Menu menu;
    MenuItem showImage, showPlot, clearArea;

    public MainWindow() {
        super("GUI Window");
        setTitle("AWT Menu"); // Set the title
        setSize(800, 600); // Set size to the frame
        addWindowListener(new BasicWindowMonitor());
        setLayout(new FlowLayout()); // Set the layout
        setVisible(true); // Make the frame visible
        setLocationRelativeTo(null);  // Center the frame

        // Create the menu bar
        menuBar = new MenuBar();

        menu = new Menu("Menu");

        showImage = new MenuItem("Show Image");
        showPlot = new MenuItem("Show Plot");
        clearArea = new MenuItem("Clear Area");

        menu.add(showImage);
        menu.add(showPlot);
        menu.add(clearArea);

        menuBar.add(menu);

        setMenuBar(menuBar);
    }

    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
    }

    public static void main(String args[]) {
        new MainWindow();
    }
}