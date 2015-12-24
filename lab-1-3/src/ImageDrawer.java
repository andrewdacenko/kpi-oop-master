import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDrawer {
    Frame window;

    public ImageDrawer(Frame w) {
        window = w;
    }

    public Image getImage(CanvasArea canvasArea) throws IOException {
        BufferedImage image = ImageIO.read(chooseImage()[0]);
        return scaleImage(canvasArea, image);
    }

    private File[] chooseImage() {
        FileDialog fd = new FileDialog(window, "Choose a file", FileDialog.LOAD);
        fd.setDirectory("");
        fd.setFilenameFilter((dir, name) -> name.endsWith(".jpeg") || name.endsWith(".jpg") || name.endsWith(".png"));
        fd.setVisible(true);
        return fd.getFiles();
    }

    private Image scaleImage(CanvasArea canvasArea, BufferedImage original) {
        int canvasAreaWidth = canvasArea.getWidth();
        int canvasAreaHeight = canvasArea.getHeight();

        double factor;

        double widthFactor = (double) canvasAreaWidth / (double) original.getWidth();
        double heightFactor = (double) canvasAreaHeight / (double) original.getHeight();

        factor = widthFactor < heightFactor ? widthFactor : heightFactor;

        int scaledWidth = (int) (original.getWidth() * factor);
        int scaledHeight = (int) (original.getHeight() * factor);

        return original.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
    }
}
