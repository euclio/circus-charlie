import java.awt.Image;

import objectdraw.*;

public class Hoop extends ActiveObject {
    private static final int MOVE_DELAY = 20;

    private VisibleImage theHoop;
    private Charlie charlie;
    private double baseSpeed, fasterSpeed, slowerSpeed;

    public Hoop(Image ringImage, Charlie charlie, Location loc, double speed,
            DrawingCanvas canvas) {
        theHoop = new VisibleImage(ringImage, loc, canvas);
        this.baseSpeed = speed;
        this.charlie = charlie;

        fasterSpeed = speed + .2;
        slowerSpeed = speed - .15;

        start();
    }

    @Override
    public void run() {
        double speed = baseSpeed;

        while (theHoop.getX() + theHoop.getWidth() > 0) {
            if (!charlie.isJumping()) {
                if (charlie.isMovingForward()) {
                    // Make speed faster
                    speed = fasterSpeed;
                } else if (charlie.isMovingBackward()) {
                    // Make speed slower
                    speed = slowerSpeed;
                } else {
                    speed = baseSpeed;
                }
            }

            double moveDistance = speed * MOVE_DELAY;

            theHoop.move(-moveDistance, 0);
            if (charlie.overlaps(theHoop)) {
                charlie.kill();
            }
            pause(MOVE_DELAY);
        }
    }
}
