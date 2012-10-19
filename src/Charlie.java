import objectdraw.*;
import java.awt.Image;

public class Charlie extends ActiveObject{
	private static final int MOVE_DELAY_FORWARDS = 20;
	private static final int MOVE_DELAY_BACKWARDS = 40;
	
	private VisibleImage charlie;
	
	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;
	private boolean onScreen = false;
	private boolean isAlive = false;
	private boolean isJumping = false;
	
	public Charlie (Image charlieImage, Location origin, DrawingCanvas canvas) {
		this.charlie = new VisibleImage(charlieImage, origin, canvas);
		onScreen = true;
		isAlive = true;
		
		start();
	}
	
	@Override
	public void run () {
		while(isAlive) {
			if (isMovingLeft) {
				// move left
				pause(MOVE_DELAY_FORWARDS);
			} else if (isMovingRight) {
				// move right
				pause(MOVE_DELAY_BACKWARDS);
			}
			
			// Move charlie
			if (isJumping) {
				// Jump
			}
		}
	}
	
	public void jump () {
		this.isJumping = true;
	}
	
	public void moveLeft () {
		this.isMovingLeft = true;
	}
	
	public void moveRight () {
		this.isMovingRight = false;
	}
	
	public void stopMoving () {
		this.isMovingLeft = false;
		this.isMovingRight = false;
	}
	
	public void kill () {
		isAlive = false;
		removeFromCanvas();
	}
	
	private void removeFromCanvas () {
		if (onScreen)
			charlie.removeFromCanvas();
		
		onScreen = false;
	}
}
