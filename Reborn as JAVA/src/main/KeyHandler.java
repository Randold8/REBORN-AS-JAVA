package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public int upPressed, downPressed, rightPressed, leftPressed, R_Pressed = 0;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_R){
            R_Pressed = 1;
        }

        if(code == KeyEvent.VK_W||code==KeyEvent.VK_UP) {
            upPressed = 1;
        }
        if(code == KeyEvent.VK_A||code==KeyEvent.VK_LEFT) {
            leftPressed = 1;
        }
        if(code == KeyEvent.VK_S||code==KeyEvent.VK_DOWN) {
            downPressed = 1;
        }
        if(code == KeyEvent.VK_D||code==KeyEvent.VK_RIGHT) {
            rightPressed = 1;
        }
        if(code==KeyEvent.VK_SPACE) {
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code==KeyEvent.VK_R){
            R_Pressed=0;
        }

        if(code == KeyEvent.VK_W||code==KeyEvent.VK_UP) {
            upPressed = 0;
        }
        if(code == KeyEvent.VK_A||code==KeyEvent.VK_LEFT) {
            leftPressed = 0;
        }
        if(code == KeyEvent.VK_S||code==KeyEvent.VK_DOWN) {
            downPressed = 0;
        }
        if(code == KeyEvent.VK_D||code==KeyEvent.VK_RIGHT) {
            rightPressed = 0;
        }

    }
}

