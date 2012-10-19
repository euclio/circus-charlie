import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

public class CircusCharlie extends WindowController implements KeyListener {
	private Image charlieImage;
	private Charlie charlie;
	private boolean keyDown = false;

	public void begin(){
		charlieImage = getImage("resources/tempLion.jpg");
		charlie = new Charlie(charlieImage, new Location(0, 400), canvas);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!keyDown) {

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				charlie.moveBackward();

			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				charlie.moveForward();
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				charlie.jump();
			}
		}
		keyDown = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keyDown = false;
		charlie.stopMoving();
	}

}
