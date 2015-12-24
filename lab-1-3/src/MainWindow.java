import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class MainWindow extends Frame implements ActionListener {
    // A⋅x2 +A⋅y2 +B⋅x+C⋅y+D=0
    MenuBar menuBar;
    Menu menu;
    MenuItem showImage, showPlot, clearArea;
    Panel controlPanel;
    ImageDrawer imageDrawer;
    CanvasArea canvasArea;

    public MainWindow() {
        super("GUI Window");
        setTitle("AWT Menu");
        setSize(800, 600);
        addWindowListener(new BasicWindowMonitor());
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        imageDrawer = new ImageDrawer(this);

        menuBar = new MenuBar();

        menu = new Menu("Menu");

        showImage = new MenuItem("Show Image");
        showImage.addActionListener(this::drawImage);
        showPlot = new MenuItem("Show Plot");
        clearArea = new MenuItem("Clear Area");

        menu.add(showImage);
        menu.add(showPlot);
        menu.add(clearArea);

        menuBar.add(menu);

        setMenuBar(menuBar);

        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());

        add(controlPanel);

        canvasArea = new CanvasArea();
        controlPanel.add(canvasArea);
    }

    private void drawImage(ActionEvent ae) {
        try {
            canvasArea.getGraphics().drawImage(imageDrawer.getImage(canvasArea), 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
    }

    public static void main(String args[]) {
        MainWindow mw = new MainWindow();
        mw.setVisible(true);
    }
}