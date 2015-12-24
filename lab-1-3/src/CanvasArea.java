import java.awt.*;

public class CanvasArea extends Canvas {

    public CanvasArea() {
        setBackground(Color.GRAY);
        setSize(780, 560);
    }

    public void paint(Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;
        g2.drawString("Hello! Please select any option from menu to get started.", 230, 250);
    }

    public void clear() {
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
    }
}