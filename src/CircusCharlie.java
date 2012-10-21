import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class CircusCharlie extends WindowController implements KeyListener {
    private static final Location CHARLIE_ORIGIN = new Location(40, 400);

    private Charlie charlie;
    private Background background;
    private RingFactory ringFactory;

    protected static final int CANVAS_HEIGHT = 600;
    protected static final int CANVAS_WIDTH = 800;

    public void begin() {
        // Get image assets
        Image backgroundImage = getImage("resources/background.gif");
        Image charlieImage = getImage("resources/charlie.gif");
        Image ringImage = getImage("resources/ringOfFire.gif");

        // set up objects essential to game
        background = new Background(backgroundImage, canvas);
        charlie = new Charlie(charlieImage, background, CHARLIE_ORIGIN, canvas);       
        ringFactory = new RingFactory(ringImage, charlie, canvas);
        
        // set up key listener
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
        
        // set window size
        this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    }
    

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            charlie.moveForward();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            charlie.moveBackward();
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            charlie.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // stop moving if you were moving
        if (e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            charlie.stopMoving();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            charlie.stopJumping();
        }
    }
}