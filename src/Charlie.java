import objectdraw.*;

import java.awt.Color;
import java.awt.Image;

public class Charlie extends ActiveObject {
    // Speeds for Charlie's movement
    private static final double FORWARD_SPEED = .4;
    private static final double BACKWARD_SPEED = .2;
    private static final double JUMP_SPEED = .6;

    // The height of Charlie's jump in pixels
    private static final int JUMP_HEIGHT = 250;

    // Delay between drawing the sprites
    private static final int MOVE_DELAY = 10;

    // Distances to move the sprites for animation
    private static final double FORWARD_MOVE_DISTANCE = -FORWARD_SPEED
            * MOVE_DELAY;
    private static final double BACKWARD_MOVE_DISTANCE = BACKWARD_SPEED
            * MOVE_DELAY;
    private static final double JUMP_DISTANCE = JUMP_SPEED * MOVE_DELAY;

    private VisibleImage charlie;
    private Background background;
    private Location origin;
    private DrawingCanvas canvas;

    private boolean isMovingBackward = false;
    private boolean isMovingForward = false;
    private boolean onScreen = false;
    private boolean isAlive = false;
    private boolean isJumping = false;

    public Charlie(Image charlieImage, Background background, Location origin,
            DrawingCanvas canvas) {
        this.charlie = new VisibleImage(charlieImage, origin, canvas);
        this.background = background;
        this.origin = origin;
        this.canvas = canvas;
        onScreen = true;
        isAlive = true;

        start();
    }

    @Override
    public void run() {
        while (isAlive) {
            if (isMovingBackward) {
                background.move(BACKWARD_MOVE_DISTANCE);
            } else if (isMovingForward) {
                background.move(FORWARD_MOVE_DISTANCE);
            }

            pause(MOVE_DELAY);

            // Move charlie
            if (isJumping) {
                double horizontalChange = 0;

                // Charlie keeps horizontal movement when jumping
                if (isMovingBackward) {
                    horizontalChange = BACKWARD_MOVE_DISTANCE;
                } else if (isMovingForward) {
                    horizontalChange = FORWARD_MOVE_DISTANCE;
                }

                // Upward part of jump
                for (int i = 0; i < JUMP_HEIGHT; i += JUMP_DISTANCE) {
                    charlie.move(0, -JUMP_DISTANCE);
                    background.move(horizontalChange);
                    pause(MOVE_DELAY);
                }

                // Downward part of jump
                for (int i = 0; i < JUMP_HEIGHT; i += JUMP_DISTANCE) {
                    charlie.move(0, JUMP_DISTANCE);
                    background.move(horizontalChange);
                    pause(MOVE_DELAY);
                }
                isJumping = false;
            }
        }
    }

    public void jump() {
        this.isJumping = true;
    }

    public void moveBackward() {
        this.isMovingBackward = true;
    }

    public void moveForward() {
        this.isMovingForward = true;
    }

    public void stopMoving() {
        this.isMovingBackward = false;
        this.isMovingForward = false;
    }

    public void kill() {
        isAlive = false;
        removeFromCanvas();
    }

    public void reset() {
        charlie.moveTo(origin);
        isAlive = true;
    }

    private void removeFromCanvas() {
        if (onScreen)
            charlie.removeFromCanvas();

        onScreen = false;
    }

    public boolean isMovingForward() {
        return isMovingForward;
    }

    public boolean isMovingBackward() {
        return isMovingBackward;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public FramedRect getHitbox() {
        FramedRect charlieHitbox = new FramedRect(charlie.getX(),
                charlie.getY()+68, charlie.getHeight()-80, charlie.getWidth(), canvas);
        charlieHitbox.hide();
        return charlieHitbox;

    }
}
