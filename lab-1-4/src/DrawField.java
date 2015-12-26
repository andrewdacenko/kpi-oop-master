import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class DrawField extends JPanel {
    private BufferedImage buffer;
    private static final String text = "Hello world";
    private HashSet<Color> colors = new HashSet<Color>();
    private Color color = Color.blue;
    private Font font;

    int width = 220, height = 220, stepWidth = 2, stepHeight = 2;
    float fontStep = 2.0f;

    Iterator<Color> colorIterator;
    Iterator<Color> fontIterator;

    public DrawField() {
        setOpaque(true);
        addComponentListener(new ComponentListenerImpl());
        colors.add(Color.pink);
        colors.add(Color.blue);
        colors.add(Color.green);
        colors.add(Color.cyan);

        colorIterator = colors.iterator();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rebuildBuffer();
        g.drawImage(buffer, 0, 0, this);
    }

    public void tick(MainFrame window) {

        try {
            width += stepWidth;
            height += stepHeight;

            Graphics2D g2d = buffer.createGraphics();
            g2d.setFont(font);
            Rectangle2D stringBounds = g2d.getFontMetrics().getStringBounds(text, g2d);

            boolean needChange = false;
            if (width + stringBounds.getWidth() > window.getWidth()) {
                if (stepWidth != -1) {
                    needChange = true;
                }
                stepWidth = -1;
            }

            if (width < 0) {
                if (stepWidth != 1) {
                    needChange = true;
                }
                stepWidth = 1;
            }

            if (height + stringBounds.getHeight() + 8 > window.getHeight()) {
                if (stepHeight != -1) {
                    needChange = true;
                }
                stepHeight = -1;
            }

            if (height < 0) {
                if (stepHeight != 1) {
                    needChange = true;
                }
                stepHeight = 1;
            }

            if (needChange) {
                changeString(g2d);
            }

        } catch (NullPointerException e) {

        }
    }

    private void changeString(Graphics2D g2d) {
        Font currentFont = font != null ? font : g2d.getFont();
        fontStep = currentFont.getSize() > 50
                ? 1 / fontStep
                : fontStep;

        fontStep = currentFont.getSize() < 10 ? 1 / fontStep : fontStep;

        font = new Font(currentFont.getName(), 0, (int) (fontStep * currentFont.getSize()));

        g2d.setFont(font);

        if (!colorIterator.hasNext()) {
            colorIterator = colors.iterator();
        }

        color = colorIterator.next();
    }

    public void rebuildBuffer() {
        int w = getWidth();
        int h = getHeight();
        buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffer.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        g2d.setColor(color);
        g2d.drawString(text, width, height);
    }

    private class ComponentListenerImpl extends ComponentAdapter {

        private Dimension lastSize = null;

        @Override
        public void componentShown(ComponentEvent e) {
            if (!getSize().equals(lastSize)) {
                rebuildBuffer();
                lastSize = getSize();
            }
        }

        @Override
        public void componentResized(ComponentEvent e) {
            if (!getSize().equals(lastSize)) {
                rebuildBuffer();
                lastSize = getSize();
            }
        }
    }
}