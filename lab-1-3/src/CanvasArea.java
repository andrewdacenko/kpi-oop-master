import java.awt.*;

public class CanvasArea extends Canvas {

    public CanvasArea() {
        setBackground(Color.GRAY);
        setSize(780, 560);
    }

    public void paint(Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;
        g2.drawString("It is a custom canvas area", 70, 70);
    }

    public void clear() {
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
    }
}