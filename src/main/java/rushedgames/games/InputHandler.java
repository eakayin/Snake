package rushedgames.games;

import java.awt.event.*;

public class InputHandler implements KeyListener {

    boolean upPressed, downPressed, leftPressed, rightPressed;

    public void keyTyped(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){

        if(e.getExtendedKeyCode() == 'W'){
            upPressed = true;
        }
        else if(e.getExtendedKeyCode() == 'S'){
            downPressed = true;
        }
        else if(e.getExtendedKeyCode() == 'A'){
            leftPressed = true;
        }
        else if(e.getExtendedKeyCode() == 'D'){
            rightPressed = true;
        }

    }

    public void keyReleased(KeyEvent e){

        if(e.getExtendedKeyCode() == 'W'){
            upPressed = false;
        }
        else if(e.getExtendedKeyCode() == 'S'){
            downPressed = false;
        }
        else if(e.getExtendedKeyCode() == 'A'){
            leftPressed = false;
        }
        else if(e.getExtendedKeyCode() == 'D'){
            rightPressed = false;
        }

    }
    
}
