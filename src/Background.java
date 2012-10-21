import java.awt.Image;
import java.util.LinkedList;

import objectdraw.*;

public class Background {
    private static final int CANVAS_HEIGHT = CircusCharlie.CANVAS_HEIGHT;
    private static final int CANVAS_WIDTH = CircusCharlie.CANVAS_WIDTH;

    private VisibleImage bg;
    private LinkedList<VisibleImage> tiledBG = new LinkedList<VisibleImage>();

    public Background(Image backgroundImage, DrawingCanvas canvas) {
        bg = new VisibleImage(backgroundImage, new Location(0, 0), canvas);

        if (bg.getWidth() != CANVAS_WIDTH || bg.getHeight() != CANVAS_HEIGHT) {
            throw new IllegalArgumentException(
                    "The background image is not the same size as the screen.");
        }

        tiledBG.add(bg);

        VisibleImage leftBG = new VisibleImage(backgroundImage, new Location(
                -bg.getWidth(), 0), canvas);
        VisibleImage rightBG = new VisibleImage(backgroundImage, new Location(
                bg.getWidth(), 0), canvas);

        tiledBG.addFirst(leftBG);
        tiledBG.addLast(rightBG);
    }

    public void move(double dx) {
        if (dx > 0) {
            // Move right
            if (tiledBG.getFirst().getX() > 0) {
                // Cycle through images
                tiledBG.addFirst(tiledBG.removeLast());
            }

            // Move all background images
            for (VisibleImage i : tiledBG) {
                i.move(dx, 0);
            }

        } else {
            // Move left
            if (tiledBG.getLast().getX() < CANVAS_WIDTH) {
                // Cycle through images
                tiledBG.addLast(tiledBG.removeFirst());
            }

            // Move all background images
            for (VisibleImage i : tiledBG) {
                i.move(dx, 0);
            }
        }
    }

}
