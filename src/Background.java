import java.awt.Image;
import java.util.LinkedList;

import objectdraw.*;

public class Background {
    private static final int CANVAS_HEIGHT = CircusCharlie.CANVAS_HEIGHT;
    private static final int CANVAS_WIDTH = CircusCharlie.CANVAS_WIDTH;

    private VisibleImage leftBG, bg, rightBG;

    // Represents the tiled background. Three images cycled when necessary.
    private LinkedList<VisibleImage> tiledBG = new LinkedList<VisibleImage>();

    public Background(Image backgroundImage, DrawingCanvas canvas) {
        bg = new VisibleImage(backgroundImage, new Location(0, 0), canvas);

        if (bg.getWidth() != CANVAS_WIDTH || bg.getHeight() != CANVAS_HEIGHT) {
            throw new IllegalArgumentException(
                    "The background image is not the same size as the screen.");
        }

        leftBG = new VisibleImage(backgroundImage, new Location(-bg.getWidth(),
                0), canvas);
        rightBG = new VisibleImage(backgroundImage, new Location(bg.getWidth(),
                0), canvas);

        tiledBG.add(bg);
        tiledBG.addFirst(leftBG);
        tiledBG.addLast(rightBG);
    }

    public void move(double dx) {
        // Move each image in the list
        for (VisibleImage i : tiledBG) {
            i.move(dx, 0);
        }

        // Check that moving the images did not leave a gap
        if (tiledBG.getFirst().getX() > 0) {
            // Move rightmost image to leftmost position on screen to fill gap
            VisibleImage newFirst = tiledBG.removeLast();
            newFirst.move(2 * -CANVAS_WIDTH, 0);

            // Cycle the images
            tiledBG.addFirst(newFirst);
        } else if (tiledBG.getLast().getX() + CANVAS_WIDTH < CANVAS_WIDTH) {
            // Move leftmost image to rightmost position on screen to fill gap
            VisibleImage newLast = tiledBG.removeFirst();
            newLast.move(2 * CANVAS_WIDTH, 0);

            // Cycle the images
            tiledBG.addLast(newLast);
        }
    }
}