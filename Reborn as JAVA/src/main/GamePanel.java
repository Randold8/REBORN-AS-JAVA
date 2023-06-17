package main;

import entity.Player;
import object.SuperObject;
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
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //НАСТРОЙКИ МИРА
    public final int maxWorldCol = 52;
    public final int maxWorldRow = 32;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    MouseMotion mouseMH = new MouseMotion();
    public Player player = new Player(this,keyH);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);

    public SuperObject obj[] = new SuperObject[10];
    public AssetSetter aSetter = new AssetSetter(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseMH);
        this.setFocusable(true);
    }
    public void setupGame() {
        aSetter.setObject();
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
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }
        player.draw(g2);

        g2.dispose();
    }
}
