import objectdraw.*;
import java.awt.Image;

public class Charlie extends ActiveObject{
	private VisibleImage charlie;
	
	private boolean isMoving = false;
	private boolean onScreen = false;
	private boolean isAlive = false;
	
	public Charlie (Image charlieImage, Location origin, DrawingCanvas canvas) {
		this.charlie = new VisibleImage(charlieImage, origin, canvas);
		onScreen = true;
		isAlive = true;
		
		start();
	}
	
	@Override
	public void run () {

	}
	
	public void jump () {
		
	}
	
	public void setMovingLeft(boolean isMovingLeft) {
		
	}
	
	public void setMovingRight(boolean isMovingRight) {
		
	}
	
	public void kill () {
		isAlive = false;
	}
	
	public void removeFromCanvas () {
		charlie.removeFromCanvas();
		onScreen = false;
	}
}
