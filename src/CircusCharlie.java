import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CircusCharlie extends WindowController implements KeyListener {
    private Image charlieImage;
    private Charlie charlie;
    private Background background;

    protected static final int CANVAS_HEIGHT = 600;
    protected static final int CANVAS_WIDTH = 800;

    private final Set<Character> pressed = new HashSet<Character>();

    public void begin() {
        // making charlie and the background
        background = new Background(getImage("resources/stupidBackground.jpg"),
                canvas);
        charlieImage = getImage("resources/tempLion.jpg");
        charlie = new Charlie(charlieImage, background, new Location(0, 400),
                canvas);
        // set window size
        this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);

        // set up key listener
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
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