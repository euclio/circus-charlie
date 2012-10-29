import java.awt.Image;

import objectdraw.*;

public class Hoop extends ActiveObject {
    private static final int MOVE_DELAY = 20;
    private static final double HOOP_UPPER_BOUND = 60;
    private static final double HOOP_LOWER_BOUND = 40;

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
            if (charlie.isTouching(this)) {
                charlie.kill();
            }
            pause(MOVE_DELAY);
        }
    }
    
    public double getUpperBound() {
        return theHoop.getY() + HOOP_UPPER_BOUND;
    }
    
    public double getLowerBound() {
        return theHoop.getY() + theHoop.getHeight() - HOOP_LOWER_BOUND;
    }
    
    public VisibleImage getImage() {
        return theHoop;
    }
}
