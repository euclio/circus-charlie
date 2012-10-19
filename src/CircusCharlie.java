import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

public class CircusCharlie extends WindowController implements KeyListener {

	private Charlie charlie;
	private boolean keyDown = false;

	public void begin(){
		charlie = new Charlie(getImage("assets/Circus-Lion-Embroidery-Design-683.jpg"), new Location(0, 600), canvas);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!keyDown) {

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				charlie.moveLeft();

			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				charlie.moveRight();
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
