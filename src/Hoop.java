import java.awt.Image;

import objectdraw.*;

public class Hoop extends ActiveObject {
    private static final int MOVE_DELAY = 20;
    
    private VisibleImage theRing;
    private Charlie charlie;
    private double speed;
    
    public Hoop (Image ringImage, Charlie charlie, Location loc, double speed, DrawingCanvas canvas) {
        theRing = new VisibleImage(ringImage, loc, canvas);
        this.speed = speed;
        this.charlie = charlie;
                
        start();
    }
    
    @Override
    public void run () {
        double moveDistance = speed * MOVE_DELAY;
        
        while (theRing.getX() + theRing.getWidth() > 0) {
            theRing.move(-moveDistance, 0);
            if (charlie.overlaps(theRing)) {
                charlie.kill();
            }
            pause(MOVE_DELAY);
        }
    }
}
