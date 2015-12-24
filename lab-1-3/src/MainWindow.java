import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainWindow extends Frame implements ActionListener {

    MenuBar menuBar;
    Menu menu;
    MenuItem showImage, showPlot, clearArea;
    Panel controlPanel;

    CanvasArea canvasArea;
    ImageDrawer imageDrawer;
    PlotDrawer plotDrawer;

    public MainWindow() throws Exception {
        super("GUI Window");
        setTitle("Canvas Window");
        setSize(800, 600);
        addWindowListener(new BasicWindowMonitor());
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        canvasArea = new CanvasArea();
        imageDrawer = new ImageDrawer(this);
        plotDrawer = new PlotDrawer();

        menuBar = new MenuBar();

        menu = new Menu("Menu");

        showImage = new MenuItem("Show Image");
        showImage.addActionListener(this::drawImage);

        showPlot = new MenuItem("Show Plot");
        showPlot.addActionListener(this::drawPlot);

        clearArea = new MenuItem("Clear Area");
        clearArea.addActionListener(this::clearCanvas);

        menu.add(showImage);
        menu.add(showPlot);
        menu.add(clearArea);

        menuBar.add(menu);

        setMenuBar(menuBar);

        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());

        add(controlPanel);

        controlPanel.add(canvasArea);
    }

    private void drawImage(ActionEvent ae) {
        try {
            Image image = imageDrawer.getImage(canvasArea);
            canvasArea.clear();
            canvasArea.getGraphics().drawImage(image, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawPlot(ActionEvent ae) {
        canvasArea.clear();
        plotDrawer.drawPlot(canvasArea);
    }

    private void clearCanvas(ActionEvent ae) {
        canvasArea.clear();
    }

    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
    }

    public static void main(String args[]) {
        try {
            MainWindow mw = new MainWindow();
            mw.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}