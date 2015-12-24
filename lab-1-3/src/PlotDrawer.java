import java.awt.*;

public class PlotDrawer {
    // A⋅x2 +A⋅y2 +B⋅x+C⋅y+D=0

    final double a = 1.0d;
    final double b = -2.0d;
    final double c = -4.0d;
    final double d = -4.0d;

    private double centerX;
    private double centerY;
    private double radius;

    public PlotDrawer() throws Exception {
        getCircleCenterAndRadius();

        if (radius == Double.NaN) {
            throw new Exception("Not correct equation");
        }
    }

    private void getCircleCenterAndRadius() {
        centerX = calculateCenter(b, a);
        centerY = calculateCenter(c, a);
        radius = calculateRadius();
    }

    private double calculateCenter(double predicate, double divider) {
        double center = predicate / 2.0d;
        center = center / divider;
        return center * (predicate >= 0 ? 1.0d : -1.0d);
    }

    private double calculateRadius() {
        double radius = 0.0d - d - c * centerY - b * centerX - a * (centerX * centerX + centerY * centerY);
        return Math.sqrt(radius);
    }

    public void drawPlot(CanvasArea canvasArea) {
        drawAxis(canvasArea);
        drawGraphic(canvasArea);
    }

    private void drawGraphic(CanvasArea canvasArea) {
        Graphics graphics = canvasArea.getGraphics();

        int height = canvasArea.getHeight();
        int width = canvasArea.getWidth();

        int minSide = height < width
                ? height
                : width;

        double steps = calculateStep();
        double pixelsPerStep = (double) minSide / steps;

        int r = (int) (pixelsPerStep * radius);
        int x = width / 2 + (int) (pixelsPerStep * centerX) - r;
        int y = height / 2 - (int) (pixelsPerStep * centerY) - r;

        graphics.drawOval(x, y, r * 2, r * 2);
    }

    private void drawAxis(CanvasArea canvasArea) {
        Graphics graphics = canvasArea.getGraphics();

        int height = canvasArea.getHeight();
        int width = canvasArea.getWidth();

        int minSide = height < width
                ? height
                : width;

        double steps = calculateStep();
        double pixelsPerStep = (double) minSide / steps;

        graphics.drawLine(width / 2, 0, width / 2, height);
        graphics.drawLine(0, height / 2, width, height / 2);

        for (int i = 1; i < steps; i++) {
            final int pixelStepOnLine = i * (int) pixelsPerStep;
            int stepRight = pixelStepOnLine + width / 2;
            int stepDown = pixelStepOnLine + height / 2;
            int stepLeft = width / 2 - pixelStepOnLine;
            int stepUp = height / 2 - pixelStepOnLine;

            graphics.drawLine(stepRight, height / 2 - 10, stepRight, height / 2 + 10);
            graphics.drawString(String.valueOf(i), stepRight - 5, height / 2 + 25);
            graphics.drawLine(stepLeft, height / 2 - 10, stepLeft, height / 2 + 10);
            graphics.drawString(String.valueOf(-i), stepLeft - 10, height / 2 + 25);
            graphics.drawLine(width / 2 - 10, stepDown, width / 2 + 10, stepDown);
            graphics.drawString(String.valueOf(-i), width / 2 - 25, stepDown + 5);
            graphics.drawLine(width / 2 - 10, stepUp, width / 2 + 10, stepUp);
            graphics.drawString(String.valueOf(i), width / 2 - 35, stepUp + 5);
        }
    }

    private double calculateStep() {
        double gutter = radius * 3;

        double top = centerX + radius;
        top = top < radius
                ? gutter
                : top;

        double bottom = centerX - radius;
        bottom = bottom < radius
                ? -gutter
                : bottom;

        double right = centerY + radius;
        right = right < radius
                ? gutter
                : right;

        double left = centerY - radius;
        left = left < radius
                ? -gutter
                : left;

        double maxHeight = (int) (top - bottom);
        double maxWidth = (int) (right - left);

        return maxHeight > maxWidth
                ? maxHeight
                : maxWidth;
    }
}
