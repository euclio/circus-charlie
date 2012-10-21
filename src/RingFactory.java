import java.awt.Image;

import objectdraw.*;

public class RingFactory extends ActiveObject {
    private Charlie charlie;
    
    public RingFactory (Image ringImage, Charlie charlie, DrawingCanvas canvas) {
        
        start();
    }
    
    @Override
    public void run () {
    }
}
