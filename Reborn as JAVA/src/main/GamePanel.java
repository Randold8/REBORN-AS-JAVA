package main;

import entity.Player;
import environment.Env;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class GamePanel extends JPanel implements Runnable {
    //НАСТРОЙКИ ЭКРАНА
    final int originalTileSize = 16; //16 на 16 тайлы
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //НАСТРОЙКИ МИРА
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler(this);
    MouseHandler mouseH = new MouseHandler();
    MouseMotion mouseMH = new MouseMotion();
    Sound music = new Sound();
    Sound se = new Sound();
    public AssetSetter aSetter = new AssetSetter(this);
    public Env env = new Env(this);
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    Thread gameThread;
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    public GamePanel() throws IOException, FontFormatException {
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
        playMusic(0,-10f);
        gameState = playState;
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
        if (gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {

        }
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
        ui.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i,float f) {
        music.setFile(i);
        music.play(f);
        music.loop();
    }
    public void stopMusic () {
        music.stop();
    }
    public void playSE (int i, float vol) {
        se.setFile(i);
        se.play(vol);
    }
}
