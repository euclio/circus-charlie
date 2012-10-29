import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class CircusCharlie extends WindowController implements KeyListener {
    private static final Location CHARLIE_ORIGIN = new Location(100, 360);

    private Charlie charlie;
    private Background background;
    private HoopFactory ringFactory;

    protected static final int CANVAS_HEIGHT = 600;
    protected static final int CANVAS_WIDTH = 800;

    public void begin() {
        // Get image assets
        Image backgroundImage = getImage("resources/background.gif");
        Image charlieImage = getImage("resources/charlie.gif");
        Image ringImage = getImage("resources/ringOfFire.gif");

        // Set up objects essential to game
        background = new Background(backgroundImage, canvas);
        charlie = new Charlie(charlieImage, background, CHARLIE_ORIGIN, canvas);       
        ringFactory = new HoopFactory(ringImage, charlie, canvas);
        
        // Set up key listener
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
        
        // Set window size
        this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
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
        // Stop moving if you were moving
        if (e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            charlie.stopMoving();
        }
    }
}