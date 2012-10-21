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
    	//making charlie and the background
        background = new Background(getImage("resources/stupidBackground.jpg"),
                canvas);
        charlieImage = getImage("resources/tempLion.jpg");
        charlie = new Charlie(charlieImage, background, new Location(0, 400),
                canvas);
        //set window size
        this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);

        //set up key listener
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    	System.out.println(e.getKeyCode());
        // Checking for multiple key presses
    	pressed.add(e.getKeyChar());
    	//if the player jumps while moving, jump and keep moving
        if (pressed.size()>1) {
        	System.out.println("Multiple Keys Pressed");
            if (pressed.contains(KeyEvent.VK_RIGHT) && e.getKeyCode() == KeyEvent.VK_SPACE) {
            	charlie.jumpAndMoveForward();
            }
            if (pressed.contains(KeyEvent.VK_LEFT) && e.getKeyCode() == KeyEvent.VK_SPACE) {
            	charlie.jumpAndMoveBackward();
            }
        }
        //if the player is only pressing one key, move or jump appropriately
        else{
        	if (e.getKeyCode()==KeyEvent.VK_RIGHT){
        		charlie.moveForward();
        	}
        	else if (e.getKeyCode()==KeyEvent.VK_LEFT){
        		charlie.moveBackward();
        	}
        	else if (e.getKeyCode()==KeyEvent.VK_SPACE){
        		charlie.jump();
        	}
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    	System.out.println(e.getKeyCode());
        // stop moving if you were moving
        pressed.remove(e.getKeyChar());
        if(e.getKeyCode()==(KeyEvent.VK_LEFT)||e.getKeyCode()==(KeyEvent.VK_RIGHT)){
        charlie.stopMoving();
    }
        //stop jumping if you were jumping
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){
        	charlie.stopJumping();
        }


}
}