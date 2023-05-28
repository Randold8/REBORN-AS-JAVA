package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

public class KeyHandler implements KeyListener {

    public int upPressed, downPressed, rightPressed, leftPressed = 0;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = 1;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = 1;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = 1;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = 0;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = 0;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = 0;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = 0;
        }

    }
}

