package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class GamePanel extends JPanel implements Runnable {
    //НАСТРОЙКИ ЭКРАНА
    final int originalTileSize = 16; //16 на 16 тайлы
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    MouseMotion mouseMH = new MouseMotion();
    Player player = new Player(this,keyH);
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseMH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //0.166666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {

            long currentTime = System.nanoTime();
            // 1 ОБНОВЛЕНИЕ: обновляем информацию такую как позиции персонажей

            update();
            // 2 РИСОВАНИЕ: рисуем экран с обновленной информацией
            repaint();
            try {
                double remainingTime = (nextDrawTime - System.nanoTime())/1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
