import java.awt.Image;

import objectdraw.*;

public class RingFactory extends ActiveObject {
    private static final int CREATION_DELAY = 3000;
    private static final Location RING_ORIGIN = new Location (CircusCharlie.CANVAS_WIDTH, 30);
    private static final double RING_SPEED = .2;
    
    private Charlie charlie;
    private Image ringImage;
    private DrawingCanvas canvas;
    
    
    public RingFactory (Image ringImage, Charlie charlie, DrawingCanvas canvas) {
        this.charlie = charlie;
        this.canvas = canvas;
        this.ringImage = ringImage;
        start();
    }
    
    @Override
    public void run () {
        while (true) {
            new Ring(ringImage, charlie, RING_ORIGIN, RING_SPEED, canvas);
            
            pause(CREATION_DELAY);
        }
    }
}
