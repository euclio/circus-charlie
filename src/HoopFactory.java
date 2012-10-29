import java.awt.Image;

import objectdraw.*;

public class HoopFactory extends ActiveObject {
    private static final int CREATION_DELAY = 3000;
    private static final Location RING_ORIGIN = new Location (CircusCharlie.CANVAS_WIDTH, 100);
    private static final double RING_SPEED = .2;
    
    private Charlie charlie;
    private Image ringImage;
    private DrawingCanvas canvas;
    private boolean isRunning = false;
    
    
    public HoopFactory (Image ringImage, Charlie charlie, DrawingCanvas canvas) {
        this.charlie = charlie;
        this.canvas = canvas;
        this.ringImage = ringImage;
        
        this.start();
    }
    
    @Override
    public void run () {
        isRunning = true;
        
        while (isRunning) {
            Hoop theHoop = new Hoop(ringImage, charlie, RING_ORIGIN, RING_SPEED, canvas);

            
            pause(CREATION_DELAY);
        }
    }

    public void stopProduction () {
        isRunning = false;
    }
}
