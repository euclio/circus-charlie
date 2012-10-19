import objectdraw.*;
import java.awt.Image;

public class Charlie extends ActiveObject {
	private static final double FORWARD_SPEED = .4;
	private static final double BACKWARD_SPEED = .2;

	private static final int MOVE_DISTANCE = 2;

	private static final int FORWARD_MOVE_DELAY = (int) (MOVE_DISTANCE / FORWARD_SPEED);
	private static final int BACKWARD_MOVE_DELAY = (int) (MOVE_DISTANCE / BACKWARD_SPEED);
	private static final int JUMP_DELAY = 40;
	
	private static final int JUMP_DISTANCE = 4;
	private static final int JUMP_HEIGHT = 80;

	private VisibleImage charlie;

	private boolean isMovingBackward = false;
	private boolean isMovingForward = false;
	private boolean onScreen = false;
	private boolean isAlive = false;
	private boolean isJumping = false;

	public Charlie(Image charlieImage, Location origin, DrawingCanvas canvas) {
		this.charlie = new VisibleImage(charlieImage, origin, canvas);
		onScreen = true;
		isAlive = true;

		start();
	}

	@Override
	public void run() {
		while (isAlive) {
			if (isMovingBackward) {
				charlie.move(-MOVE_DISTANCE, 0);
				pause(FORWARD_MOVE_DELAY);
			} else if (isMovingForward) {
				charlie.move(MOVE_DISTANCE, 0);
				pause(BACKWARD_MOVE_DELAY);
			}

			// Move charlie
			if (isJumping) {

				// Charlie keeps horizontal speed when jumping
				double initialDistance;

				if (isMovingBackward) {
					initialDistance = BACKWARD_SPEED * BACKWARD_MOVE_DELAY;
				} else if (isMovingForward) {
					initialDistance = FORWARD_SPEED * FORWARD_MOVE_DELAY;
				} else {
					initialDistance = 0;
				}

				for (int i = 0; i < JUMP_HEIGHT; i += JUMP_DISTANCE) {
					charlie.move(initialDistance, JUMP_DISTANCE);
					pause(JUMP_DELAY);
				}
				
				for (int i = JUMP_HEIGHT; i > 0; i -= JUMP_DISTANCE) {
					charlie.move(initialDistance, -JUMP_DISTANCE);
					pause(JUMP_DELAY);
				}
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
		this.isMovingForward = false;
	}

	public void stopMoving() {
		this.isMovingBackward = false;
		this.isMovingForward = false;
	}

	public void kill() {
		isAlive = false;
		removeFromCanvas();
	}

	private void removeFromCanvas() {
		if (onScreen)
			charlie.removeFromCanvas();

		onScreen = false;
	}
}
